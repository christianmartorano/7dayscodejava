package br.alura.daysofcode.classes;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class will be responsible to parse the string response received by the API
 */
public class ResponseParser {

    public MovieList ExtractInformation(String[] keys, String json) {
        String[] movies =  ExtractList(json);
        return GetMovieList(movies, keys);
    }
    private String[] ExtractList(String json) throws IllegalArgumentException {
        Matcher matcher = Pattern.compile(".*\\[(.*)].*").matcher(json);
        if (!matcher.matches()) throw new IllegalArgumentException("No match in ".concat(json));
        return matcher.group(1).split("},\\{");
    }

    private MovieList GetMovieList(String[] movies, String[] searchKey) {
        MovieList movieList = new MovieList();
        for (String movie: movies) {
            Map<String, String> map = new HashMap<>();
            for (String key: searchKey) {
                Object[] params = new Object[]{key};
                map.put(key, movie.split(MessageFormat.format("\"{0}\":\"", params))[1].split("\",")[0]);
            }
            movieList.addMovie(map);
        }
        movieList.showMovies();
        return movieList;
    }

}
