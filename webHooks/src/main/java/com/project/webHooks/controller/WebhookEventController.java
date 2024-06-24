package com.project.webHooks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.webHooks.dto.WebPayloadDataDTO;
import com.project.webHooks.entity.WebhookEvent;
import com.project.webHooks.factory.PayloadParserFactory;
import com.project.webHooks.service.WebhookEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/webhook")
public class WebhookEventController {

    private final WebhookEventService webhookEventService;
    private final PayloadParserFactory payloadParserFactory;
    private final ObjectMapper objectMapper;

    @Autowired
    public WebhookEventController(WebhookEventService webhookEventService, PayloadParserFactory payloadParserFactory, ObjectMapper objectMapper) {
        this.webhookEventService = webhookEventService;
        this.payloadParserFactory = payloadParserFactory;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/{provider}/save")
    public WebhookEvent saveEvent(@PathVariable String provider, @RequestBody Object webPayloadData) {
        WebPayloadDataDTO webPayloadDataDTO = new WebPayloadDataDTO(webPayloadData);
        WebhookEvent webhookEvent = payloadParserFactory.getParser(provider).parsePayload(provider, webPayloadDataDTO);
        return webhookEventService.saveEvent(webhookEvent);
    }

    @GetMapping("/filter")
    public Iterable<WebhookEvent> filterEvent(@RequestParam(required = false) String provider,
                                              @RequestParam(required = false) String eventType,
                                              @RequestParam(required = false) String status) {
        return webhookEventService.filterEvent(provider, eventType, status);
    }

    @PutMapping("/update-status/{eventId}")
    public WebhookEvent updateEventStatus(@PathVariable UUID eventId, @RequestParam String status) {
        return webhookEventService.updateEventStatus(eventId, status);
    }
}
