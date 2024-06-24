package com.project.webHooks.parser;

import com.project.webHooks.dto.WebPayloadDataDTO;
import com.project.webHooks.entity.WebhookEvent;

public interface ProviderPayloadParser {
    WebhookEvent parsePayload(String provider, WebPayloadDataDTO webPayloadDataDTO);
}
