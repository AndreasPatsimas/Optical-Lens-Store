package org.patsimas.optical_lens_store.services;

import org.patsimas.optical_lens_store.dto.authenticate.*;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest);

    ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest);
}
