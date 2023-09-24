package com.dev.torhugo.hub_payments.service;

import com.dev.torhugo.hub_payments.lib.data.dto.UserRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserResponseDTO;

public interface UserService {

    /**
     * Register user response dto.
     *
     * @param user the user
     * @return the {@link UserResponseDTO}
     */
    UserResponseDTO register(final UserRequestDTO user);
}
