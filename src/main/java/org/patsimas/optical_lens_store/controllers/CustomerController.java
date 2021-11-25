package org.patsimas.optical_lens_store.controllers;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.dto.customers.CustomerDto;
import org.patsimas.optical_lens_store.dto.customers.CustomerSearchRequestDto;
import org.patsimas.optical_lens_store.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/customers")
@RestController
@Slf4j
@Api(description = "Customers (Πελάτες)")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    /**
     * Advanced Search Customers
     *
     * @param page          the page number
     * @param pageSize      records per page
     * @param sortBy        the sorting field
     * @param sortDirection the sorting direction
     * @return Customers as paged results
     */
    @PostMapping(value = "/advanced-search",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Page<CustomerDto> advancedSearch(@RequestParam Integer page,
                                     @RequestParam Integer pageSize,
                                     @RequestParam(required = false) String sortBy,
                                     @RequestParam(required = false) String sortDirection,
                                     @RequestBody CustomerSearchRequestDto customerSearchRequestDto) {

        log.info("Advanced search Customers");

        Pageable pageable;
        if (!ObjectUtils.isEmpty(sortBy) && !ObjectUtils.isEmpty(sortDirection)) {
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
            pageable = PageRequest.of(page, pageSize, sort);
        }
        else
            pageable = PageRequest.of(page, pageSize);

        return customerService.advancedSearch(customerSearchRequestDto, pageable);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity save(@RequestBody CustomerDto customerDto) {

        log.info("Save Customer[{}]", customerDto);
        customerService.save(customerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity delete(@PathVariable("id") Long id) {

        log.info("Delete customer[id:{}]", id);
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
