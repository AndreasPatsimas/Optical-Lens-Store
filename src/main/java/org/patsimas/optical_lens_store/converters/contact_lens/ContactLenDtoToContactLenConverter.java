package org.patsimas.optical_lens_store.converters.contact_lens;

import org.patsimas.optical_lens_store.domain.contact_lens.ContactLen;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactLenDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContactLenDtoToContactLenConverter implements Converter<ContactLenDto, ContactLen> {

    @Override
    public ContactLen convert(ContactLenDto contactLenDto) {

        return ContactLen.builder()
                .id(contactLenDto.getId())
                .type(contactLenDto.getType().code())
                .sph(contactLenDto.getSph())
                .cyl(contactLenDto.getCyl())
                .axe(contactLenDto.getAxe())
                .bc(contactLenDto.getBc())
                .diam(contactLenDto.getDiam())
                .mkOne(contactLenDto.getMkOne())
                .mkTwo(contactLenDto.getMkTwo())
                .contactLenType(contactLenDto.getContactLenType())
                .build();
    }
}
