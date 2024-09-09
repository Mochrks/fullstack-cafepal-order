package com.cafepal.order.services;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import com.cafepal.order.dto.request.AddCartDTO;
import com.cafepal.order.dto.request.DeleteCartDTO;
import com.cafepal.order.dto.response.CartResDTO;
import com.cafepal.order.dto.response.GetAllCartResDTO;
import com.cafepal.order.dto.response.MenuItemResDTO;
import com.cafepal.order.exception.CartNotFoundException;
import com.cafepal.order.exception.MenuNotFoundException;
import com.cafepal.order.model.Cart;
import com.cafepal.order.model.MenuItem;
import com.cafepal.order.model.Users;
import com.cafepal.order.repository.CartRepository;
import com.cafepal.order.repository.MenuRepository;
import com.cafepal.order.repository.UserRepository;
import com.cafepal.order.utils.ResponseUtil;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
public class CartService {
    public static final Logger logger = LoggerFactory.getLogger(CartService.class);

    public static final LocalDateTime now = LocalDateTime.now();

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Map<String, Object>> addItemCart(AddCartDTO addCartDTO) {

        MenuItem checkMenu = menuRepository.findById(addCartDTO.getMenuItemId()).orElse(null);
        Integer pricing = checkMenu.getPrice() * addCartDTO.getQuantity();
        CartResDTO result = null;
        if (checkMenu != null) {
            logger.info("menu with id" + addCartDTO.getMenuItemId() + "found in database");

            UUID userId = UUID.fromString("a2b5e6c1-9c6b-4a2d-9e1a-6d4a9d7b7a24");
            Users user = userRepository.findById(userId).orElse(null);

            Cart carts = Cart.builder()
                    .user(user)
                    .menuItem(checkMenu)
                    .quantity(addCartDTO.getQuantity())
                    .price(pricing)
                    .createdAt(now)
                    .updatedAt(now)
                    .build();
            cartRepository.save(carts);
            logger.info("Success save to cart {};", carts);
            result = CartResDTO.builder()
                    .id(carts.getId())
                    .menu(checkMenu)
                    .price(carts.getPrice())
                    .message("Add item to carts")
                    .build();
        }

        return ResponseUtil.createSuccess(result);

    }

    public ResponseEntity<Map<String, Object>> deleteCart(DeleteCartDTO deleteCartDTO) {

        Cart checkCart = cartRepository.findById(deleteCartDTO.getCartId()).orElse(null);

        if (checkCart == null) {
            throw new CartNotFoundException("cart with id " + deleteCartDTO.getCartId() + " not found");
        }

        cartRepository.delete(checkCart);

        logger.info("Success delete for cart with id" + deleteCartDTO + "in database");
        return ResponseUtil.deleteSuccess();
    }

    public ResponseEntity<Map<String, Object>> getAllCart(UUID userId) {
        List<Cart> carts = cartRepository.findByUser_id(userId);

        if (carts == null) {
            throw new CartNotFoundException("cart not found with userid :" + userId);
        }

        List<GetAllCartResDTO> result = carts.stream()
                .map(cart -> GetAllCartResDTO.builder()
                        .userId(userId)
                        .cartId(cart.getId())
                        .quantity(cart.getQuantity())
                        .menu(cart.getMenuItem())
                        .totalPrice(cart.getPrice())
                        .build())
                .collect(Collectors.toList());

        return ResponseUtil.getAllDataSuccess(result);
    }
}
