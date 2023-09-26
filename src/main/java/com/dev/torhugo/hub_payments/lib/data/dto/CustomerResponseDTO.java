package com.dev.torhugo.hub_payments.lib.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomerResponseDTO(
        @JsonProperty("customer_id")
        String customerId,
        @JsonProperty("credit_card_info")
        CreditCardResponseDTO creditCardResponseDTO,
        @JsonProperty("form_payment")
        String formPayment,
        @JsonProperty("in_active")
        Boolean inActive,
        @JsonProperty("creat_at")
        @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
        LocalDateTime creatAt,
        @JsonProperty("update_at")
        @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
        LocalDateTime updateAt
) {
}
