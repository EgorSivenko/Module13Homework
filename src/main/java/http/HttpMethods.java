package http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.UserDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpMethods {
    private final static String USERS_URL = "https://jsonplaceholder.typicode.com/users/";
    private final static String POSTS_URL = "https://jsonplaceholder.typicode.com/posts/";
    private final static String USERNAME_URL = "https://jsonplaceholder.typicode.com/users?username=";
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void createUser(UserDTO user) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.createRequestBuilder(USERS_URL)
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        HttpResponse<String> response = HttpUtils.getStringResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void updateUser(UserDTO user, int userId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.createRequestBuilder(USERS_URL + userId)
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        HttpResponse<String> response = HttpUtils.getStringResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void removeUser(int userId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.createRequestBuilder(USERS_URL + userId)
                .DELETE()
                .build();

        HttpResponse<String> response = HttpUtils.getStringResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void getAllUsers() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.createRequestBuilder(USERS_URL)
                .GET()
                .build();

        HttpResponse<String> response = HttpUtils.getStringResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void getUser(int userId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.createRequestBuilder(USERS_URL + userId)
                .GET()
                .build();

        HttpResponse<String> response = HttpUtils.getStringResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void getUser(String username) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.createRequestBuilder(USERNAME_URL + username)
                .GET()
                .build();

        HttpResponse<String> response = HttpUtils.getStringResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void getOpenTasks(int userId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpUtils.createRequestBuilder(USERS_URL + userId + "/todos?completed=false")
                .GET()
                .build();

        HttpResponse<String> response = HttpUtils.getStringResponse(request);
        HttpUtils.displayResponseInfo(response);
    }

    public static void getCommentsOnLastPost(int userId) throws URISyntaxException, IOException, InterruptedException {
        int postId = userId * 10;
        HttpRequest request = HttpUtils.createRequestBuilder(POSTS_URL + postId + "/comments")
                .GET()
                .build();

        Path path = Paths.get("src/main/resources/user-" + userId + "-post-" + postId + "-comments.json");
        HttpResponse<Path> response = HttpUtils.getResponse(request, HttpResponse.BodyHandlers.ofFile(path));
        HttpUtils.displayResponseInfo(response);
    }
}
