package com.tms.dataprovider.core.mappers;

import com.tms.dataprovider.core.models.dtos.SourceDto;
import com.tms.dataprovider.core.models.entities.Source;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SourceMapper {

    private final ProcessedDataMapper processedDataMapper;
    public SourceDto toDto(Source source) {
        if(source == null) {
            return null;
        }
        return new SourceDto(
                source.getId(),
                source.getLink(),
                source.getStatusCode(),
                processedDataMapper.toDto(source.getProcessedData()),
                source.getStatus()
        );
    }
}
