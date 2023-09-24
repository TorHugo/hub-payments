package com.dev.torhugo.hub_payments.lib.data.enumerator;

import com.dev.torhugo.hub_payments.lib.exception.impl.DataBaseException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum FlagCardEnum {
    VISA(1L, "flag.card.name.visa"),
    MASTER_CARD(2L, "flag.card.name.master_card"),
    ELO(3L, "flag.card.name.elo");

    private final Long flagCardId;
    private final String nameFlagCard;

    public FlagCardEnum parseFromName(final String nameFlagCard){
        return Arrays.stream(values()).filter(name -> Objects.equals(name.nameFlagCard, nameFlagCard))
                .findFirst().orElseThrow(() -> new DataBaseException("Entity not found! ", nameFlagCard));
    }
}
