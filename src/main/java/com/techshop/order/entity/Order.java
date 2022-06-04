package com.techshop.order.entity;

import com.techshop.common.entity.BaseEntity;
import com.techshop.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PUTTING;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @Transient
    private Long totalPrice;

    public Long getTotalPrice() {
        if (voucher != null) {
            long sum = orderDetails.stream().mapToLong(i -> i.getVariant().getPrice() * i.getQuantity()).sum();
            long deal = sum * voucher.getVoucherValue() / 100;
            System.out.println(voucher.getCappedAt());
            if (deal > voucher.getCappedAt()) {
                sum = sum - voucher.getCappedAt();
            } else {
                sum = sum - deal;
            }

            return sum;
        }

        return orderDetails.stream().mapToLong(i -> i.getVariant().getPrice() * i.getQuantity()).sum();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted = false;
}
