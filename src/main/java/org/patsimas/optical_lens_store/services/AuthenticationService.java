package org.patsimas.optical_lens_store.services;

import org.patsimas.optical_lens_store.dto.authenticate.AuthenticationRequest;
import org.patsimas.optical_lens_store.dto.authenticate.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
