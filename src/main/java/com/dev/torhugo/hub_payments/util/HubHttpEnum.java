package com.dev.torhugo.hub_payments.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@PropertySource("classpath:messages.properties")
public enum HubHttpEnum {

    HTTP_200(HttpStatus.OK, "response.200"),
    HTTP_201(HttpStatus.CREATED, "response.201"),
    HTTP_204(HttpStatus.OK, "response.204"),
    HTTP_400(HttpStatus.BAD_REQUEST, "response.400"),
    HTTP_404(HttpStatus.NOT_FOUND, "response.404"),
    HTTP_500(HttpStatus.INTERNAL_SERVER_ERROR, "response.500");

    private final HttpStatus httpStatus;
    private final String message;
}
