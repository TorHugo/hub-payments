package com.dev.torhugo.hub_payments.resource;

import com.dev.torhugo.hub_payments.lib.data.dto.UserRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserResponseDTO;
import com.dev.torhugo.hub_payments.util.HubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

//    @PostMapping()
//    public ResponseEntity<HubResponse<UserResponseDTO>> registerCustomer(
//            @RequestBody final CustomerResponseDTO customer
//    ) {
//        return returnSuccess(userService.register(user));
//    }
}
