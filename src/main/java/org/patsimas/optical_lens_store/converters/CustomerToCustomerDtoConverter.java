package org.patsimas.optical_lens_store.converters;

import org.patsimas.optical_lens_store.domain.Customer;
import org.patsimas.optical_lens_store.dto.customers.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerDtoConverter implements Converter<Customer, CustomerDto> {

    @Override
    public CustomerDto convert(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .lastUpdateDate(customer.getLastUpdateDate())
                .registerDate(customer.getRegisterDate())
                .build();
    }
}
