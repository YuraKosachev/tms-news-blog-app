package com.tms.dataprovider.services;

import com.tms.dataprovider.core.interfaces.services.SourceService;
import com.tms.dataprovider.core.mappers.SourceMapper;
import com.tms.dataprovider.core.models.dtos.SourceDto;
import com.tms.dataprovider.repositories.SourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SourceServiceImpl implements SourceService {

    private final SourceRepository sourceRepository;
    private final SourceMapper sourceMapper;

    @Override
    public List<SourceDto> getAll() {
        return sourceRepository.findAll().stream()
                .map(source->sourceMapper.toDto(source))
                .toList();
    }

    @Override
    public Optional<SourceDto> getById(UUID id) {
        return sourceRepository.findById(id).map(source->sourceMapper.toDto(source));
    }

    @Override
    public List<SourceDto> getByTaskId(UUID taskId) {
        return sourceRepository.findAllByTaskId(taskId).stream()
                .map(source->sourceMapper.toDto(source))
                .toList();
    }
}
