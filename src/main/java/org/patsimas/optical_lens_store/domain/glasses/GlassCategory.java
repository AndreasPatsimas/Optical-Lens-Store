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
@Table(name = "glasses_category")
public class GlassCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private Short type;

    @Column(name = "sph1")
    private String sphOne;

    @Column(name = "cyl1")
    private String cylOne;

    @Column(name = "ax1")
    private String axOne;

    @Column(name = "prism1")
    private String prismOne;

    @Column(name = "sph2")
    private String sphTwo;

    @Column(name = "cyl2")
    private String cylTwo;

    @Column(name = "ax2")
    private String axTwo;

    @Column(name = "prism2")
    private String prismTwo;

    @Column(name = "dp1")
    private String dpOne;

    @Column(name = "dp2")
    private String dpTwo;

    @Column(name = "height")
    private Integer height;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "glasses_id")
    private Glass glass;
}
