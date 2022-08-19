package br.alura.daysofcode.classes;
import java.io.*;
import java.net.URI;


/**
 * This class will be responsible to create the HTML page with the Movie information
 */
public class HTMLGenerator implements Closeable {

    private final PrintWriter printer;
    private final String filePath = String.format("/tmp/movies_%s.html", System.currentTimeMillis());

    public HTMLGenerator() throws FileNotFoundException {
        File file = new File(this.filePath);
        this.printer = new PrintWriter(file);
    }

    public void generate(MovieList movies) {
        System.out.println("Generating the HTML with the the found movies");
        this.printer.println(header());
        for (Movie movie: movies.getMovieList()) {
            String div = String.format(
                    divTemplate(), movie.title(), movie.urlImage(), movie.title(), movie.rating(), movie.year()
            );
            this.printer.println(div);
        }
        this.printer.flush();
    }

    public String header() {
        return """
                <head>
                	<meta charset="utf-8">
                	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
                	<link rel="stylesheet" \
                	 + href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" \
                	 + integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" \
                	 + crossorigin="anonymous">
                </head>
                """;
    }

    public String divTemplate() {
        return """
                <div class="card text-white bg-dark mb-3" style="max-width: 18rem;">
                	<h4 class="card-header">%s</h4>
                	<div class="card-body">
                		<img class="card-img" src="%s" alt="%s">
                		<p class="card-text mt-2">Nota: %s - Ano: %s</p>
                	</div>
                </div>""";
    }

    @Override
    public void close() throws IOException {
        this.printer.close();
    }

    public URI getFilePath() {
        return URI.create("file:".concat(filePath));
    }
}
