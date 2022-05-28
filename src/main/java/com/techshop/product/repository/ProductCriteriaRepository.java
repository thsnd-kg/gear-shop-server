package com.techshop.product.repository;

import com.techshop.product.entity.Product;
import com.techshop.product.search.ProductSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ProductCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Product> findAllWithFilters( ProductSearchCriteria productSearchCriteria) {
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Predicate predicate = getPredicate(productSearchCriteria, productRoot);
        criteriaQuery.where(predicate);

        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    private Predicate getPredicate(ProductSearchCriteria productSearchCriteria, Root<Product> productRoot) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.notEqual(productRoot.get("activeFlag"), "D"));

        if (Objects.nonNull(productSearchCriteria.getProductName())) {
            predicates.add(criteriaBuilder.like(productRoot.get("productName"), "%" + productSearchCriteria.getProductName() + "%"));
        }

        if (Objects.nonNull(productSearchCriteria.getCategoryId())) {
            if (!productSearchCriteria.getCategoryId().isEmpty()) {
                productRoot.fetch("category", JoinType.LEFT);
                predicates.add(criteriaBuilder.in(productRoot.get("category").get("categoryId")).value(productSearchCriteria.getCategoryId()));
            }
        }

        if (Objects.nonNull(productSearchCriteria.getBrandId())) {
            if (!productSearchCriteria.getBrandId().isEmpty()) {
                productRoot.fetch("brand", JoinType.LEFT);
                predicates.add(criteriaBuilder.in(productRoot.get("brand").get("brandId")).value(productSearchCriteria.getBrandId()));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
