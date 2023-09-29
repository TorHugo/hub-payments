package com.dev.torhugo.hub_payments.lib.data.dto.payment;




import com.dev.torhugo.hub_payments.lib.data.dto.CreditCardDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CreditCardHolderInfoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record PaymentRequestDTO(
        String customer,
        @JsonProperty("billing_type")
        String billingType,
        BigDecimal value,
        @JsonProperty("due_date")
        LocalDate dueDate,
        String description,
        @JsonProperty("external_reference")
        String externalReference,
        @JsonProperty("credit_card_token")
        String creditCardToken,
        @JsonProperty("credit_card")
        CreditCardDTO creditCard,
        @JsonProperty("credit_card_holder_info")
        CreditCardHolderInfoDTO creditCardHolderInfo
) {
}
