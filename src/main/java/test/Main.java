package test;

import com.google.gson.Gson;
import http.HttpMethods;
import user.*;
import java.io.*;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        User user = new Gson().fromJson(new FileReader("src/main/resources/user.json"), User.class);
        testAllHttpMethods(user, 5, "Karianne");
    }

    public static void testAllHttpMethods(User user, int userId, String username) throws URISyntaxException, IOException, InterruptedException {
        HttpMethods.invokePOST(user);
        HttpMethods.invokePUT(user, userId);
        HttpMethods.invokeDELETE(userId);
        HttpMethods.invokeGET();
        HttpMethods.invokeGET(userId);
        HttpMethods.invokeGET(username);
        HttpMethods.getOpenTasks(userId);
        HttpMethods.getCommentsOnLastPost(userId);
    }
}
