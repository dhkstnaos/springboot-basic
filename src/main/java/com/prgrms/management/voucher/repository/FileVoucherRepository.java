package com.prgrms.management.voucher.repository;

import com.prgrms.management.config.ErrorMessage;
import com.prgrms.management.voucher.domain.FixedAmountVoucher;
import com.prgrms.management.voucher.domain.PercentAmountVoucher;
import com.prgrms.management.voucher.domain.Voucher;
import com.prgrms.management.voucher.domain.VoucherType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@Profile("dev")
public class FileVoucherRepository implements VoucherRepository {
    private static final String VOUCHER_FILE_NAME = "src/main/resources/voucher.csv";
    private static final Logger logger = LoggerFactory.getLogger(FileVoucherRepository.class);
    private final BufferedWriter writer;
    private final BufferedReader reader;

    public FileVoucherRepository() throws IOException {
        this.writer = new BufferedWriter(new FileWriter(VOUCHER_FILE_NAME, true));
        this.reader = new BufferedReader(new FileReader(VOUCHER_FILE_NAME));
    }

    @Override
    public Voucher insert(Voucher voucher) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(VOUCHER_FILE_NAME, true))) {
            bufferedWriter.write(voucher.getVoucherId() + "," + voucher.getAmount() + "," + voucher.getVoucherType());
            bufferedWriter.newLine();
        } catch (IOException e) {
            logger.warn("{}:{}",e.getClass(), ErrorMessage.IO_EXCEPTION.getMessage());
        }
        return voucher;
    }

    @Override
    public List<Voucher> findAll() {
        List<Voucher> voucherList = new ArrayList<>();
        //try-with-resource
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(VOUCHER_FILE_NAME))) {
            String reader;
            while ((reader = bufferedReader.readLine()) != null) {
                String[] voucherInfo = reader.split(",");
                VoucherType type = VoucherType.of(voucherInfo[2]);
                if (type.equals(VoucherType.FIXED))
                    voucherList.add(new FixedAmountVoucher(UUID.fromString(voucherInfo[0]), Long.parseLong(voucherInfo[1])));
                else
                    voucherList.add(new PercentAmountVoucher(UUID.fromString(voucherInfo[0]), Long.parseLong(voucherInfo[1])));
            }
        } catch (IOException e) {
            logger.warn("{}:{}",e.getClass(), ErrorMessage.IO_EXCEPTION.getMessage());
        }
        return voucherList;
    }
}
