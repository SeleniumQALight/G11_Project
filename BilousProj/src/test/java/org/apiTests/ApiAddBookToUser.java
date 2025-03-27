package org.apiTests;

import org.api.BookStoreHW.ApiHelperBookStore;
import org.api.BookStoreHW.BooksDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.Map;

public class ApiAddBookToUser {
    ApiHelperBookStore apiHelperBookStore = new ApiHelperBookStore();
    String token;
    String userId;
    int numberOfBook = 0;

    @Before
    public void getTokenAndId() {
        Map response = apiHelperBookStore.getTokenBookStore("dronBil", "White1234567!");
        token = response.get("token").toString();
        userId = response.get("userId").toString();
    }

    @Test
    public void addBookToUser() {
        apiHelperBookStore.deleteAllBooksForUser(userId, token);
        apiHelperBookStore.addBookToUser(userId, numberOfBook, token);
        List<BooksDTO> userBooks = apiHelperBookStore.getUserBooks(userId, token).getBooks();
        Assert.assertEquals(1, userBooks.size());
        Assert.assertEquals(apiHelperBookStore.chooseNumberOfBook(numberOfBook), userBooks.get(0).getIsbn());
    }
}
