package org.patsimas.optical_lens_store.config.security;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.exceptions.AuthorizationFailedException;
import org.springframework.util.ObjectUtils;

import java.security.Principal;

@Slf4j
public class Authorization {

    public static void authorizeRequest(String username, Principal principal, String message){

        if(ObjectUtils.isEmpty(principal) || !principal.getName().equals(username))
            throw new AuthorizationFailedException(message);

        log.info("username: {} , principal: {}", username, principal.getName());
    }
}