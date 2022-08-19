package br.alura.daysofcode.classes;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This class will build the necessary method to consume the IMDB api
 */
public class ApiBuilder {

    private final String apiKey;
    private final String url;

    public ApiBuilder(String url) {
        this.apiKey = System.getenv("API_KEY");
        this.url = url;

    }

    public String MakeRequest() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(this.GetApiURI())).GET().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private String GetApiURI() {
        return url.concat(apiKey);
    }

}
