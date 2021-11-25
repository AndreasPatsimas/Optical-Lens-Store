package org.patsimas.optical_lens_store.dto.contact_lens;

import lombok.*;
import org.patsimas.optical_lens_store.enums.GlassLenType;

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
}
