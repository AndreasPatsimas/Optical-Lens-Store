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
@Table(name = "glasses_skeleton")
public class GlassSkeleton {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private String size;

    @Column(name = "nose")
    private String nose;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "glasses_id")
    private Glass glass;
}
