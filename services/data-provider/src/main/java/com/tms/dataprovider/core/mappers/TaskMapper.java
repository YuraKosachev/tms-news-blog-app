package com.tms.dataprovider.core.mappers;

import com.tms.dataprovider.core.enums.ProcessingStatus;
import com.tms.dataprovider.core.models.dtos.TaskDto;
import com.tms.dataprovider.core.models.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto toTaskDto(Task task) {
        if (task == null) {
            return null;
        }
        long sucessCount = task.getSources().stream()
                .filter(x -> x.getStatus() == ProcessingStatus.OK).count();

        long errorCount = task.getSources().stream()
                .filter(x -> x.getStatus() == ProcessingStatus.ERROR).count();
        return new TaskDto(task.getId(), task.getSource(), task.getStartAt(), task.getEndAt(), task.getStatus().getStatus(), sucessCount, errorCount);
    }
}
