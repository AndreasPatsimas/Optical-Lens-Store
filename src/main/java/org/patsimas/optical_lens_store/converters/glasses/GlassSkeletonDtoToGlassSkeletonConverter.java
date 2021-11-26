package org.patsimas.optical_lens_store.converters.glasses;

import org.patsimas.optical_lens_store.domain.glasses.GlassSkeleton;
import org.patsimas.optical_lens_store.dto.glasses.GlassSkeletonDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GlassSkeletonDtoToGlassSkeletonConverter implements Converter<GlassSkeletonDto, GlassSkeleton> {

    @Override
    public GlassSkeleton convert(GlassSkeletonDto glassSkeletonDto) {
        return GlassSkeleton.builder()
                .id(glassSkeletonDto.getId())
                .brand(glassSkeletonDto.getBrand())
                .color(glassSkeletonDto.getColor())
                .model(glassSkeletonDto.getModel())
                .nose(glassSkeletonDto.getNose())
                .size(glassSkeletonDto.getSize())
                .build();
    }
}
