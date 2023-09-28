package com.dev.torhugo.hub_payments.resource;

import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRequest;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentResponse;
import com.dev.torhugo.hub_payments.service.PaymentService;
import com.dev.torhugo.hub_payments.util.HubResource;
import com.dev.torhugo.hub_payments.util.HubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController implements HubResource {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<HubResponse<PaymentResponse>> createPayment(
            @RequestBody final PaymentRequest paymentRequest
    ) {
        return returnSuccess(paymentService.createPayment(paymentRequest));
    }
}
