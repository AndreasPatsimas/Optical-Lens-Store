package org.patsimas.optical_lens_store.services;

import org.patsimas.optical_lens_store.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    void save(Long glassId, Long contactCardId, OrderDto orderDto);

    void deleteOrder(Long orderId);
}
