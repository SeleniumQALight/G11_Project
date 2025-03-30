package org.api;


public interface EndPoints {
    String BASE_URL =  "https://aqa-complexapp.onrender.com";
    String BASE_URL_CURRENCY = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    String POST_BY_USER = BASE_URL + "/api/postsByAuthor/{0}";

    String LOGIN = BASE_URL + "/api/login";
    String CREATE_POST = BASE_URL + "/api/create-post";
    String DELETE_POST = BASE_URL + "/api/post/{0}";
}
