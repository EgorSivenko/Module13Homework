package test;

import com.google.gson.Gson;
import http.HttpMethods;
import dto.*;
import java.io.*;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        UserDTO user = new Gson().fromJson(new FileReader("src/main/resources/user.json"), UserDTO.class);
        testAllHttpMethods(user, 5, "Karianne");
    }

    public static void testAllHttpMethods(UserDTO user, int userId, String username) throws URISyntaxException, IOException, InterruptedException {
        HttpMethods.createUser(user);
        HttpMethods.updateUser(user, userId);
        HttpMethods.removeUser(userId);
        HttpMethods.getAllUsers();
        HttpMethods.getUser(userId);
        HttpMethods.getUser(username);
        HttpMethods.getOpenTasks(userId);
        HttpMethods.getCommentsOnLastPost(userId);
    }
}
