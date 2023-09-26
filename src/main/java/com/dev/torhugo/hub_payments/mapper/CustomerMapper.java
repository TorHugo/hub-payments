package com.dev.torhugo.hub_payments.mapper;

import com.dev.torhugo.hub_payments.lib.data.domain.CreditCardModel;
import com.dev.torhugo.hub_payments.lib.data.domain.CustomerModel;
import com.dev.torhugo.hub_payments.lib.data.domain.UserModel;
import com.dev.torhugo.hub_payments.lib.data.dto.CreditCardResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerResponse;
import com.dev.torhugo.hub_payments.lib.data.enumerator.FlagCardEnum;
import com.dev.torhugo.hub_payments.lib.data.enumerator.FormPaymentEnum;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerMapper {
    public CustomerResponseDTO modelToResponse(final CustomerModel customer, final String customerId) {
        return CustomerResponseDTO.builder()
                .customerId(customerId)
                .formPayment(Objects.isNull(customer.getFormPaymentId()) ? null :
                        FormPaymentEnum.parseFromId(customer.getFormPaymentId()).getNameFormPayment())
                .inActive(customer.getInActive())
                .creatAt(customer.getCreatAt())
                .updateAt(customer.getUpdateAt())
                .build();
    }

    public CustomerRequestDTO userToRequest(final UserModel userModel, final Long userId) {
        return CustomerRequestDTO.builder()
                .userId(userId)
                .cpfOrCnpj(userModel.getCpfOrCnpj())
                .fullName(userModel.getName())
                .build();
    }

    public CustomerModel mappingToModel(final PaymentRegisterCustomerResponse savedClient,
                                        final CustomerRequestDTO customerDTO) {
        CustomerModel model = new CustomerModel();
        model.setCustomerId(savedClient.id());
        model.setUserId(customerDTO.userId());
        model.setInActive(Boolean.TRUE);
        return model;
    }

    public CustomerResponseDTO mappingToResponse(final CustomerModel customer,
                                                 final CreditCardModel creditCard) {
        return CustomerResponseDTO.builder()
                .customerId(customer.getCustomerId())
                .creditCardResponseDTO(CreditCardResponseDTO.builder()
                        .creditCardId(creditCard.getCreditCardId())
                        .flagCreditCard(FlagCardEnum.fromId(creditCard.getFlagCardId()).getNameFlagCard())
                        .tokenCreditCard(creditCard.getCardToken())
                        .numberCreditCard(creditCard.getNumber())
                        .creatAt(creditCard.getCreatAt())
                        .build())
                .formPayment(Objects.isNull(customer.getFormPaymentId()) ? null :
                        FormPaymentEnum.parseFromId(customer.getFormPaymentId()).getNameFormPayment())
                .inActive(customer.getInActive())
                .creatAt(customer.getCreatAt())
                .build();
    }
}
