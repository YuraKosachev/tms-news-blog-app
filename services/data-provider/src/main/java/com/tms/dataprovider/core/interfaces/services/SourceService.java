package com.tms.dataprovider.core.interfaces.services;

import com.tms.dataprovider.core.models.dtos.SourceDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SourceService {
    List<SourceDto> getAll();
    Optional<SourceDto> getById(UUID id);
    List<SourceDto> getByTaskId(UUID taskId);
}
