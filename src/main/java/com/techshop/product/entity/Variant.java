package com.techshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Variant extends BaseEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "variant_id")
    private Long variantId;

    @Column(name = "sku",
            length = 100,
            unique = true)
    private String sku;

    private String variantName;
    private String variantDesc;
    private Long price;
    private String imgUrl;

    @Column(columnDefinition = ("varchar(1) default 'Y'"))
    private String activeFlag = "Y";

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "variant", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<VariantAttribute> attributes = new HashSet<>();

}
