package com.dev.torhugo.hub_payments.mapper;

import com.dev.torhugo.hub_payments.lib.data.domain.PaymentModel;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentReturnDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.enumerator.FormPaymentEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.dev.torhugo.hub_payments.util.GenericUtil.genericHost;

@Component
public class PaymentMapper {

    @Value("${request.host.value}")
    private String host;

    @Value("${request.endpoint.payment.value}")
    private String endpointPayment;

    public TokenizeRequestDTO mapperTokenize(final PaymentRequestDTO paymentRequest) {
        return TokenizeRequestDTO.builder()
                .creditCard(paymentRequest.creditCard())
                .creditCardHolderInfo(paymentRequest.creditCardHolderInfo())
                .build();
    }

    public PaymentDTO mapperToRequest(final PaymentRequestDTO paymentRequest,
                                      final String tokenization) {
        return PaymentDTO.builder()
                .customer(paymentRequest.customer())
                .billingType(paymentRequest.billingType())
                .value(paymentRequest.value())
                .dueDate(paymentRequest.dueDate())
                .description(paymentRequest.description())
                .creditCardToken(Objects.nonNull(paymentRequest.creditCardToken()) ? paymentRequest.creditCardToken() : tokenization)
                .build();
    }

    public PaymentModel mapperToModel(final PaymentReturnDTO returnRequest,
                                      final PaymentRequestDTO paymentRequest) {
        return PaymentModel.builder()
                .paymentId(returnRequest.id())
                .customerId(paymentRequest.customer())
                .storeId(paymentRequest.storeId())
                .externalReference(paymentRequest.externalReference())
                .value(paymentRequest.value())
                .status(returnRequest.status())
                .description(paymentRequest.description())
                .dueDate(paymentRequest.dueDate())
                .dateCreated(returnRequest.dateCreated())
                .formPaymentId(FormPaymentEnum.parseFromName(paymentRequest.billingType()).getFormPaymentId())
                .build();
    }

    public PaymentResponseDTO mapperToResponse(final PaymentModel paymentModel) {
        return PaymentResponseDTO.builder()
                .paymentId(paymentModel.getPaymentId())
                .customerId(paymentModel.getCustomerId())
                .storeId(paymentModel.getStoreId())
                .value(paymentModel.getValue())
                .externalReference(paymentModel.getExternalReference())
                .dueDate(paymentModel.getDueDate())
                .dateCreated(paymentModel.getDateCreated())
                .billingType(FormPaymentEnum.parseFromId(paymentModel.getFormPaymentId()).getNameFormPayment())
                .status(paymentModel.getStatus())
                .href(buildToHref(paymentModel.getPaymentId()))
                .build();
    }

    private String buildToHref(final String paymentId) {
        return genericHost(host, endpointPayment).concat("/").concat(paymentId);
    }
}
