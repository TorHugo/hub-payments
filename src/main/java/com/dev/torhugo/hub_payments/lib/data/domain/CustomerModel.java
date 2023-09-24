package com.dev.torhugo.hub_payments.lib.data.domain;

import com.dev.torhugo.hub_payments.lib.data.enumerator.FormPaymentEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_tb")
public class CustomerModel extends BaseModel{

    @Id
    private String customerId;
    private Long userId;
    private Long formPaymentId;
    private Long creditCardId;
    private Boolean inActive;
}
