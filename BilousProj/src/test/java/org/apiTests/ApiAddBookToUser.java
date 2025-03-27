package org.apiTests;

import org.api.BookStoreHW.ApiHelperBookStore;
import org.api.BookStoreHW.BooksDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ApiAddBookToUser {
    ApiHelperBookStore apiHelperBookStore = new ApiHelperBookStore();
    String token;
    String userId;
    String bookIsbn;
    List<BooksDTO> books;

    @Before
    public void getTokenAndId() {
        Map response = apiHelperBookStore.getTokenBookStore("dronBil", "White1234567!");
        token = response.get("token").toString();
        userId = response.get("userId").toString();
    }

    @Test
    public void addBookToUser() {
        apiHelperBookStore.deleteAllBooksForUser(userId, token);
        apiHelperBookStore.addBookToUser(userId, 0, token);



    }

}
