package org.patsimas.optical_lens_store.converters.orders;

import org.patsimas.optical_lens_store.domain.Order;
import org.patsimas.optical_lens_store.dto.OrderDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderDtoToOrderConverter implements Converter<OrderDto, Order> {

    @Override
    public Order convert(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .total(orderDto.getTotal())
                .paymentInAdvance(orderDto.getPaymentInAdvance())
                .balance(orderDto.getBalance())
                .orderDate(orderDto.getOrderDate())
                .deliveryDate(orderDto.getDeliveryDate())
                .build();
    }
}
