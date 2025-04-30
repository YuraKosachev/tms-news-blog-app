package com.tms.dataprovider.repositories;

import com.tms.dataprovider.core.enums.ProcessingStatus;
import com.tms.dataprovider.core.models.entities.Task;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    @Transactional(readOnly = true)
    @Query("SELECT t FROM Task t WHERE DATE(t.startAt) = :date")
    List<Task> findAllByDate(LocalDate date);

    @Transactional(readOnly = true)
    @Query("select t from Task t where t.status = :status")
    List<Task> findAllByStatus(ProcessingStatus status);
}
