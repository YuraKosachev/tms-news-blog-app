package com.tms.dataprovider.repositories;

import com.tms.dataprovider.core.models.entities.ProcessedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProcessedDataRepository extends JpaRepository<ProcessedData, UUID> {

    @Query("select p from ProcessedData p where p.sentAt is NULL")
    List<ProcessedData> findAllNotSent();
}
