package org.patsimas.optical_lens_store.dto.contact_lens;

import lombok.*;
import org.patsimas.optical_lens_store.dto.OrderDto;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactCardDto {

    private Long id;
    private String comments;
    private Instant registerDate;
    private Instant lastUpdateDate;
    private Set<ContactLenDto> contactLens;
    private Set<OrderDto> orders;
}
