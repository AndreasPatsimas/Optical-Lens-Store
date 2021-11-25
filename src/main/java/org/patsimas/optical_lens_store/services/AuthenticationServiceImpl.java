package org.patsimas.optical_lens_store.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.config.security.JwtTokenProvider;
import org.patsimas.optical_lens_store.dto.authenticate.AuthenticationRequest;
import org.patsimas.optical_lens_store.dto.authenticate.AuthenticationResponse;
import org.patsimas.optical_lens_store.exceptions.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     UserDetailsServiceImpl userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        Map<String, Object> claims = new HashMap<>();
        final String jwt = jwtTokenProvider.generateToken(claims, userDetails.getUsername());

        log.info("Authentication process completed");

        return AuthenticationResponse.builder()
                .jwt(jwt)
                .build();
    }

    private void authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){

        try {

            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (BadCredentialsException e){

            throw new AuthenticationFailedException("Λαναθασμένα διαπιστευτήρια.", e);
        }
    }
}
