package org.patsimas.optical_lens_store.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"customer", "orders"})
@ToString(exclude = {"customer", "orders"})
@Entity
@Table(name = "contact_lens")
public class ContactLen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private Short type;

    @Column(name = "sph")
    private String sph;

    @Column(name = "cyl")
    private String cyl;

    @Column(name = "axe")
    private String axe;

    @Column(name = "bc")
    private String bc;

    @Column(name = "diam")
    private String diam;

    @Column(name = "mk1")
    private String mkOne;

    @Column(name = "mk2")
    private String mkTwo;

    @Column(name = "contact_len_type")
    private String contactLenType;

    @Column(name = "register_date")
    private Instant registerDate;

    @Column(name = "last_update_date")
    private Instant lastUpdateDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "contactLen", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;
}
