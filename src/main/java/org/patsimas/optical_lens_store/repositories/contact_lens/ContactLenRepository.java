package org.patsimas.optical_lens_store.repositories.contact_lens;

import org.patsimas.optical_lens_store.domain.contact_lens.ContactLen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactLenRepository extends JpaRepository<ContactLen, Long> {
}
