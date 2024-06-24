package com.project.webHooks.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.webHooks.util.PayloadJsonSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class WebhookEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID eventId;
    private String eventType;
    private String provider;
    private Long providerTenantId;

    @JsonSerialize(using = PayloadJsonSerializer.class)
    private String payload;

    private String status;
    private String partitionKey;

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Long getProviderTenantId() {
        return providerTenantId;
    }

    public void setProviderTenantId(Long providerTenantId) {
        this.providerTenantId = providerTenantId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }
}
