package com.dev.torhugo.hub_payments.repository;

import com.dev.torhugo.hub_payments.lib.data.domain.UserModel;

public interface UserRepository {
    /**
     * Retrieve by email user model.
     *
     * @param email the email
     * @return the user model
     */
    UserModel retrieveByEmail(final String email);

    /**
     * Save.
     *
     * @param userModel the user model
     */
    void save(final UserModel userModel);

    /**
     * Retrieve by id user model.
     *
     * @param userId the user id
     * @return the user model
     */
    UserModel retrieveById(final Long userId);
}
