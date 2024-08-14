package com.cafepal.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherResDTO {
    private String message;
    private boolean isValid;
    private String voucherCode;
    private Integer discountAmount;
}
