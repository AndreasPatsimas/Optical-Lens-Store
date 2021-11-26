package org.patsimas.optical_lens_store.converters.contact_lens;

import org.patsimas.optical_lens_store.converters.orders.OrderToOrderDtoConverter;
import org.patsimas.optical_lens_store.domain.contact_lens.ContactCard;
import org.patsimas.optical_lens_store.domain.contact_lens.ContactLen;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactCardDto;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactLenDto;
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
public class ContactCardToContactCardDtoConverter extends OrderToOrderDtoConverter
        implements Converter<ContactCard, ContactCardDto> {

    @Override
    public ContactCardDto convert(ContactCard contactCard) {
        return ContactCardDto.builder()
                .id(contactCard.getId())
                .comments(contactCard.getComments())
                .registerDate(contactCard.getRegisterDate())
                .lastUpdateDate(contactCard.getLastUpdateDate())
                .contactLens(buildContactLens(contactCard.getContactLens()))
                .orders(buildOrders(contactCard.getOrders()))
                .build();
    }

    private Set<ContactLenDto> buildContactLens(Set<ContactLen> contactLens) {

        return !ObjectUtils.isEmpty(contactLens) && !contactLens.isEmpty() ?
                contactLens.stream()
                        .map(contactLen -> ContactLenDto.builder()
                                .id(contactLen.getId())
                                .type(!ObjectUtils.isEmpty(contactLen.getType()) ?
                                        GlassLenType.fromValue(contactLen.getType()) : null)
                                .sph(contactLen.getSph())
                                .cyl(contactLen.getCyl())
                                .axe(contactLen.getAxe())
                                .bc(contactLen.getBc())
                                .diam(contactLen.getDiam())
                                .mkOne(contactLen.getMkOne())
                                .mkTwo(contactLen.getMkTwo())
                                .contactLenType(contactLen.getContactLenType())
                                .build())
                        .sorted(Comparator.comparing(contactLenDto -> contactLenDto.getType().code()))
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : new HashSet<>();
    }
}
