package org.patsimas.optical_lens_store.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.domain.Customer;
import org.patsimas.optical_lens_store.domain.contact_lens.ContactCard;
import org.patsimas.optical_lens_store.domain.contact_lens.ContactLen;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactCardDto;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactLenDto;
import org.patsimas.optical_lens_store.enums.GlassLenType;
import org.patsimas.optical_lens_store.repositories.CustomerRepository;
import org.patsimas.optical_lens_store.repositories.contact_lens.ContactCardRepository;
import org.patsimas.optical_lens_store.repositories.contact_lens.ContactLenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ContactLenServiceImpl implements ContactLenService {

    private CustomerRepository customerRepository;
    private ContactCardRepository contactCardRepository;
    private ContactLenRepository contactLenRepository;
    private ConversionService conversionService;

    @Autowired
    public ContactLenServiceImpl(CustomerRepository customerRepository, ContactCardRepository contactCardRepository,
                                 ContactLenRepository contactLenRepository, ConversionService conversionService) {
        this.customerRepository = customerRepository;
        this.contactCardRepository = contactCardRepository;
        this.contactLenRepository = contactLenRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<ContactCardDto> fetchByCustomerId(Long customerId) {

        log.info("Fetch ContactCard from customer[id:{}] process begins", customerId);

        List<ContactCard> contactCards = contactCardRepository.findByCustomer_IdOrderByLastUpdateDate(customerId);

        List<ContactCardDto> contactCardDtoList = contactCards.stream()
                .map(contactCard -> conversionService.convert(contactCard, ContactCardDto.class))
                .collect(Collectors.toList());

        log.info("Fetch ContactCard from customer[id:{}] process end", customerId);

        return contactCardDtoList;
    }

    @Override
    public void save(Long customerId, ContactCardDto contactCardDto) {

        log.info("Save ContactCard from customer[id:{}] process begins", customerId);

        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        customerOptional.ifPresent(customer -> {

            customer.setLastUpdateDate(Instant.now());
            customerRepository.save(customer);
            ContactCard contactCard = conversionService.convert(contactCardDto, ContactCard.class);
            if (!ObjectUtils.isEmpty(contactCard)) {
                contactCard.setCustomer(customer);
                contactCard = contactCardRepository.save(contactCard);
                if (ObjectUtils.isEmpty(contactCardDto.getId()))
                    initialSaveContactCard(contactCard);
            }
        });

        log.info("Save ContactCard from customer[id:{}] process end", customerId);
    }

    @Override
    public void saveContactLen(Long contactCardId, ContactLenDto contactLenDto) {

        log.info("Save Len from contactCard[id:{}] process begins", contactCardId);

        Optional<ContactCard> contactCardOptional = contactCardRepository.findById(contactCardId);

        contactCardOptional.ifPresent(contactCard -> {

            contactCard.setLastUpdateDate(Instant.now());
            contactCardRepository.save(contactCard);
            ContactLen contactLen = conversionService.convert(contactLenDto, ContactLen.class);
            if (!ObjectUtils.isEmpty(contactLen)) {
                contactLen.setContactCard(contactCard);
                contactLenRepository.save(contactLen);
                log.info("Save Len from contactCard[id:{}] process end", contactCardId);
            }
        });
    }

    @Override
    public void deleteContactCard(Long contactCardId) {

        log.info("Delete ContactCard[id:{}] process begins", contactCardId);

        contactCardRepository.deleteById(contactCardId);

        log.info("Delete ContactCard[id:{}] process end", contactCardId);
    }

    private void initialSaveContactCard(ContactCard contactCard) {

        Set<ContactLen> contactLens = Stream.of(ContactLen.builder()
                .type(GlassLenType.RIGHT.code())
                .contactCard(contactCard)
                .build(), ContactLen.builder()
                .type(GlassLenType.LEFT.code())
                .contactCard(contactCard)
                .build())
                .collect(Collectors.toSet());
        contactLenRepository.saveAll(contactLens);
    }
}
