//package java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientDemo {
    public static void main(final String[] args) throws IOException, InterruptedException {
        var client = HttpClient.newBuilder()
                               .build();
        var request = HttpRequest.newBuilder()
                                 .GET()
                                 .uri(URI.create("https://www.ivowoltring.org"))
                                 .build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
