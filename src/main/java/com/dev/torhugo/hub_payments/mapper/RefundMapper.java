package com.dev.torhugo.hub_payments.mapper;

import com.dev.torhugo.hub_payments.lib.data.domain.RefundModel;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentRequestRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentResponseRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.ResponseRefundDTO;
import com.dev.torhugo.hub_payments.lib.exception.impl.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.dev.torhugo.hub_payments.util.ConstraintUtil.MESSAGE_UNDEFINED;

@Component
public class RefundMapper {
    public RefundModel mapperToModel(final PaymentRequestRefundDTO refund,
                                     final PaymentRefundDTO responseToRefund) {
        return RefundModel.builder()
                .paymentId(responseToRefund.id())
                .value(mappingToValue(responseToRefund.refunds()))
                .status(mappingToStatus(responseToRefund.refunds()))
                .descriptionRefund(refund.description())
                .dateCreated(mappingToDateCreated(responseToRefund.refunds()))
                .build();
    }

    private LocalDate mappingToDateCreated(final List<ResponseRefundDTO> refunds) {
        return LocalDate.from(refunds.stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_UNDEFINED)).dateCreated());
    }

    private String mappingToStatus(final List<ResponseRefundDTO> refunds) {
        return refunds.stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_UNDEFINED)).status();
    }

    private BigDecimal mappingToValue(final List<ResponseRefundDTO> refunds) {
        return refunds.stream().findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No value present!.")).value();
    }

    public PaymentResponseRefundDTO mapperToResponse(final PaymentRequestRefundDTO refund,
                                                     final PaymentRefundDTO responseToRefund) {
        return PaymentResponseRefundDTO.builder()
                .paymentId(refund.paymentId())
                .statusPayment(responseToRefund.status())
                .totalValue(responseToRefund.value())
                .refunds(responseToRefund.refunds())
                .build();
    }
}
