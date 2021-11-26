package org.patsimas.optical_lens_store.converters.glasses;

import org.patsimas.optical_lens_store.domain.glasses.Glass;
import org.patsimas.optical_lens_store.dto.glasses.GlassDto;
import org.patsimas.optical_lens_store.repositories.glasses.GlassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.Optional;

@Component
public class GlassDtoToGlassConverter implements Converter<GlassDto, Glass> {

    @Autowired
    private GlassRepository glassRepository;

    @Override
    public Glass convert(GlassDto glassDto) {

        Instant registerDate;
        if (ObjectUtils.isEmpty(glassDto.getId()))
            registerDate = Instant.now();
        else {
            Optional<Glass> glass = glassRepository.findById(glassDto.getId());
            registerDate = glass.map(Glass::getRegisterDate).orElse(null);
        }

        return Glass.builder()
                .id(glassDto.getId())
                .comments(glassDto.getComments())
                .registerDate(registerDate)
                .lastUpdateDate(Instant.now())
                .build();
    }
}
