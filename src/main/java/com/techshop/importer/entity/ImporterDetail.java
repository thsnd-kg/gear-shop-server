package com.techshop.importer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.techshop.product.entity.Variant;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImporterDetail {
    @JsonIgnore
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private ImporterDetailPK id = new ImporterDetailPK();

    @ManyToOne
    @MapsId("importId")
    @JoinColumn(name = "import_id")
    private Importer importer;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("variantId")
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Long price;
}
