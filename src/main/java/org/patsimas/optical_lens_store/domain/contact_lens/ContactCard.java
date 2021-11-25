package org.patsimas.optical_lens_store.domain.contact_lens;

import lombok.*;
import org.patsimas.optical_lens_store.domain.Customer;
import org.patsimas.optical_lens_store.domain.Order;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"customer", "orders", "contactLens"})
@ToString(exclude = {"customer", "orders", "contactLens"})
@Entity
@Table(name = "contact_cards")
public class ContactCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "comments")
    private String comments;

    @Column(name = "register_date")
    private Instant registerDate;

    @Column(name = "last_update_date")
    private Instant lastUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "contactCard", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;

    @OneToMany(mappedBy = "contactCard", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ContactLen> contactLens;
}
