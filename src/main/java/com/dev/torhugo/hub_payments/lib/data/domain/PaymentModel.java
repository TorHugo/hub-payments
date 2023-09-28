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
@Table(name = "customer_tb")
public class PaymentModel extends BaseModel{

    @Id
    private String paymentId;
    private String customerId;
    private BigDecimal value;
    private String description;
    private LocalDate firstDueDate;
    private Long formPaymentId;
}
