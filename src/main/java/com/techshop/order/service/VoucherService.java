package com.techshop.order.service;

import com.techshop.order.dto.voucher.CreateVoucherDto;
import com.techshop.order.dto.voucher.UpdateVoucherDto;
import com.techshop.order.entity.Voucher;

import java.util.List;

public interface VoucherService {
    Voucher getVoucherById(Long voucherId);

    List<Voucher> getVouchers();

    List<Voucher> getVoucherActive();

    Voucher createVoucher(CreateVoucherDto dto);

    Boolean deleteVoucher(Long voucherId);

    Voucher updateVoucher(UpdateVoucherDto dto);

    Voucher getVoucherByName(String name);
}
