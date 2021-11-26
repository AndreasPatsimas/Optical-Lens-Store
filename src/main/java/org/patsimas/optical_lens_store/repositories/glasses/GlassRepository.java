package org.patsimas.optical_lens_store.repositories.glasses;

import org.patsimas.optical_lens_store.domain.glasses.Glass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlassRepository extends JpaRepository<Glass, Long> {

    List<Glass> findByCustomer_IdOrderByLastUpdateDate(Long customerId);
}
