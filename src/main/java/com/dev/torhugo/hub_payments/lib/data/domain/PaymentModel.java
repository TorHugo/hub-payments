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
@Table(name = "payment_tb")
public class PaymentModel extends BaseModel{

    @Id
    private String paymentId;
    private String customerId;
    private Long storeId;
    private String externalReference;
    private BigDecimal value;
    private String status;
    private String description;
    private LocalDate dueDate;
    private LocalDate dateCreated;
    private Long formPaymentId;
}
