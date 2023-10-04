package com.dev.torhugo.hub_payments.lib.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refund_tb")
public class RefundModel extends BaseModel {

    @Id
    private Long refundId;
    private String paymentId;
    private BigDecimal value;
    private String status;
    private String descriptionRefund;
    private LocalDate dateCreated;
}
