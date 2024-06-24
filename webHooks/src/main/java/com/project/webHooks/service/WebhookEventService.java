package com.project.webHooks.service;

import com.project.webHooks.entity.WebhookEvent;
import com.project.webHooks.repository.WebhookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class WebhookEventService {

    private final WebhookRepository webhookRepository;

    @Autowired
    public WebhookEventService(WebhookRepository webhookRepository) {
        this.webhookRepository = webhookRepository;
    }

    public WebhookEvent saveEvent(WebhookEvent event) {
        event.setEventId(UUID.randomUUID());
        return webhookRepository.save(event);
    }

    public List<WebhookEvent> filterEvent(String provider, String eventType, String status) {
        return webhookRepository.filterEvents(provider, eventType, status);
    }

    public WebhookEvent updateEventStatus(UUID eventId, String status) {
        WebhookEvent event = webhookRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        event.setStatus(status);
        return webhookRepository.save(event);
    }
}
