package org.patsimas.optical_lens_store.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Double total;
    private Double paymentInAdvance;
    private Double balance;
}
