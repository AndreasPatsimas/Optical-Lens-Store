package org.patsimas.optical_lens_store.converters.glasses;

import org.patsimas.optical_lens_store.domain.glasses.GlassLen;
import org.patsimas.optical_lens_store.dto.glasses.GlassLenDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GlassLenDtoToGlassLenConverter implements Converter<GlassLenDto, GlassLen> {

    @Override
    public GlassLen convert(GlassLenDto glassLenDto) {
        return GlassLen.builder()
                .id(glassLenDto.getId())
                .type(glassLenDto.getType().code())
                .coating(glassLenDto.getCoating())
                .diameter(glassLenDto.getDiameter())
                .color(glassLenDto.getColor())
                .build();
    }
}
