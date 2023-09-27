package com.dev.torhugo.hub_payments.mapper;

import com.dev.torhugo.hub_payments.lib.data.domain.CreditCardModel;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.enumerator.FlagCardEnum;
import org.springframework.stereotype.Component;

@Component
public class CreditCardMapper {
    public CreditCardModel mappingToModel(final TokenizeResponseDTO response) {
        CreditCardModel model = new CreditCardModel();
        model.setCardToken(response.creditCardToken());
        model.setFlagCardId(FlagCardEnum.fromName(
                response.creditCardBrand()).getFlagCardId());
        model.setNumber(response.creditCardNumber());
        return model;
    }
}
