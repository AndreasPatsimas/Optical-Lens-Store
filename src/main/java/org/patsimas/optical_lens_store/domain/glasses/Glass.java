package org.patsimas.optical_lens_store.domain.glasses;

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
@EqualsAndHashCode(exclude = {"customer", "glassCategories", "glassLens", "glassSkeleton", "orders"})
@ToString(exclude = {"customer", "glassCategories", "glassLens", "glassSkeleton", "orders"})
@Entity
@Table(name = "glasses")
public class Glass {

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

    @OneToMany(mappedBy = "glass", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<GlassCategory> glassCategories;

    @OneToMany(mappedBy = "glass", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<GlassLen> glassLens;

    @OneToOne(mappedBy = "glass", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private GlassSkeleton glassSkeleton;

    @OneToMany(mappedBy = "glass", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;
}
