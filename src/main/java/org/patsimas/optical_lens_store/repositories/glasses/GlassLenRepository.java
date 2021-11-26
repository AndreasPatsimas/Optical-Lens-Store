package org.patsimas.optical_lens_store.repositories.glasses;

import org.patsimas.optical_lens_store.domain.glasses.GlassLen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlassLenRepository extends JpaRepository<GlassLen, Long> {
}
