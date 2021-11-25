package org.patsimas.optical_lens_store.config.security;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.config.MessageProcessor;
import org.patsimas.optical_lens_store.exceptions.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private MessageProcessor processor;

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) {

        log.error("Responding with unauthorized error. Message - {}", e.getMessage());

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        try {
            processor.handle(ErrorResponse.builder()
                    .errorCode(HttpStatus.UNAUTHORIZED.value())
                    .status(HttpStatus.UNAUTHORIZED)
                    .message(e.getMessage() + ". Token may have expired.")
                    .build(), httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}