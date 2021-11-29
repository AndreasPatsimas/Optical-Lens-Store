package org.patsimas.optical_lens_store.dto.authenticate;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.patsimas.optical_lens_store.enums.ForgotPasswordStatus;

@Getter
@ToString
@EqualsAndHashCode
@Builder
public class ForgotPasswordResponse {

    private ForgotPasswordStatus forgotPasswordStatus;

    private String resetKeyPassword;
}
