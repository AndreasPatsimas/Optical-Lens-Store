package org.patsimas.optical_lens_store.repositories.contact_lens;

import org.patsimas.optical_lens_store.domain.contact_lens.ContactCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactCardRepository extends JpaRepository<ContactCard, Long> {

    List<ContactCard> findByCustomer_IdOrderByLastUpdateDate(Long customerId);
}
