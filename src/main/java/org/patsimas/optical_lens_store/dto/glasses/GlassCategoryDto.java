package org.patsimas.optical_lens_store.dto.glasses;

import lombok.*;
import org.patsimas.optical_lens_store.enums.GlassCategoryType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlassCategoryDto {

    private Long id;
    private GlassCategoryType type;
    private String sphOne;
    private String cylOne;
    private String axOne;
    private String prismOne;
    private String sphTwo;
    private String cylTwo;
    private String axTwo;
    private String prismTwo;
    private String dpOne;
    private String dpTwo;
    private Integer height;
}
