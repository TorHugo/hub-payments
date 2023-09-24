package com.dev.torhugo.hub_payments.repository;

import com.dev.torhugo.hub_payments.lib.data.domain.UserModel;

public interface UserRepository {
    UserModel retrieveByEmail(final String email);

    void save(final UserModel userModel);
}
