package org.patsimas.optical_lens_store.dto.glasses;

import lombok.*;
import org.patsimas.optical_lens_store.dto.OrderDto;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlassDto {

    private Long id;
    private String comments;
    private Instant registerDate;
    private Instant lastUpdateDate;
    private Set<GlassCategoryDto> glassCategories;
    private Set<GlassLenDto> glassLens;
    private GlassSkeletonDto glassSkeleton;
    private Set<OrderDto> orders;
}
