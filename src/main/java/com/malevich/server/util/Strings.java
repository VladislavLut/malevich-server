package com.malevich.server.util;

public class Strings {

    //Table
    public static final String TABLES_TABLE_NAME = "tables";

    public static final String TABLES_ID_COLUMN = "id";
    public static final String TABLES_OPENED_COLUMN = "opened";

    //User
    public static final String USERS_TABLE_NAME = "users";

    public static final String USERS_ID_COLUMN = "id";
    public static final String USERS_TYPE_COLUMN = "type";
    public static final String USERS_LOGIN_COLUMN = "login";
    public static final String USERS_PASSWORD_COLUMN = "password";
    public static final String USERS_PHONE_COLUMN = "phone";
    public static final String USERS_NAME_COLUMN = "name";
    public static final String USERS_BIRTH_DAY_COLUMN = "birth_day";

//Dish

    public static final String DISHES_TABLE_NAME = "dishes";

    public static final String DISHES_ID_COLUMN = "id";
    public static final String DISHES_CATEGORY_COLUMN = "category";
    public static final String DISHES_NAME_COLUMN = "name";
    public static final String DISHES_DESCRIPTION_COLUMN = "description";
    public static final String DISHES_IMAGE_URL_COLUMN = "imageurl";
    public static final String DISHES_PRICE_COLUMN = "price";
    public static final String DISHES_RATING_COLUMN = "rating";

//Order

    public static final String ORDERS_TABLE_NAME = "orders";

    public static final String ORDERS_ID_COLUMN = "id";
    public static final String ORDERS_TABLE_ID_COLUMN = "table_id";
    public static final String ORDERS_STATUS_COLUMN = "status";
    public static final String ORDERS_DATE_COLUMN = "date";
    public static final String ORDERS_COMMENT_COLUMN = "comment";

//OrderedDish

    public static final String ORDERED_DISHES_TABLE_NAME = "ordered_dishes";

    public static final String ORDERED_DISHES_ID_COLUMN = "id";
    public static final String ORDERED_DISHES_ORDER_ID_COLUMN = "order_id";
    public static final String ORDERED_DISHES_DISH_ID_COLUMN = "dish_id";
    public static final String ORDERED_DISHES_KITCHENER_ID_COLUMN = "kitchener_id";
    public static final String ORDERED_DISHES_STATUS_COLUMN = "status";
    public static final String ORDERED_DISHES_QUANTITY_COLUMN = "quantity";
    public static final String ORDERED_DISHES_TIME_COLUMN = "time";
    public static final String ORDERED_DISHES_COMMENT_COLUMN = "comment";

//Reservation

    public static final String RESERVED_TABLE_NAME = "reserved";

    public static final String RESERVED_ID_COLUMN = "id";
    public static final String RESERVED_TABLE_ID_COLUMN = "table_id";
    public static final String RESERVED_DATE_COLUMN = "date";
    public static final String RESERVED_TIME_COLUMN = "time";
    public static final String RESERVED_PHONE_COLUMN = "phone";
    public static final String RESERVED_NAME_COLUMN = "name";
    public static final String RESERVED_COMMENT_COLUMN = "comment";

//Comment

    public static final String COMMENTS_TABLE_NAME = "comments";

    public static final String COMMENTS_ID_COLUMN = "id";
    public static final String COMMENTS_ORDER_ID_COLUMN = "order_id";
    public static final String COMMENTS_COMMENT_COLUMN = "comment";

//Session

    public static final String SESSIONS_TABLE_NAME = "sessions";

    public static final String SESSIONS_USER_ID_COLUMN = "user_id";
    public static final String SESSIONS_SID_COLUMN = "sid";
    public static final String SESSIONS_LOGGED_IN_COLUMN = "logged_in";
    public static final String SESSIONS_LAST_ACTIVITY_TIME_COLUMN = "last_activity";
    public static final String SESSIONS_SESSION_START_TIME_COLUMN = "start_time";

}


