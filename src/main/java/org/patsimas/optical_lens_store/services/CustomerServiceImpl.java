package org.patsimas.optical_lens_store.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.domain.Customer;
import org.patsimas.optical_lens_store.dto.CustomerDto;
import org.patsimas.optical_lens_store.dto.CustomerSearchRequestDto;
import org.patsimas.optical_lens_store.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.patsimas.optical_lens_store.utils.PageUtils.buildPagedResults;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @PersistenceContext(unitName = "opticalEntityManager")
    private EntityManager em;

    private CustomerRepository customerRepository;
    private ConversionService conversionService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ConversionService conversionService) {
        this.customerRepository = customerRepository;
        this.conversionService = conversionService;
    }

    @Override
    public Page<CustomerDto> advancedSearch(CustomerSearchRequestDto customerSearchRequestDto, Pageable pageable) {

        log.info("Advanced search Customers process begins");
        
        CriteriaBuilder cBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cQuery = cBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = cQuery.from(Customer.class);

        //Constructing list of parameters for the query
        List<Predicate> predicates = buildPredicates(customerSearchRequestDto, cBuilder, customerRoot);

        //Constructing list of order properties
        List<Order> orderList = getOrders(pageable, cBuilder, customerRoot);

        //build the query
        cQuery.select(customerRoot)
                .where(predicates.toArray(new Predicate[]{}))
                .orderBy(orderList);

        //count the total elements that the query returned
        Integer count = em.createQuery(cQuery).getResultList().size();

        //need for TypedQuery for setting variables for paging
        TypedQuery<Customer> typedQuery = em.createQuery(cQuery);
        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<Customer> customerList = typedQuery.getResultList();

        return getCustomerDtoPage(pageable, customerList, count);
    }

    @Override
    public void save(CustomerDto customerDto) {

        log.info("Save Customer[{}] process begins", customerDto);

        log.info("Save Customer process end");
    }

    @Override
    public void delete(Long id) {

    }

    private List<Predicate> buildPredicates(CustomerSearchRequestDto searchRequestDto, CriteriaBuilder cBuilder,
                                            Root<Customer> customerRoot) {

        List<Predicate> predicates = new ArrayList<>();

        //Adding predicates for not null request fields

        if (!ObjectUtils.isEmpty(searchRequestDto.getFirstName()))
            predicates.add(cBuilder.like(customerRoot.get("firstName"), searchRequestDto.getFirstName() + '%'));

        if (!ObjectUtils.isEmpty(searchRequestDto.getLastName()))
            predicates.add(cBuilder.like(customerRoot.get("lastName"), searchRequestDto.getLastName() + '%'));

        if (!ObjectUtils.isEmpty(searchRequestDto.getEmail()))
            predicates.add(cBuilder.equal(customerRoot.get("email"), searchRequestDto.getEmail()));

        if (!ObjectUtils.isEmpty(searchRequestDto.getPhone()))
            predicates.add(cBuilder.equal(customerRoot.get("phone"), searchRequestDto.getPhone()));

        return predicates;
    }

    private List<Order> getOrders(Pageable pageable, CriteriaBuilder cBuilder, Root<Customer> customerRoot) {

        List<Order> orderList = new ArrayList<>();

        if (!ObjectUtils.isEmpty(pageable) && !pageable.getSort().isUnsorted()) {

            List<Sort.Order> orders = pageable.getSort().get().collect(Collectors.toList());

            if (!ObjectUtils.isEmpty(orders)) {

                String direction = orders.get(0).getDirection().name();

                switch (direction) {
                    case "ASC":
                        orderList = orders.stream().map(order -> cBuilder.asc(customerRoot.get(order.getProperty())))
                                .collect(Collectors.toList());
                        break;
                    case "DESC":
                        orderList = orders.stream().map(order -> cBuilder.desc(customerRoot.get(order.getProperty())))
                                .collect(Collectors.toList());
                        break;
                }
            } else {
                //default sorting
                orderList.add(cBuilder.desc(customerRoot.get("lastUpdateDate")));
            }
        } else {
            //default sorting
            orderList.add(cBuilder.desc(customerRoot.get("lastUpdateDate")));
        }
        return orderList;
    }

    private Page<CustomerDto> getCustomerDtoPage(Pageable pageable, List<Customer> customers,
                                                 Integer count){

        Page<CustomerDto> customerDtoPage;

        Long total = !ObjectUtils.isEmpty(count) ? (long) count : 0L;
        if (ObjectUtils.isEmpty(customers)) {
            total = 0L;
            customerDtoPage = buildPagedResults(Collections.EMPTY_LIST, total, pageable);

        } else {

            List<CustomerDto> customerDtoList = new ArrayList<>(customers.size());
            customers.forEach(customer -> customerDtoList.add(conversionService.convert(customer, CustomerDto.class)));
            customerDtoPage = buildPagedResults(customerDtoList, total, pageable);
        }

        log.info("Find all Customers end. Total Customers: {}, Page size {}, Page {} of {} pages.",
                total, pageable.getPageSize(), pageable.getPageNumber(), customerDtoPage.getTotalPages());

        return customerDtoPage;
    }
}
