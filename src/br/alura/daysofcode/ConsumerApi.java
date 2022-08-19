package br.alura.daysofcode;

import br.alura.daysofcode.classes.ApiBuilder;
import br.alura.daysofcode.classes.HTMLGenerator;
import br.alura.daysofcode.classes.MovieList;
import br.alura.daysofcode.classes.ResponseParser;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * The program entry point to consume the API
 */
public class ConsumerApi {

    public static void main(String[] args) {
        ApiBuilder api = new ApiBuilder("https://imdb-api.com/en/API/Top250Movies/");
        ResponseParser parser = new ResponseParser();

        try {
            String body = api.MakeRequest();
            HTMLGenerator htmlGenerator = new HTMLGenerator();
            System.out.println("Resultado do consumo da api => ".concat(body));
            MovieList movieList = parser.ExtractInformation(new String[]{"title", "image", "imDbRating", "year"}, body);
            htmlGenerator.generate(movieList);
            htmlGenerator.close();
            Desktop.getDesktop().browse(htmlGenerator.getFilePath());
        } catch (URISyntaxException | InterruptedException | IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

}
