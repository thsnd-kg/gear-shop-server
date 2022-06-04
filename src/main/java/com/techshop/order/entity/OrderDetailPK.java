package com.techshop.order.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailPK implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "variant_id")
    private Long variantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailPK that = (OrderDetailPK) o;
        return orderId.equals(that.orderId) && variantId.equals(that.variantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, variantId);
    }
}
