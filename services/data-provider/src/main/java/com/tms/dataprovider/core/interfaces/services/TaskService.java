package com.tms.dataprovider.core.interfaces.services;

import com.tms.dataprovider.core.enums.ProcessingStatus;
import com.tms.dataprovider.core.models.dtos.TaskDto;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    List<TaskDto> getAll();
    List<TaskDto> getAllByStatus(ProcessingStatus status);
    List<TaskDto> getAllByDate(LocalDate date);
}
