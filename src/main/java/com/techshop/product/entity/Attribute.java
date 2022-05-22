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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Attribute implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attribute_id")
    private Long attributeId;
    
    private String attributeName;

    @JsonIgnore
    @ManyToMany(mappedBy = "attributes")
    private Collection<Category> categories = new ArrayList<>();;

    private String attributeIcon;


    @JsonIgnore
    @OneToMany(mappedBy = "attribute", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<VariantAttribute> variants = new HashSet<>();
}
