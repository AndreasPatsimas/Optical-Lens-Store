package org.patsimas.optical_lens_store.services;

import org.patsimas.optical_lens_store.dto.CustomerDto;
import org.patsimas.optical_lens_store.dto.CustomerSearchRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    Page<CustomerDto> advancedSearch(CustomerSearchRequestDto customerSearchRequestDto, Pageable pageable);

    void save(CustomerDto customerDto);

    void delete(Long id);
}
