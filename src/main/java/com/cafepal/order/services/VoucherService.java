package com.cafepal.order.services;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import com.cafepal.order.dto.request.VoucherDTO;
import com.cafepal.order.dto.response.VoucherResDTO;
import com.cafepal.order.exception.MessageException;
import com.cafepal.order.exception.VoucherNotFoundException;
import com.cafepal.order.model.Voucher;
import com.cafepal.order.repository.VoucherRepository;
import com.cafepal.order.utils.ResponseUtil;

@Service
public class VoucherService {

    public static final Logger logger = LoggerFactory.getLogger(CartService.class);

    @Autowired
    VoucherRepository voucherRepository;

    public ResponseEntity<Map<String, Object>> getVoucher(VoucherDTO voucherDto) {

        Optional<Voucher> checkVoucher = voucherRepository.findByCode(voucherDto.getVoucherCode());

        Optional<Voucher> getVoucher = voucherRepository.findByCodeAndIsActive(voucherDto.getVoucherCode(), true);
        VoucherResDTO result = null;

        if (checkVoucher.isEmpty()) {
            logger.info("voucher not found in database");
            throw new MessageException("Voucher code not Found", "This voucher code is not valid",
                    "Please try again with a different code", "NOT_FOUND", HttpStatus.NOT_FOUND);
        }

        if (getVoucher.isPresent()) {
            getVoucher.get();

            result = VoucherResDTO.builder()
                    .voucherCode(voucherDto.getVoucherCode())
                    .isValid(getVoucher.get().getIsActive())
                    .discountAmount(getVoucher.get().getDiscountAmount())
                    .message("Success")
                    .build();

            logger.info("Succes to get voucher code {} ;", result);
        } else {
            throw new VoucherNotFoundException("Voucher code is not valid");
        }

        return ResponseUtil.dataSuccess(result);
    }
}
