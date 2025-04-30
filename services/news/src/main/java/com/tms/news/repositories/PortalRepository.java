package com.tms.news.repositories;

import com.tms.news.core.models.entities.Portal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PortalRepository extends JpaRepository<Portal, UUID> {
}
