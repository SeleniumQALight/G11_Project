package org.api;

public interface BooksEndPoints {
  String BASE_URL = "https://demoqa.com";
  String LOGIN = BASE_URL + "/Account/v1/Login";
  String ALL_BOOKS = BASE_URL + "/BookStore/v1/Books";
  String GET_ALL_BOOKS_OF_USER = BASE_URL + "/Account/v1/User/{0}";
}
