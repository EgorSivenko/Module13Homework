package http;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HttpUtils {
    public static HttpRequest.Builder getBuilder(String URL) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .uri(new URI(URL));
    }

    public static HttpResponse<String> getResponse(HttpRequest request) throws IOException, InterruptedException {
        return HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_NONE))
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static <T> void displayResponseInfo(HttpResponse<T> response) {
        var method = response.request().method();
        System.out.println(method + " response code: " + response.statusCode());
        System.out.println(method + " response uri: " + response.uri());
        System.out.println(method + " response body:\n" + response.body());
    }
}
