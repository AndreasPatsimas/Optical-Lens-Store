package org.patsimas.optical_lens_store.repositories;

import org.patsimas.optical_lens_store.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailAndActive(String email, short active);
}
