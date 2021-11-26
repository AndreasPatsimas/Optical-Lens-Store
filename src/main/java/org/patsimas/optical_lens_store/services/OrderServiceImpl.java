package org.patsimas.optical_lens_store.services;

import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.domain.Order;
import org.patsimas.optical_lens_store.domain.contact_lens.ContactCard;
import org.patsimas.optical_lens_store.domain.glasses.Glass;
import org.patsimas.optical_lens_store.dto.OrderDto;
import org.patsimas.optical_lens_store.repositories.OrderRepository;
import org.patsimas.optical_lens_store.repositories.contact_lens.ContactCardRepository;
import org.patsimas.optical_lens_store.repositories.glasses.GlassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.Optional;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private GlassRepository glassRepository;
    private ContactCardRepository contactCardRepository;
    private ConversionService conversionService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, GlassRepository glassRepository,
                            ContactCardRepository contactCardRepository, ConversionService conversionService) {
        this.orderRepository = orderRepository;
        this.glassRepository = glassRepository;
        this.contactCardRepository = contactCardRepository;
        this.conversionService = conversionService;
    }

    @Override
    public void save(Long glassId, Long contactCardId, OrderDto orderDto) {

        log.info("Save Order process begins");

        if (!ObjectUtils.isEmpty(glassId) || !ObjectUtils.isEmpty(contactCardId)) {

            Order order = conversionService.convert(orderDto, Order.class);

            if (!ObjectUtils.isEmpty(glassId)) {
                Optional<Glass> glassOptional = glassRepository.findById(glassId);
                glassOptional.ifPresent(glass -> {

                    glass.setLastUpdateDate(Instant.now());
                    glassRepository.save(glass);
                    if (!ObjectUtils.isEmpty(order)) {
                        order.setGlass(glass);
                        orderRepository.save(order);
                    }
                });
            }
            if (!ObjectUtils.isEmpty(contactCardId)) {
                Optional<ContactCard> contactCardOptional = contactCardRepository.findById(contactCardId);
                contactCardOptional.ifPresent(contactCard -> {

                    contactCard.setLastUpdateDate(Instant.now());
                    contactCardRepository.save(contactCard);
                    if (!ObjectUtils.isEmpty(order)) {
                        order.setContactCard(contactCard);
                        orderRepository.save(order);
                    }
                });
            }

            log.info("Save Order process end");
        }
    }

    @Override
    public void deleteOrder(Long orderId) {

        log.info("Delete Order[id:{}] process begins", orderId);

        orderRepository.deleteById(orderId);

        log.info("Delete Order[id:{}] process end", orderId);
    }
}
