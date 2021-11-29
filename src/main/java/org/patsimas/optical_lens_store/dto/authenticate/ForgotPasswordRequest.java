package org.patsimas.optical_lens_store.dto.authenticate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ForgotPasswordRequest {

    private String username;

    private String email;
}
