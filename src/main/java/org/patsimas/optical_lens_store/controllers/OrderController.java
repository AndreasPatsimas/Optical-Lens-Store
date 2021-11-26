package org.patsimas.optical_lens_store.controllers;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.patsimas.optical_lens_store.dto.OrderDto;
import org.patsimas.optical_lens_store.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/orders")
@RestController
@Slf4j
@Api(description = "Glasses (Γυαλιά)")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity save(@RequestParam(value = "glassId", required = false) Long glassId,
                        @RequestParam(value = "contactCardId", required = false) Long contactCardId,
                        @RequestBody OrderDto orderDto) {

        log.info("Save Order");
        orderService.save(glassId, contactCardId, orderDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity delete(@PathVariable Long id) {

        log.info("Delete Order[id:{}]", id);
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
