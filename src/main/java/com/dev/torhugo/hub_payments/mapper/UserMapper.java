package com.dev.torhugo.hub_payments.mapper;

import com.dev.torhugo.hub_payments.lib.data.domain.UserModel;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserModel requestToModel(final UserRequestDTO user) {
        UserModel userModel = new UserModel();
        userModel.setEmail(user.email());
        userModel.setPassword(user.password());
        userModel.setCpfOrCnpj(user.cpfOrCnpj());
        userModel.setFirstName(user.firstName());
        userModel.setLastName(user.lastName());
        userModel.setPhone(user.phone());
        return userModel;
    }

    public UserResponseDTO modelToResponse(final UserModel userModel,
                                           final Long userId,
                                           final CustomerResponseDTO customerResponse) {
        return UserResponseDTO.builder()
                .userId(userId)
                .email(userModel.getEmail())
                .cpfOrCnpj(userModel.getCpfOrCnpj())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .phone(userModel.getPhone())
                .customerResponse(customerResponse)
                .build();
    }
}
