package com.cafepal.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemResDTO {
    private UUID id;
    private String name;
    private String description;
    private Integer price;
    private String category;
    private String imageUrl;

}
