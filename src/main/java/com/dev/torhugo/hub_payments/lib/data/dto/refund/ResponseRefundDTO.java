package com.dev.torhugo.hub_payments.lib.data.dto.refund;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ResponseRefundDTO(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dateCreated,
        String status,
        BigDecimal value
) {
}
