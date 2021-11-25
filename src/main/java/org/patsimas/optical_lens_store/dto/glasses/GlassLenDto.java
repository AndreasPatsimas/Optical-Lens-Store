package org.patsimas.optical_lens_store.dto.glasses;

import lombok.*;
import org.patsimas.optical_lens_store.enums.GlassLenType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlassLenDto {

    private Long id;
    private GlassLenType type;
    private String coating;
    private String color;
    private String diameter;
}
