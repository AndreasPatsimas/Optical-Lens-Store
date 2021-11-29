package org.patsimas.optical_lens_store.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.config.security.JwtTokenProvider;
import org.patsimas.optical_lens_store.domain.User;
import org.patsimas.optical_lens_store.dto.authenticate.*;
import org.patsimas.optical_lens_store.enums.ChangePasswordStatus;
import org.patsimas.optical_lens_store.enums.ForgotPasswordStatus;
import org.patsimas.optical_lens_store.exceptions.AuthenticationFailedException;
import org.patsimas.optical_lens_store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@PropertySource(value = "classpath:application.properties")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${email.address.from}")
    private String mailSender;

    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;
    private JwtTokenProvider jwtTokenProvider;
    private MailService mailService;
    private UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
                                     UserDetailsServiceImpl userDetailsService, JwtTokenProvider jwtTokenProvider,
                                     MailService mailService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.mailService = mailService;
        this.userRepository = userRepository;
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

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest) {

        log.info("Change password process begins");

        authenticate(new UsernamePasswordAuthenticationToken(changePasswordRequest.getUsername(),
                changePasswordRequest.getOldPassword()));

        Optional<User> user = userRepository.findByUsername(changePasswordRequest.getUsername());

        if(user.isPresent()){

            saveBcryptedPassword(changePasswordRequest.getNewPassword(), user.get());

            log.info("Change password process completed");

            return ChangePasswordResponse.builder()
                    .changePasswordStatus(ChangePasswordStatus.SUCCEEDED)
                    .build();
        }

        return  ChangePasswordResponse.builder()
                .changePasswordStatus(ChangePasswordStatus.FAILED)
                .build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {

        log.info("Forgot password process begins");

        Optional<User> user = userRepository.findByUsername(forgotPasswordRequest.getUsername());

        if(user.isPresent()){

            if(validateEmail(user.get(), forgotPasswordRequest.getEmail())){

                String alphanumeric = UUID.randomUUID().toString();

                mailService.sendMessage(mailSender, forgotPasswordRequest.getEmail(), "Νέος Κωδικός",
                        emailText(alphanumeric));

                saveBcryptedPassword(alphanumeric, user.get());

                log.info("Forgot password process completed");

                return ForgotPasswordResponse.builder()
                        .forgotPasswordStatus(ForgotPasswordStatus.USERNAME_VERIFIED)
                        .resetKeyPassword(alphanumeric)
                        .build();
            }
            else
                return ForgotPasswordResponse.builder()
                        .forgotPasswordStatus(ForgotPasswordStatus.USERNAME_NOT_MATCH_WITH_EMAIL)
                        .build();
        }

        return ForgotPasswordResponse.builder()
                .forgotPasswordStatus(ForgotPasswordStatus.USERNAME_NOT_VERIFIED)
                .build();
    }

    private void saveBcryptedPassword(String password, User user){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
    }

    private String emailText(String password){

        StringBuilder sb = new StringBuilder();

        sb.append("Ο νέος σας κωδικός είναι ");
        sb.append(password).append(". ");
        sb.append("Εισέλθετε με το νέος σας κωδικό και μετά θα είστε σε θέση να βάλετε τον κωδικό που επιθυμείτε.");

        return sb.toString();
    }

    private boolean validateEmail(User user, String email){

        return user.getEmail().equals(email);
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
