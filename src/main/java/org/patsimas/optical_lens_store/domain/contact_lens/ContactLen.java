package org.patsimas.optical_lens_store.domain.contact_lens;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"contactCard"})
@ToString(exclude = {"contactCard"})
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "contact_cards_id")
    private ContactCard contactCard;
}
