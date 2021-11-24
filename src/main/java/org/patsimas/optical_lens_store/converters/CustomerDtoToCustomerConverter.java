package org.patsimas.optical_lens_store.converters;

import org.patsimas.optical_lens_store.domain.Customer;
import org.patsimas.optical_lens_store.dto.CustomerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomerConverter implements Converter<CustomerDto, Customer> {

    @Override
    public Customer convert(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .lastUpdateDate(customerDto.getLastUpdateDate())
                .registerDate(customerDto.getRegisterDate())
                .build();
    }
}
