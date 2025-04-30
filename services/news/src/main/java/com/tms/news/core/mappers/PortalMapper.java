package com.tms.news.core.mappers;

import com.tms.news.core.models.dtos.PortalCreateDto;
import com.tms.news.core.models.dtos.PortalDto;
import com.tms.news.core.models.dtos.PortalUpdateDto;
import com.tms.news.core.models.entities.Portal;
import com.tms.news.core.models.kafka.NewsPublisher;
import org.springframework.stereotype.Component;

@Component
public class PortalMapper {

    public Portal kafkaMessageToEntity(NewsPublisher newsPublisher) {
        if(newsPublisher == null)
            return null;

        return Portal.builder()
                .name(newsPublisher.portal())
                .mainLink(newsPublisher.mainUrl())
                .build();
    }

    public PortalDto entityToDto(Portal portal) {
        if(portal == null)
            return null;

        return new PortalDto(portal.getId(),
                portal.getName(),
                portal.getDescription(),
                portal.getMainLink());
    }

    public Portal dtoToEntity(PortalCreateDto dto){
        if(dto == null)
            return null;

        return Portal.builder()
                .name(dto.name())
                .description(dto.description())
                .mainLink(dto.url())
                .build();
    }

    public void dtoToEntity(PortalUpdateDto dto, Portal portal){
        portal.setName(dto.name());
        portal.setDescription(dto.description());
        portal.setMainLink(dto.url());
    }
}
