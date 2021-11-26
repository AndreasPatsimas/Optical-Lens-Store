package org.patsimas.optical_lens_store.converters.glasses;

import org.patsimas.optical_lens_store.domain.glasses.GlassCategory;
import org.patsimas.optical_lens_store.dto.glasses.GlassCategoryDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GlassCategoryDtoToGlassCategoryConverter implements Converter<GlassCategoryDto, GlassCategory> {

    @Override
    public GlassCategory convert(GlassCategoryDto glassCategoryDto) {
        return GlassCategory.builder()
                .id(glassCategoryDto.getId())
                .type(glassCategoryDto.getType().code())
                .sphOne(glassCategoryDto.getSphOne())
                .cylOne(glassCategoryDto.getCylOne())
                .axOne(glassCategoryDto.getAxOne())
                .prismOne(glassCategoryDto.getPrismOne())
                .sphTwo(glassCategoryDto.getSphTwo())
                .cylTwo(glassCategoryDto.getCylTwo())
                .axTwo(glassCategoryDto.getAxTwo())
                .prismTwo(glassCategoryDto.getPrismTwo())
                .dpOne(glassCategoryDto.getDpOne())
                .dpTwo(glassCategoryDto.getDpTwo())
                .height(glassCategoryDto.getHeight())
                .build();
    }
}
