package com.tms.news.services;

import com.tms.news.core.exceptions.ItemNotFoundException;
import com.tms.news.core.interfaces.services.PortalService;
import com.tms.news.core.mappers.PortalMapper;
import com.tms.news.core.models.dtos.PortalCreateDto;
import com.tms.news.core.models.dtos.PortalDto;
import com.tms.news.core.models.dtos.PortalUpdateDto;
import com.tms.news.core.models.entities.Category;
import com.tms.news.core.models.entities.Portal;
import com.tms.news.repositories.PortalRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PortalServiceImpl implements PortalService {

    private final PortalRepository portalRepository;
    private final PortalMapper portalMapper;

    @Override
    public List<PortalDto> getPortals() {
        return portalRepository.findAll()
                .stream().map(x->portalMapper.entityToDto(x))
                .toList();
    }

    @Override
    public Optional<PortalDto> getPortalById(UUID id) {
        return portalRepository.findById(id).map(x->portalMapper.entityToDto(x));
    }

    @Override
    public PortalDto createPortal(PortalCreateDto portalDto) {
        Portal portal = portalRepository.save(portalMapper.dtoToEntity(portalDto));
        return portalMapper.entityToDto(portal);
    }

    @Override
    public void deletePortalById(UUID id) {
        portalRepository.deleteById(id);
    }

    @Override
    public PortalDto updatePortal(PortalUpdateDto dto) {
        Portal entity = portalRepository.findById(dto.id()).orElseThrow(()-> new ItemNotFoundException("portal not found"));
        portalMapper.dtoToEntity(dto, entity);
        return portalMapper.entityToDto(portalRepository.save(entity));
    }
}
