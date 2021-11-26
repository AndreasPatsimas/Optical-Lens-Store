package org.patsimas.optical_lens_store.repositories.glasses;

import org.patsimas.optical_lens_store.domain.glasses.GlassCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlassCategoryRepository extends JpaRepository<GlassCategory, Long> {
}
