package com.cafepal.order.services;

import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cafepal.order.dto.response.MenuItemResDTO;
import com.cafepal.order.exception.MenuNotFoundException;
import com.cafepal.order.model.MenuItem;
import com.cafepal.order.repository.MenuRepository;
import com.cafepal.order.utils.ResponseUtil;
import java.util.Map;
import java.util.UUID;

@Service
public class MenuService {
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    private MenuRepository menuRepository;

    public ResponseEntity<Map<String, Object>> getMenu() {

        List<MenuItem> menuItems = menuRepository.findAll();

        if (menuItems.isEmpty()) {
            throw new MenuNotFoundException("List Menu not found in database");
        }

        List<MenuItemResDTO> result = menuItems.stream()
                .map(menuItem -> MenuItemResDTO.builder()
                        .id(menuItem.getId())
                        .name(menuItem.getName())
                        .description(menuItem.getDescription())
                        .price(menuItem.getPrice())
                        .category(menuItem.getCategory())
                        .imageUrl(menuItem.getImageUrl())
                        .build())
                .collect(Collectors.toList());

        logger.info("Fetching data all menu in database {}:", result);
        return ResponseUtil.getAllDataSuccess(result);
    }

    public ResponseEntity<Map<String, Object>> getMenuId(UUID id) {
        Optional<MenuItem> menu = menuRepository.findById(id);

        if (menu.isEmpty()) {
            throw new MenuNotFoundException("Data menu by id not found in database");
        }

        MenuItemResDTO result = MenuItemResDTO.builder()
                .id(menu.get().getId())
                .name(menu.get().getName())
                .description(menu.get().getDescription())
                .price(menu.get().getPrice())
                .category(menu.get().getCategory())
                .imageUrl(menu.get().getImageUrl())
                .build();

        logger.info("Fetching data menu by id in database {}:", result);

        return ResponseUtil.getDataByIdSuccess(result);
    }

}
