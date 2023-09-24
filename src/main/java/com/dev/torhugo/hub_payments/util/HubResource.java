package com.dev.torhugo.hub_payments.util;

import org.springframework.http.ResponseEntity;

import java.util.Objects;

public interface HubResource {

    default <T>ResponseEntity<HubResponse<T>> returnResponse(final HubHttpEnum hubHttpEnum, final T response){
        return ResponseEntity.status(hubHttpEnum.getHttpStatus()).body(new HubResponse(hubHttpEnum, response));
    }

    default <T>ResponseEntity<HubResponse<T>> returnSuccess(final T response){
        return Objects.nonNull(response) ? this.returnResponse(HubHttpEnum.HTTP_200, response) : this.returnNoValue();
    }

    default <T> ResponseEntity<HubResponse<T>> returnNoValue(){
        return this.returnResponse(HubHttpEnum.HTTP_204, null);
    }
}
