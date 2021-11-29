package org.patsimas.optical_lens_store.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.dto.authenticate.*;
import org.patsimas.optical_lens_store.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping(value = "/authenticate")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @ApiOperation(value = "Request for authentication", response = AuthenticationResponse.class)
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequest authenticationRequest){

        log.info("Authenticate user {}", authenticationRequest.getUsername());

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ChangePasswordResponse changePassword(@RequestBody ChangePasswordRequest request) {

        log.info("Change password process started for user {}", request.getUsername());

        return authenticationService.changePassword(request);
    }

    @PostMapping(value = "/forgotPassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ForgotPasswordResponse forgotPassword(@RequestBody ForgotPasswordRequest request) {

        log.info("Forgot password process started fo user{} ", request.getUsername());

        return authenticationService.forgotPassword(request);
    }
}
