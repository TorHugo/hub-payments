package com.dev.torhugo.hub_payments.ma;

import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public TokenizeRequestDTO mapperTokenize(final PaymentRequest paymentRequest) {
        return TokenizeRequestDTO.builder()
                .creditCard(paymentRequest.creditCard())
                .creditCardHolderInfo(paymentRequest.creditCardHolderInfo())
                .build();
    }
}
