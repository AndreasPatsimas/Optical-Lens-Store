package org.patsimas.optical_lens_store.domain.glasses;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"glass"})
@ToString(exclude = {"glass"})
@Entity
@Table(name = "glasses_lens")
public class GlassLen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private Short type;

    @Column(name = "coating")
    private String coating;

    @Column(name = "color")
    private String color;

    @Column(name = "diameter")
    private String diameter;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "glasses_id")
    private Glass glass;
}
