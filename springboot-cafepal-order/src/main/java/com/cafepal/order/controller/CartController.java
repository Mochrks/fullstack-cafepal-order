package com.cafepal.order.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
import com.cafepal.order.common.Urls;
import com.cafepal.order.dto.request.AddCartDTO;
import com.cafepal.order.dto.request.DeleteCartDTO;
import com.cafepal.order.services.CartService;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/")
public class CartController {
    public static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartService cartService;

    @PostMapping(Urls.ADD_ITEM_TO_CART)
    public ResponseEntity<Map<String, Object>> AddTocart(@RequestBody AddCartDTO addCustomerDTO) {
        logger.info("initial for add cart");

        return cartService.addItemCart(addCustomerDTO);
    }

    @DeleteMapping(Urls.DELETE_CART_ITEM)
    ResponseEntity<Map<String, Object>> DeleteTocart(@RequestBody DeleteCartDTO deleteCartDTO) {
        logger.info("initial for delete cart");

        return cartService.deleteCart(deleteCartDTO);
    }

    @GetMapping(Urls.GET_ALL_CART_ITEM)
    ResponseEntity<Map<String, Object>> GetAllcart(@RequestParam UUID userId) {
        logger.info("initial for get all cart with userId:" + userId);
        return cartService.getAllCart(userId);
    }

}