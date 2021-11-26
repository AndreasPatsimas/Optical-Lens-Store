package org.patsimas.optical_lens_store.converters.orders;

import org.patsimas.optical_lens_store.domain.Order;
import org.patsimas.optical_lens_store.dto.OrderDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderToOrderDtoConverter {

    protected Set<OrderDto> buildOrders(Set<Order> orders) {

        return !ObjectUtils.isEmpty(orders) && !orders.isEmpty() ?
                orders.stream()
                        .map(order -> OrderDto.builder()
                                .id(order.getId())
                                .total(order.getTotal())
                                .paymentInAdvance(order.getPaymentInAdvance())
                                .balance(order.getBalance())
                                .orderDate(order.getOrderDate())
                                .deliveryDate(order.getDeliveryDate())
                                .build())
                        .sorted(Comparator.comparing(OrderDto::getOrderDate).reversed()
                                .thenComparing(OrderDto::getDeliveryDate).reversed())
                        .collect(Collectors.toCollection(LinkedHashSet::new)) : new HashSet<>();
    }
}
