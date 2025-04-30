package com.tms.dataprovider.repositories;

import com.tms.dataprovider.core.enums.ProcessingStatus;
import com.tms.dataprovider.core.models.entities.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface SourceRepository extends JpaRepository<Source, UUID> {
    @Transactional(readOnly = true)
    @Query("select s from Source s where s.status =:status")
    List<Source> findAllByStatus(ProcessingStatus status);

    @Transactional(readOnly = true)
    @Query("select s from Source s where s.task.id = :taskId")
    List<Source> findAllByTaskId(UUID taskId);
}
