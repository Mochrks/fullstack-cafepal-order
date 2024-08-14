package com.cafepal.order.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafepal.order.common.Urls;
import com.cafepal.order.services.MenuService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @GetMapping(Urls.GET_ALL_MENU)
    public ResponseEntity<Map<String, Object>> getAllMenu() {
        logger.info("Getting all menu");
        return menuService.getMenu();
    }

    @GetMapping(Urls.GET_MENU_BY_ID)
    public ResponseEntity<Map<String, Object>> getMenuById(@PathVariable UUID id) {
        logger.info("Getting menu by id");
        return menuService.getMenuId(id);
    }

}
