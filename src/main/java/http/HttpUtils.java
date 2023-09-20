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
    public static HttpRequest.Builder createRequestBuilder(String URL) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .timeout(Duration.of(10, ChronoUnit.SECONDS))
                .uri(new URI(URL));
    }

    private static HttpClient buildHttpClient() {
        return HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .cookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_NONE))
                .build();
    }

    public static HttpResponse<String> getStringResponse(HttpRequest request) throws IOException, InterruptedException {
        return buildHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static <T> HttpResponse<T> getResponse(HttpRequest request, HttpResponse.BodyHandler<T> bodyHandler) throws IOException, InterruptedException {
        return buildHttpClient().send(request, bodyHandler);
    }

    public static <T> void displayResponseInfo(HttpResponse<T> response) {
        var method = response.request().method();
        System.out.println(method + " response code: " + response.statusCode());
        System.out.println(method + " response uri: " + response.uri());
        System.out.println(method + " response body:\n" + response.body());
    }
}
