package http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import user.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpMethods {
    private final static String USERS_URL = "https://jsonplaceholder.typicode.com/users/";
    private final static String POSTS_URL = "https://jsonplaceholder.typicode.com/posts/";
    private final static String USERNAME_URL = "https://jsonplaceholder.typicode.com/users?username=";
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void invokePOST(User user) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.getBuilder(USERS_URL)
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        HttpResponse<String> response = HttpUtils.getResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void invokePUT(User user, int userId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.getBuilder(USERS_URL + userId)
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        HttpResponse<String> response = HttpUtils.getResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void invokeDELETE(int userId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.getBuilder(USERS_URL + userId)
                .DELETE()
                .build();

        HttpResponse<String> response = HttpUtils.getResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void invokeGET() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.getBuilder(USERS_URL)
                .GET()
                .build();

        HttpResponse<String> response = HttpUtils.getResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void invokeGET(int userId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.getBuilder(USERS_URL + userId)
                .GET()
                .build();

        HttpResponse<String> response = HttpUtils.getResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void invokeGET(String username) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.getBuilder(USERNAME_URL + username)
                .GET()
                .build();

        HttpResponse<String> response = HttpUtils.getResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void getOpenTasks(int userId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.getBuilder(USERS_URL + userId + "/todos?completed=false")
                .GET()
                .build();

        HttpResponse<String> response = HttpUtils.getResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void getCommentsOnLastPost(int userId) throws URISyntaxException, IOException, InterruptedException {
        int postId = userId * 10;
        HttpRequest request = HttpUtils.getBuilder(POSTS_URL + postId + "/comments")
                .GET()
                .build();

        HttpResponse<Path> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofFile(
                        Paths.get("src/main/resources/user-" + userId + "-post-" + postId + "-comments.json")
                ));
        HttpUtils.displayResponseInfo(response);
    }
}