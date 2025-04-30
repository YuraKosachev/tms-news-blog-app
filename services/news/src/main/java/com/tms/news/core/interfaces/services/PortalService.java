package com.tms.news.core.interfaces.services;

import com.tms.news.core.models.dtos.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PortalService {
    List<PortalDto> getPortals();
    Optional<PortalDto> getPortalById(UUID id);
    PortalDto createPortal(PortalCreateDto portalDto);
    void deletePortalById(UUID id);
    PortalDto updatePortal(PortalUpdateDto dto);
}
