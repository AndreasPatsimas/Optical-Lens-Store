package org.patsimas.optical_lens_store.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSearchRequestDto {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
