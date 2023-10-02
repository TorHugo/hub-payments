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
     * Retrieve by store id and user id user response dto.
     *
     * @param storeId the store id
     * @param userId  the user id
     * @return the user response dto
     */
    UserResponseDTO retrieveByStoreIdAndUserId(final Long storeId,
                                               final String userId);
}
