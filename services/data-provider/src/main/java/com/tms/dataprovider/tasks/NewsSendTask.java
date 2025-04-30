package com.tms.dataprovider.tasks;

import com.tms.dataprovider.core.mappers.ProcessedDataMapper;
import com.tms.dataprovider.kafka.KafkaProducer;
import com.tms.dataprovider.repositories.ProcessedDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class NewsSendTask {

    private final ProcessedDataRepository processedDataRepository;
    private final KafkaProducer newsProducer;
    private final ProcessedDataMapper processedDataMapper;

    @Scheduled(cron = "${task.publisher.cron}")
    public void processNews() {
        var newsToSend = processedDataRepository.findAllNotSent();
        if (newsToSend.isEmpty())
            return;
        try {
            newsProducer.sendNews(processedDataMapper.entityToMessage(newsToSend));
            newsToSend.forEach(x -> x.setSentAt(LocalDateTime.now()));
            processedDataRepository.saveAll(newsToSend);
        } catch (Exception e) {

        }
    }

}
