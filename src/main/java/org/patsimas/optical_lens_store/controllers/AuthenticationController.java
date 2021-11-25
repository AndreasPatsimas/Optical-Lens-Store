package org.patsimas.optical_lens_store.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.dto.authenticate.AuthenticationRequest;
import org.patsimas.optical_lens_store.dto.authenticate.AuthenticationResponse;
import org.patsimas.optical_lens_store.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
