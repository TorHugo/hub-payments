package com.dev.torhugo.hub_payments.repository;

import com.dev.torhugo.hub_payments.lib.data.domain.CreditCardModel;

public interface CreditCardRepository {
    /**
     * Save.
     *
     * @param creditCardModel the credit card model
     */
    void save(final CreditCardModel creditCardModel);

    /**
     * Retrieve by token credit card model.
     *
     * @param token the token
     * @return the credit card model
     */
    CreditCardModel retrieveByToken(final String token);
}
