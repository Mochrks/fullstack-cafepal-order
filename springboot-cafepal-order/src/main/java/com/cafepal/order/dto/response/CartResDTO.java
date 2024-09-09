package com.cafepal.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

import com.cafepal.order.model.Cart;
import com.cafepal.order.model.MenuItem;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResDTO {
    private UUID id;
    private String message;
    private MenuItem menu;
    private Integer price;
}
