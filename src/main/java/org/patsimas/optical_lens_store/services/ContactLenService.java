package org.patsimas.optical_lens_store.services;

import org.patsimas.optical_lens_store.dto.contact_lens.ContactCardDto;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactLenDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactLenService {

    List<ContactCardDto> fetchByCustomerId(Long customerId);

    void save(Long customerId, ContactCardDto contactCardDto);

    void saveContactLen(Long contactCardId, ContactLenDto contactLenDto);

    void deleteContactCard(Long contactCardId);
}
