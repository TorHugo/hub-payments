package com.dev.torhugo.hub_payments.lib.data.enumerator;

import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

import static com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum.CONTACT_SUPPORT;
import static com.dev.torhugo.hub_payments.lib.data.enumerator.MessageEnum.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum FormPaymentEnum {
    CREDIT_CARD(1L, "CREDIT_CARD","Representation form payment of CreditCard.");

    private final Long formPaymentId;
    private final String nameFormPayment;
    private final String descriptionPayment;

    public static FormPaymentEnum parseFromId(final Long formPaymentId) {
        return Arrays.stream(values()).filter(formPaymentEnum -> Objects.equals(formPaymentEnum.getFormPaymentId(), formPaymentId))
                .findFirst().orElseThrow(() -> new DataBaseException(NOT_FOUND.getDescription("FormPaymentEnum", formPaymentId.toString()), CONTACT_SUPPORT.getDescription(), null, null));
    }

    public static FormPaymentEnum parseFromName(final String nameFormPayment){
        return Arrays.stream(values()).filter(name -> Objects.equals(name.nameFormPayment, nameFormPayment))
                .findFirst().orElseThrow(() -> new DataBaseException(NOT_FOUND.getDescription("FormPaymentEnum", nameFormPayment), CONTACT_SUPPORT.getDescription(), null, null));
    }
}
