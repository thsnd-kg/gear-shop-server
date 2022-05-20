package com.techshop.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.techshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NotBlank
    private String categoryName;

    private String categoryDesc;

    private String imgUrl;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Collection<Product> products;


    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @JsonIgnore
    @OneToMany(mappedBy = "parent")
    private Collection<Category> children;

    @OneToMany(mappedBy = "category")
    private Collection<Attribute> attributes = new ArrayList<>();
}
