package org.patsimas.optical_lens_store.converters.glasses;

import org.patsimas.optical_lens_store.domain.glasses.Glass;
import org.patsimas.optical_lens_store.domain.glasses.GlassCategory;
import org.patsimas.optical_lens_store.domain.glasses.GlassLen;
import org.patsimas.optical_lens_store.domain.glasses.GlassSkeleton;
import org.patsimas.optical_lens_store.dto.glasses.GlassCategoryDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassLenDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassSkeletonDto;
import org.patsimas.optical_lens_store.enums.GlassCategoryType;
import org.patsimas.optical_lens_store.enums.GlassLenType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GlassToGlassDtoConverter implements Converter<Glass, GlassDto> {

    @Override
    public GlassDto convert(Glass glass) {
        return GlassDto.builder()
                .id(glass.getId())
                .comments(glass.getComments())
                .registerDate(glass.getRegisterDate())
                .lastUpdateDate(glass.getLastUpdateDate())
                .build();
    }

    private Set<GlassCategoryDto> buildGlassCategories(Set<GlassCategory> glassCategories) {

        return !ObjectUtils.isEmpty(glassCategories) && !glassCategories.isEmpty() ?
                glassCategories.stream()
                        .map(glassCategory -> GlassCategoryDto.builder()
                                .id(glassCategory.getId())
                                .type(!ObjectUtils.isEmpty(glassCategory.getType()) ?
                                        GlassCategoryType.fromValue(glassCategory.getType()) : null)
                                .sphOne(glassCategory.getSphOne())
                                .cylOne(glassCategory.getCylOne())
                                .axOne(glassCategory.getAxOne())
                                .prismOne(glassCategory.getPrismOne())
                                .sphTwo(glassCategory.getSphTwo())
                                .cylTwo(glassCategory.getCylTwo())
                                .axTwo(glassCategory.getAxTwo())
                                .prismTwo(glassCategory.getPrismTwo())
                                .dpOne(glassCategory.getDpOne())
                                .dpTwo(glassCategory.getDpTwo())
                                .height(glassCategory.getHeight())
                                .build())
                        .sorted(Comparator.comparing(glassCategoryDto -> glassCategoryDto.getType().code()))
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : new HashSet<>();
    }

    private Set<GlassLenDto> buildGlassLens(Set<GlassLen> glassLens) {

        return !ObjectUtils.isEmpty(glassLens) && !glassLens.isEmpty() ?
                glassLens.stream()
                        .map(glassLen -> GlassLenDto.builder()
                                .id(glassLen.getId())
                                .type(!ObjectUtils.isEmpty(glassLen.getType()) ?
                                        GlassLenType.fromValue(glassLen.getType()) : null)
                                .coating(glassLen.getCoating())
                                .diameter(glassLen.getDiameter())
                                .color(glassLen.getColor())
                                .build())
                        .sorted(Comparator.comparing(glassLenDto -> glassLenDto.getType().code()))
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : new HashSet<>();
    }

    private GlassSkeletonDto buildGlassSkeleton(GlassSkeleton glassSkeleton) {

        return GlassSkeletonDto.builder()
                .id(glassSkeleton.getId())
                .brand(glassSkeleton.getBrand())
                .color(glassSkeleton.getColor())
                .model(glassSkeleton.getModel())
                .nose(glassSkeleton.getNose())
                .size(glassSkeleton.getSize())
                .build();
    }

}
