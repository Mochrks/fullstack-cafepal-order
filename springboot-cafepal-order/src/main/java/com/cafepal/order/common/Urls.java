package com.cafepal.order.common;

public class Urls {

    // authentication API
    // public static final String AUTH_BASE = "/api/auth/";
    // public static final String REGISTER = AUTH_BASE + "register";
    // public static final String LOGIN = AUTH_BASE + "login";

    // menus API
    public static final String MENU_BASE = "menu/";
    public static final String GET_ALL_MENU = MENU_BASE + "list";
    public static final String GET_MENU_BY_ID = MENU_BASE + "{id}";

    // carts API
    public static final String CART_BASE = "carts/";
    public static final String GET_ALL_CART_ITEM = CART_BASE;
    public static final String ADD_ITEM_TO_CART = CART_BASE + "add";
    public static final String DELETE_CART_ITEM = CART_BASE + "delete";

    // payment API
    public static final String PAYMENT_BASE = "/api/payment/";
    public static final String CREATE_PAYMENT = PAYMENT_BASE;

    // payment history API
    public static final String HISTORY_PAYMENT_BASE = "/api/payment/history/";
    public static final String GET_PAYMENT_HISTORY = HISTORY_PAYMENT_BASE;

    // voucher API
    public static final String VOUCHER_BASE = "voucher/";
    public static final String VALIDATE_VOUCHER = VOUCHER_BASE + "validate/";

    // user_profiles API
    public static final String USER_PROFILE_BASE = "/api/user/profile/";
    public static final String GET_USER_PROFILE = VOUCHER_BASE;

    // order API
    public static final String ORDER_BASE = "/api/order/";
    public static final String PLACE_ORDER = ORDER_BASE;
    public static final String GET_ORDER_BY_ID = ORDER_BASE + "{id}";

}
