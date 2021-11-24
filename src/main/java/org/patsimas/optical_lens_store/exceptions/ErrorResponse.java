package org.patsimas.optical_lens_store.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int errorCode;

    private HttpStatus status;

    private String message;
}
