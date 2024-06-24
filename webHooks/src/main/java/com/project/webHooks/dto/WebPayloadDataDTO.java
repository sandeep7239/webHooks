package com.project.webHooks.dto;

public class WebPayloadDataDTO {
    private Object payload;

    public WebPayloadDataDTO(Object payload) {
        this.payload = payload;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
