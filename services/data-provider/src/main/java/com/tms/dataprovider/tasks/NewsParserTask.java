package com.tms.dataprovider.tasks;

import com.tms.dataprovider.configurations.TaskConfiguration;
import com.tms.dataprovider.core.enums.ProcessingStatus;
import com.tms.dataprovider.core.exceptions.DataServiceException;
import com.tms.dataprovider.core.interfaces.units.CnnNewsDetailUnit;
import com.tms.dataprovider.core.interfaces.units.CnnNewsListUnit;
import com.tms.dataprovider.core.models.dtos.ArticleDto;
import com.tms.dataprovider.core.models.entities.ProcessedData;
import com.tms.dataprovider.core.models.entities.Source;
import com.tms.dataprovider.core.models.entities.Task;
import com.tms.dataprovider.core.models.parsers.cnn.ArticleCnn;
import com.tms.dataprovider.repositories.ProcessedDataRepository;
import com.tms.dataprovider.repositories.SourceRepository;
import com.tms.dataprovider.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsParserTask {

    private final TaskConfiguration configuration;
    private final CnnNewsDetailUnit cnnNewsDetailUnit;
    private final CnnNewsListUnit cnnNewsListUnit;
    private final TaskRepository taskRepository;
    private final SourceRepository sourceRepository;
    private final ProcessedDataRepository processedDataRepository;

    @Scheduled(cron = "${task.cnn.cron}")
    public void parseCnnNews() throws DataServiceException, IOException, URISyntaxException, InterruptedException {

        Task task = taskRepository.save(Task.builder()
                .status(ProcessingStatus.PROCESSING)
                .source("CNN News")
                .build());

        ProcessingStatus taskProcessingStatus = ProcessingStatus.OK;
        try {
            var links = cnnNewsListUnit.getCnnNewsList(configuration.getCnnWorldUrl());
            var savedLinks = sourceRepository.findAllByStatus(ProcessingStatus.OK);

            for (var link : links) {
                if(savedLinks.stream().anyMatch(x->x.getLink().equals(link))) {
                    continue;
                }
                var sourceBuilder = Source
                        .builder()
                        .link(link)
                        .absoluteLink(configuration.getCnnUrl() + link)
                        .portal("CNN")
                        .mainLink(configuration.getCnnUrl())
                        .task(task)
                        .status(ProcessingStatus.PROCESSING);

                var source = sourceRepository.save(sourceBuilder.build());
                try {
                    ArticleCnn articleCnn = cnnNewsDetailUnit.getArticle(link);

                    String authors = String.join(",", articleCnn.getAuthor().stream()
                            .map(x->x.getName()).toList());
                    var processedDataBuilder = ProcessedData.builder()
                            .title(articleCnn.getHeadline())
                            .publishedAt(articleCnn.getDatePublished())
                            .author(authors.isBlank() ? null : authors)
                            .category(String.join(",", articleCnn.getArticleSection()))
                            .description(articleCnn.getDescription())
                            .source(source)
                            .content(articleCnn.getArticleBody());

                    source.setProcessedData(processedDataBuilder.build());
                    source.setStatusCode(HttpStatus.SC_OK);
                    source.setStatus(ProcessingStatus.OK);
                    sourceRepository.save(source);
                }
                catch (DataServiceException e) {
                    taskProcessingStatus = ProcessingStatus.PARTIAL_ERROR;
                    source.setStatus(ProcessingStatus.ERROR);
                    source.setStatusCode(e.getCode());
                    sourceRepository.save(source);
                }
                catch (Exception ex) {
                    taskProcessingStatus = ProcessingStatus.PARTIAL_ERROR;
                    source.setStatus(ProcessingStatus.ERROR);
                    source.setStatusCode(HttpStatus.SC_OK);
                    sourceRepository.save(source);
                }
            }

            task.setEndAt(LocalDateTime.now());
            task.setStatus(taskProcessingStatus);

        } catch (Exception e) {
           task.setStatus(ProcessingStatus.ERROR);
           task.setEndAt(LocalDateTime.now());
        }
        taskRepository.save(task);
    }
}
