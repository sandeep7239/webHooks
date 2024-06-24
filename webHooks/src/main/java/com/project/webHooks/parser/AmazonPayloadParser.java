package com.project.webHooks.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.webHooks.dto.WebPayloadDataDTO;
import com.project.webHooks.entity.AmazonEvent;
import com.project.webHooks.entity.WebhookEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmazonPayloadParser implements ProviderPayloadParser {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public WebhookEvent parsePayload(String provider, WebPayloadDataDTO webPayloadDataDTO) {
        Object payload = webPayloadDataDTO.getPayload();
        AmazonEvent amazonEvent = objectMapper.convertValue(payload, AmazonEvent.class);

        WebhookEvent webhookEvent = new WebhookEvent();
        webhookEvent.setProvider(provider);
        webhookEvent.setEventType(amazonEvent.getEvent());
        webhookEvent.setProviderTenantId(1L);
        try {
            webhookEvent.setPayload(objectMapper.writeValueAsString(amazonEvent));
        } catch (Exception e) {
            throw new RuntimeException("Error converting payload to JSON string", e);
        }
        webhookEvent.setStatus("PENDING");
        webhookEvent.setPartitionKey(amazonEvent.getEvent());

        return webhookEvent;
    }
}
