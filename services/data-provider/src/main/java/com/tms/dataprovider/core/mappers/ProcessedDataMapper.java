package com.tms.dataprovider.core.mappers;

import com.tms.dataprovider.core.models.dtos.ProcessedDataDto;
import com.tms.dataprovider.core.models.entities.ProcessedData;
import com.tms.dataprovider.core.models.kafka.NewsMessage;
import com.tms.dataprovider.core.models.kafka.NewsPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessedDataMapper {

    public ProcessedDataDto toDto(ProcessedData processedData) {
        if(processedData == null)
            return null;
        return new ProcessedDataDto(
                processedData.getTitle(),
                processedData.getContent(),
                processedData.getDescription(),
                processedData.getCategory(),
                processedData.getSentAt());
    }

    public NewsPublisher entityToPublisher(ProcessedData processedData) {
        if(processedData == null)
            return null;
        return new NewsPublisher(
                processedData.getTitle(),
                processedData.getSource().getAbsoluteLink(),
                processedData.getContent(),
                processedData.getDescription(),
                processedData.getCategory(),
                processedData.getAuthor(),
                processedData.getSource().getPortal(),
                processedData.getSource().getMainLink(),
                processedData.getSentAt(),
                processedData.getCreatedAt()
        );
    }
    public NewsMessage entityToMessage(List<ProcessedData> list) {
        if(list == null)
            return null;

        return NewsMessage.builder()
                .news(list.stream().map(x->entityToPublisher(x)).toList())
                .build();
    }
}
