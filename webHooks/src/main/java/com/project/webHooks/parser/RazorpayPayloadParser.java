package com.project.webHooks.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.webHooks.dto.WebPayloadDataDTO;
import com.project.webHooks.entity.RazorpayEvent;
import com.project.webHooks.entity.WebhookEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RazorpayPayloadParser implements ProviderPayloadParser {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public WebhookEvent parsePayload(String provider, WebPayloadDataDTO webPayloadDataDTO) {
        Object payload = webPayloadDataDTO.getPayload();
        RazorpayEvent razorpayEvent = objectMapper.convertValue(payload, RazorpayEvent.class);

        WebhookEvent webhookEvent = new WebhookEvent();
        webhookEvent.setProvider(provider);
        webhookEvent.setEventType(razorpayEvent.getEvent());
        webhookEvent.setProviderTenantId(1L);
        try {
            webhookEvent.setPayload(objectMapper.writeValueAsString(razorpayEvent));
        } catch (Exception e) {
            throw new RuntimeException("Error converting payload to JSON string", e);
        }
        webhookEvent.setStatus("PENDING");
        webhookEvent.setPartitionKey(razorpayEvent.getEvent());

        return webhookEvent;
    }
}
