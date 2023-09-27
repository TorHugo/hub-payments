package com.dev.torhugo.hub_payments.client.impl;

import com.dev.torhugo.hub_payments.client.CustomerClient;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.costumer.RegisterCustomerRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.costumer.RegisterCustomerResponse;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeResponseDTO;
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

    @Value("${request.endpoint.credit_card.tokenize.value}")
    private String endpointTokenize;

    @Value("${access.token.value}")
    private String accessToken;

    public RegisterCustomerResponse register(final RegisterCustomerRequest request){
        final String url = host.concat(endpointCreateClient);
        final String jsonRequest = gson.toJson(request);

        try {
            log.info("[-] - Request to endpoint: [{}].", endpointCreateClient);
            final ResponseEntity<RegisterCustomerResponse> response =
                    restTemplate.exchange(url, HttpMethod.POST,
                            createRequest(jsonRequest, createHeader(accessToken)), RegisterCustomerResponse.class);
            return response.getBody();
        } catch (final HttpClientErrorException.Unauthorized |
                       HttpClientErrorException.BadRequest |
                       HttpClientErrorException.NotFound exception) {
            exception.printStackTrace();
            throw new ResourceNotFoundException("exception.payment.create.client");
        }
    }

    @Override
    public TokenizeResponseDTO tokenize(final TokenizeRequestDTO tokenization) {
        final String url = host.concat(endpointTokenize);
        final String jsonRequest = gson.toJson(tokenization);

        try {
            log.info("[-] - Request to endpoint: [{}].", endpointCreateClient);
            final ResponseEntity<TokenizeResponseDTO> response =
                    restTemplate.exchange(url, HttpMethod.POST,
                            createRequest(jsonRequest, createHeader(accessToken)), TokenizeResponseDTO.class);
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
