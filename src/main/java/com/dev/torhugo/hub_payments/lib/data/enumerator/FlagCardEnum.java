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
public enum FlagCardEnum {
    VISA(1L, "VISA"),
    MASTER_CARD(2L, "MASTERCARD"),
    ELO(3L, "ELO");

    private final Long flagCardId;
    private final String nameFlagCard;

    public static FlagCardEnum fromName(final String nameFlagCard){
        return Arrays.stream(values()).filter(name -> Objects.equals(name.nameFlagCard, nameFlagCard))
                .findFirst().orElseThrow(() -> new DataBaseException(NOT_FOUND.getDescription("FlagCardEnum", nameFlagCard), CONTACT_SUPPORT.getDescription(), null, null));
    }

    public static FlagCardEnum fromId(final Long flagCardId){
        return Arrays.stream(values()).filter(id -> Objects.equals(id.flagCardId, flagCardId))
                .findFirst().orElseThrow(() -> new DataBaseException(NOT_FOUND.getDescription("FlagCardEnum", flagCardId.toString()), CONTACT_SUPPORT.getDescription(), null, null));
    }
}
