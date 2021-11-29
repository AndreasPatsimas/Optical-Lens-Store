package org.patsimas.optical_lens_store.dto.authenticate;

import lombok.*;
import org.patsimas.optical_lens_store.enums.ChangePasswordStatus;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ChangePasswordResponse {

    private ChangePasswordStatus changePasswordStatus;
}
