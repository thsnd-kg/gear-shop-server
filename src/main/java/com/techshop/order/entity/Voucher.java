package com.techshop.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.common.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Column(name = "voucher")
    private Long voucherId;

    @Column(name = "voucher_name",
            length = 100)
    private String voucherName;

    @Column(name = "voucher_desc",
            length = 100)
    private String voucherDesc;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "valid_date")
    private LocalDateTime validDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "voucher_value")
    private Integer voucherValue;

    @Column(name = "capped_at")
    private Integer cappedAt;

    @Column(name = "is_deleted",
            columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher")
    @Column(name = "order_id")
    private Set<Order> orders;
}
