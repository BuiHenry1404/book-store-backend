package com.henry.book_store.utils;

import java.util.Arrays;
import java.util.List;

public abstract class UrlUtil {

    public static final String API_VERSION = "/v1";
    public static final String PREFIX = "/api";
    public static final String BASE_URL = PREFIX + API_VERSION;

    public static final String CATEGORY_URL = BASE_URL + "/categories";
    public static final String BOOK_URL = BASE_URL + "/books";


    public static final List<String> PUBLIC_URLS = Arrays.asList(
            CATEGORY_URL + "/**",
            BOOK_URL + "/**"

    );
}
