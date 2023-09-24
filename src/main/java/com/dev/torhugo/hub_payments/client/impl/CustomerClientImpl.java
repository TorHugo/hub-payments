package com.dev.torhugo.hub_payments.client.impl;

import com.dev.torhugo.hub_payments.client.CustomerClient;
import com.dev.torhugo.hub_payments.lib.data.dto.CustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRegisterCustomerResponse;
import com.dev.torhugo.hub_payments.lib.exception.impl.ResourceNotFoundException;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerClientImpl implements CustomerClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson;

    @Value("${request.host.value}")
    private String host;

    @Value("${request.endpoint.client.creat_client.value}")
    private String endpointCreateClient;

    @Value("${access.token.value}")
    private String accessToken;

    public PaymentRegisterCustomerResponse register(final PaymentRegisterCustomerRequest request){
        final String url = host.concat(endpointCreateClient);
        final String jsonRequest = gson.toJson(request);

        try {
            log.info("[-] - Request to endpoint: [{}].", endpointCreateClient);
            final ResponseEntity<PaymentRegisterCustomerResponse> response =
                    restTemplate.exchange(url, HttpMethod.POST,
                            createRequest(jsonRequest, createHeader(accessToken)), PaymentRegisterCustomerResponse.class);
            return response.getBody();
        } catch (final HttpClientErrorException.Unauthorized |
                       HttpClientErrorException.BadRequest |
                       HttpClientErrorException.NotFound exception) {
            exception.printStackTrace();
            throw new ResourceNotFoundException("exception.payment.create.client");
        }
    }

    private <T> HttpEntity<String> createRequest(final T body,
                                                 final HttpHeaders token) {
        return new HttpEntity<>(body.toString(), token);
    }

    private HttpHeaders createHeader(final String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("access_token", accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
