package org.patsimas.optical_lens_store.dto.glasses;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlassSkeletonDto {

    private Long id;
    private String brand;
    private String model;
    private String color;
    private String size;
    private String nose;
}
