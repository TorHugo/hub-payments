package com.dev.torhugo.hub_payments.service;

import com.dev.torhugo.hub_payments.lib.data.dto.UserRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserResponseDTO;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Register user response dto.
     *
     * @param user the user
     * @return the {@link UserResponseDTO}
     */
    UserResponseDTO register(final UserRequestDTO user);

    /**
     * Retrieve by id user response dto.
     *
     * @param userId the user id
     * @return the user response dto
     */
    UserResponseDTO retrieveById(final Long userId);
}
