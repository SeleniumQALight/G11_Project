package org.apiTests;

import org.api.ApiHelperBookStore;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ApiTestsBookStore {
   ApiHelperBookStore apiHelperBookStore = new ApiHelperBookStore();
   String token;
    String userId;
    int numberOfBook = 0;

   @Before

    public void getTokenAndId() {
        Map response  = apiHelperBookStore.getTokenBookStore("", "");
        token = response.get("token").toString();
        userId = response.get("userId").toString();
        System.out.println("Token: " + token);
    }
    @Test
    public void getUserBooks() {
        apiHelperBookStore.getUserBooks(userId, token);
    }


}
