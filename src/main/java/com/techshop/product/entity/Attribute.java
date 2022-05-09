package com.techshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attribute extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private Long attributeId;
    
    private String attributeName;

    @Column(name = "category_id")
    private Long categoryId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id",
            insertable = false,
            updatable = false,
            nullable = false)
    private Category category;
}
