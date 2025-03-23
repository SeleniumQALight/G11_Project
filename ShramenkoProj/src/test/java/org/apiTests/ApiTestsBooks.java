package org.apiTests;

import org.apache.log4j.Logger;
import org.api.ApiHelperBooks;
import org.api.dto.responseDTO.UserDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ApiTestsBooks {
    ApiHelperBooks apiHelperBooks = new ApiHelperBooks();
    String token;
    String userId;
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void getToken() {
        token = apiHelperBooks.getTokenBooks();
        userId = apiHelperBooks.getUserId();
        apiHelperBooks.deleteAllBooksByUser(userId, token);
    }

    @Test
    public void testAddBookToUser() {
        int numberOfBooksBefore = apiHelperBooks.getListOfBooksByUserId(token, userId).getBooks().size();
        logger.info("User had " + numberOfBooksBefore + " books before adding " + "\n"
                + "-----------------------------------------------------------" + "\n");

        String isbnOfFirstBook;
        UserDTO userDTO = new UserDTO();
        userDTO.setBooks(apiHelperBooks.getListOfBooks().getBooks());
        isbnOfFirstBook = userDTO.getBooks().get(0).getIsbn();

        apiHelperBooks.postNewBookInUserList(token, userId, isbnOfFirstBook);

        int numberOfBooksAfter = apiHelperBooks.getListOfBooksByUserId(token, userId).getBooks().size();
        logger.info("User has now " + numberOfBooksAfter + " book after adding " + "\n"
                + "-----------------------------------------------------------" + "\n");

        Assert.assertEquals("Number of books after adding  ",
                numberOfBooksBefore + 1, numberOfBooksAfter);

    }
}
