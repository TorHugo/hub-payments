package com.dev.torhugo.hub_payments.resource;

import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentRequestRefundDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.payment.PaymentResponseDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.refund.PaymentResponseRefundDTO;
import com.dev.torhugo.hub_payments.service.PaymentService;
import com.dev.torhugo.hub_payments.util.HubResource;
import com.dev.torhugo.hub_payments.util.HubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController implements HubResource {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<HubResponse<PaymentResponseDTO>> createPayment(
            @RequestBody final PaymentRequestDTO paymentRequest
    ) {
        return returnSuccess(paymentService.createPayment(paymentRequest));
    }

    @GetMapping("/retrieve/{paymentId}")
    public ResponseEntity<HubResponse<PaymentResponseDTO>> retrievePayment(
            @PathVariable final String paymentId
    ){
        return returnSuccess(paymentService.retrieveById(paymentId));
    }

    @PostMapping("/refund")
    public ResponseEntity<HubResponse<PaymentResponseRefundDTO>> refundPayment (
            @RequestBody final PaymentRequestRefundDTO refund
    ){
        return returnSuccess(paymentService.refundPayment(refund));
    }
}
