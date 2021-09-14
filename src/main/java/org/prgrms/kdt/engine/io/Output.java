package org.prgrms.kdt.engine.io;

import org.prgrms.kdt.engine.voucher.domain.Voucher;

import java.util.Map;
import java.util.UUID;

public interface Output {
    void help();
    void createVoucher(Voucher voucher);
    void listVoucher(Map<UUID, Voucher> voucherList);
    void allocateCustomer(UUID voucherId, UUID customerId);
    void deleteCustomerVoucher(UUID customerId);
    void voucherOwner(UUID customerId);
    void printIllegalInputError();
    void printVoucherListNotFoundError();
    void printVoucherOwnerNotFoundError();
}