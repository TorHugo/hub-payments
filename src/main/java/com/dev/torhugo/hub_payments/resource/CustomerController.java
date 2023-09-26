package com.dev.torhugo.hub_payments.resource;

import com.dev.torhugo.hub_payments.lib.data.dto.CustomerResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.tokenize.TokenizeRequestDTO;
import com.dev.torhugo.hub_payments.service.CustomerService;
import com.dev.torhugo.hub_payments.util.HubResource;
import com.dev.torhugo.hub_payments.util.HubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController implements HubResource {

    private final CustomerService customerService;

    @PostMapping("/credit-token")
    public ResponseEntity<HubResponse<CustomerResponseDTO>> tokenizationCreditCard(
            @RequestBody final TokenizeRequestDTO tokenization
    ){
        return returnSuccess(customerService.tokenization(tokenization));
    }
}
