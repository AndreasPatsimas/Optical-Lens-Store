package org.patsimas.optical_lens_store.dto.authenticate;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    private final String jwt;
}
