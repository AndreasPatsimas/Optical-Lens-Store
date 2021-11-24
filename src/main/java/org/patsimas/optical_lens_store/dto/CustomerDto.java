package org.patsimas.optical_lens_store.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Instant registerDate;
    private Instant lastUpdateDate;
}
