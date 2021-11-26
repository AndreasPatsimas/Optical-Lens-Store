package org.patsimas.optical_lens_store.converters.contact_lens;

import org.patsimas.optical_lens_store.domain.contact_lens.ContactCard;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactCardDto;
import org.patsimas.optical_lens_store.repositories.contact_lens.ContactCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.Optional;

@Component
public class ContactCardDtoToContactCardConverter implements Converter<ContactCardDto, ContactCard> {

    @Autowired
    private ContactCardRepository contactCardRepository;

    @Override
    public ContactCard convert(ContactCardDto contactCardDto) {

        Instant registerDate;
        if (ObjectUtils.isEmpty(contactCardDto.getId()))
            registerDate = Instant.now();
        else {
            Optional<ContactCard> contactCard = contactCardRepository.findById(contactCardDto.getId());
            registerDate = contactCard.map(ContactCard::getRegisterDate).orElse(null);
        }

        return ContactCard.builder()
                .id(contactCardDto.getId())
                .comments(contactCardDto.getComments())
                .registerDate(registerDate)
                .lastUpdateDate(Instant.now())
                .build();
    }
}
