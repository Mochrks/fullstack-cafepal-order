package com.cafepal.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import com.cafepal.order.model.MenuItem;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAllCartResDTO {
    private UUID cartId;
    private UUID userId;
    private Integer quantity;
    private MenuItem menu;
    private Integer totalPrice;
}
