package org.patsimas.optical_lens_store.dto;

import lombok.*;
import org.patsimas.optical_lens_store.enums.GlassLenType;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactLenDto {

    private Long id;
    private GlassLenType type;
    private String sph;
    private String cyl;
    private String axe;
    private String bc;
    private String diam;
    private String mkOne;
    private String mkTwo;
    private String contactLenType;
    private Instant registerDate;
    private Instant lastUpdateDate;
    private Set<OrderDto> orders;
}
