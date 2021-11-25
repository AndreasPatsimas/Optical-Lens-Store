package org.patsimas.optical_lens_store.domain;

import lombok.*;
import org.patsimas.optical_lens_store.domain.glasses.Glass;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"glass", "contactLen"})
@ToString(exclude = {"glass", "contactLen"})
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "total")
    private Double total;

    @Column(name = "payment_in_advance")
    private Double paymentInAdvance;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "glasses_id")
    private Glass glass;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "contact_lens_id")
    private ContactLen contactLen;
}
