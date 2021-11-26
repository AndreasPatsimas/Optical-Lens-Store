package org.patsimas.optical_lens_store.repositories;

import org.patsimas.optical_lens_store.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
