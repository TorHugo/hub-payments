package com.dev.torhugo.hub_payments.client.impl;

import com.dev.torhugo.hub_payments.client.CustomerClient;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentRequestRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentReturnDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.costumer.RegisterCustomerRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.costumer.RegisterCustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeResponseDTO;
import com.dev.torhugo.hub_payments.lib.exception.impl.ResourceNotFoundException;
import com.dev.torhugo.hub_payments.util.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static com.dev.torhugo.hub_payments.util.GenericUtil.genericHost;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomerClientImpl implements CustomerClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    @Value("${request.host.value}")
    private String host;

    @Value("${request.endpoint.client.creat_client.value}")
    private String endpointCreateClient;

    @Value("${request.endpoint.credit_card.tokenize.value}")
    private String endpointTokenize;

    @Value("${request.endpoint.payment.value}")
    private String endpointPayment;

    @Value("${request.endpoint.refund.value}")
    private String endpointRefund;

    @Value("${access.token.value}")
    private String accessToken;

    public RegisterCustomerResponseDTO register(final RegisterCustomerRequestDTO request){
        final String url = genericHost(host, endpointCreateClient);
        final String jsonRequest = gson.toJson(request);

        try {
            log.info("[-] - Request to endpoint: [{}].", endpointCreateClient);
            final ResponseEntity<RegisterCustomerResponseDTO> response =
                    restTemplate.exchange(url, HttpMethod.POST,
                            createRequest(jsonRequest, createHeader(accessToken)), RegisterCustomerResponseDTO.class);
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
        final String url =  genericHost(host, endpointTokenize);
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

    @Override
    public PaymentReturnDTO payment(final PaymentDTO request) {
        final String url = genericHost(host, endpointPayment);
        final String jsonRequest = gson.toJson(request);

        try {
            log.info("[-] - Request to endpoint: [{}].", endpointPayment);
            final ResponseEntity<PaymentReturnDTO> response =
                    restTemplate.exchange(url, HttpMethod.POST,
                            createRequest(jsonRequest, createHeader(accessToken)), PaymentReturnDTO.class);
            return response.getBody();
        } catch (final HttpClientErrorException.Unauthorized |
                       HttpClientErrorException.BadRequest |
                       HttpClientErrorException.NotFound exception) {
            exception.printStackTrace();
            throw new ResourceNotFoundException(exception.getMessage());
        }
    }

    @Override
    public PaymentRefundDTO refund(final PaymentRequestRefundDTO refund) {
        final String url = uriBuilder(refund);
        final String jsonRequest = gson.toJson(refund);

        try {
            log.info("[-] - Request to endpoint: [{}].", endpointPayment);
            final ResponseEntity<PaymentRefundDTO> response =
                    restTemplate.exchange(url, HttpMethod.POST,
                            createRequest(jsonRequest, createHeader(accessToken)), PaymentRefundDTO.class);
            return response.getBody();
        } catch (final HttpClientErrorException.Unauthorized |
                       HttpClientErrorException.BadRequest |
                       HttpClientErrorException.NotFound exception) {
            exception.printStackTrace();
            throw new ResourceNotFoundException("exception.payment.create.client");
        }
    }

    private String uriBuilder(final PaymentRequestRefundDTO refund) {
        return host
                .concat(endpointPayment)
                .concat("/")
                .concat(refund.paymentId())
                .concat(endpointRefund);
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
