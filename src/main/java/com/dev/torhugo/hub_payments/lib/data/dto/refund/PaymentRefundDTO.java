package com.dev.torhugo.hub_payments.lib.data.dto.refund;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PaymentRefundDTO(
        String id,
        String customer,
        LocalDate dateCreated,
        BigDecimal value,
        BigDecimal netValue,
        String billingType,
        String status,
        List<ResponseRefundDTO> refunds
)  {
}
