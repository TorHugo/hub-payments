package com.dev.torhugo.hub_payments.lib.data.domain;

import com.dev.torhugo.hub_payments.lib.data.enumerator.FlagCardEnum;
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
@Table(name = "credit_card_tb")
public class CreditCardModel extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long creditCardId;
    private Long flagCardId;
    private String number;
    private String cardToken;

}
