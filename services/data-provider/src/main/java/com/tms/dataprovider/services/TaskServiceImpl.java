package com.tms.dataprovider.services;

import com.tms.dataprovider.core.enums.ProcessingStatus;
import com.tms.dataprovider.core.interfaces.services.TaskService;
import com.tms.dataprovider.core.mappers.TaskMapper;
import com.tms.dataprovider.core.models.dtos.TaskDto;
import com.tms.dataprovider.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public List<TaskDto> getAll() {
        return taskRepository.findAll()
                .stream()
                .map(x->taskMapper.toTaskDto(x)).toList();
    }

    @Override
    public List<TaskDto> getAllByStatus(ProcessingStatus status) {
        return taskRepository.findAllByStatus(status)
                .stream()
                .map(x->taskMapper.toTaskDto(x)).toList();
    }

    @Override
    public List<TaskDto> getAllByDate(LocalDate date) {
        return taskRepository.findAllByDate(date)
                .stream()
                .map(x->taskMapper.toTaskDto(x)).toList();
    }
}
