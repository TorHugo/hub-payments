package com.dev.torhugo.hub_payments.lib.data.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseModel {
    private LocalDateTime creatAt;
    private LocalDateTime updateAt;
}
