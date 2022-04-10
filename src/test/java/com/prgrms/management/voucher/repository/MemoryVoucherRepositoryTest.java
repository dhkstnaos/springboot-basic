package com.prgrms.management.voucher.repository;

import com.prgrms.management.voucher.domain.FixedAmountVoucher;
import com.prgrms.management.voucher.domain.PercentAmountVoucher;
import com.prgrms.management.voucher.domain.Voucher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class MemoryVoucherRepositoryTest {

    MemoryVoucherRepository voucherRepository = new MemoryVoucherRepository();

    @Test
    void Voucher_Memory에_저장() {
        //given
        FixedAmountVoucher voucher = new FixedAmountVoucher(100);
        //when
        Voucher insert = voucherRepository.insert(voucher);
        //then
        Assertions.assertThat(insert).isEqualTo(voucher);
    }

    @Test
    void Voucher_Memory에서_조회() {
        //given
        FixedAmountVoucher voucher = new FixedAmountVoucher(100);
        PercentAmountVoucher voucherTwo = new PercentAmountVoucher(50);
        //when
        Voucher insert = voucherRepository.insert(voucher);
        Voucher insertTwo = voucherRepository.insert(voucherTwo);
        //then
        Assertions.assertThat(voucherRepository.findAll().size()).isEqualTo(2);
    }
}