package com.cafepal.order.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafepal.order.common.Urls;
import com.cafepal.order.dto.request.VoucherDTO;
import com.cafepal.order.services.VoucherService;

import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/")
public class VoucherController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    VoucherService voucherService;

    @PostMapping(Urls.VALIDATE_VOUCHER)
    public ResponseEntity<Map<String, Object>> GetVoucher(@RequestBody VoucherDTO voucherDto) {
        logger.info("initial for get voucher code");

        return voucherService.getVoucher(voucherDto);
    }
}
