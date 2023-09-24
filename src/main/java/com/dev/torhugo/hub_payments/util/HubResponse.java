package com.dev.torhugo.hub_payments.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubResponse <T> {
    private LocalDateTime timeStamp;
    private Integer numberStatus;
    private transient T response;
    private String message;

    public HubResponse(final HubHttpEnum hubHttpEnum, final T data){
        this.timeStamp = LocalDateTime.now();
        this.numberStatus = hubHttpEnum.getHttpStatus().value();
        this.message = hubHttpEnum.getMessage();
        this.response = data;
    }
}
