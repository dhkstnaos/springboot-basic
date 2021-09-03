package com.programmers.voucher.service.voucher;

import com.programmers.voucher.entity.voucher.DiscountPolicy;
import com.programmers.voucher.entity.voucher.Voucher;

import java.util.List;
import java.util.Optional;

public interface VoucherService {
    void openStorage();
    void closeStorage();

    Voucher create(String name, DiscountPolicy.Type type, int value, long customerId);
    Optional<Voucher> findById(long id);
    List<Voucher> listAll();

    void update(Voucher voucher);
    void delete(long voucherId);
}