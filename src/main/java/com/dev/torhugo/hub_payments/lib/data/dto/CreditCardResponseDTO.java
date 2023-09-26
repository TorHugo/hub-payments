package com.dev.torhugo.hub_payments.lib.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreditCardResponseDTO(
        @JsonProperty("credit_card_id")
        Long creditCardId,
        @JsonProperty("flag_credit_card")
        String flagCreditCard,
        @JsonProperty("token_credit_card")
        String tokenCreditCard,
        @JsonProperty("number_credit_card")
        String numberCreditCard,
        @JsonProperty("creat_at")
        @JsonFormat(pattern = "yyyy-MM-dd@HH:mm")
        LocalDateTime creatAt
) {
}
