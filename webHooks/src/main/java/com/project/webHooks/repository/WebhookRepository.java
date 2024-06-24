package com.project.webHooks.repository;

import com.project.webHooks.entity.WebhookEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface WebhookRepository extends JpaRepository<WebhookEvent, UUID> {

    @Query("SELECT e FROM WebhookEvent e WHERE "
            + "(:provider IS NULL OR e.provider = :provider) AND "
            + "(:eventType IS NULL OR e.eventType = :eventType) AND "
            + "(:status IS NULL OR e.status = :status)")
    List<WebhookEvent> filterEvents(@Param("provider") String provider,
                                    @Param("eventType") String eventType,
                                    @Param("status") String status);
}
