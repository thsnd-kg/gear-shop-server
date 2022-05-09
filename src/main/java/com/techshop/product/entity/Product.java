package com.techshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name",
            length = 50,
            unique = true)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
