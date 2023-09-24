package com.dev.torhugo.hub_payments.resource;

import com.dev.torhugo.hub_payments.lib.data.dto.UserRequestDTO;
import com.dev.torhugo.hub_payments.lib.data.dto.UserResponseDTO;
import com.dev.torhugo.hub_payments.service.UserService;
import com.dev.torhugo.hub_payments.util.HubResource;
import com.dev.torhugo.hub_payments.util.HubResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController implements HubResource {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HubResponse<UserResponseDTO>> registerToUser(
            @RequestBody final UserRequestDTO user
    ) {
        return returnSuccess(userService.register(user));
    }

    @CrossOrigin(origins = "http://localhost:5500")
    @GetMapping("/retrieve/{userId}")
    public ResponseEntity<HubResponse<UserResponseDTO>> retrieveToUser(
            @PathVariable final Long userId
    ) {
        return returnSuccess(userService.retrieveById(userId));
    }
}
