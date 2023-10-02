package com.dev.torhugo.hub_payments.lib.data.dto.tokenize;

import com.dev.torhugo.hub_payments.lib.data.dto.CreditCardDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CreditCardHolderInfoDTO;
import lombok.Builder;

@Builder
public record TokenizeRequestDTO(
        String customer,
        Long storeId,
        CreditCardDTO creditCard,
        CreditCardHolderInfoDTO creditCardHolderInfo
) {
}
