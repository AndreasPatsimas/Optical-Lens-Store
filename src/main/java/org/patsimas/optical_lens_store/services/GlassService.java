package org.patsimas.optical_lens_store.services;

import org.patsimas.optical_lens_store.dto.glasses.GlassCategoryDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassLenDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassSkeletonDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GlassService {

    List<GlassDto> fetchByCustomerId(Long customerId);

    void save(Long customerId, GlassDto glassDto);

    void saveGlassCategory(Long glassId, GlassCategoryDto glassCategoryDto);

    void saveGlassLen(Long glassId, GlassLenDto glassLenDto);

    void saveGlassSkeleton(Long glassId, GlassSkeletonDto glassSkeletonDto);

    void deleteGlass(Long glassId);
}
