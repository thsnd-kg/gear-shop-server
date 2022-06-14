package com.techshop.order.repository;

import com.techshop.order.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    List<Voucher> findByIsDeletedFalse();

    Optional<Voucher> findByVoucherName(String name);
}
