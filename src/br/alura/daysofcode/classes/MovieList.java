package br.alura.daysofcode.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This Class will contain all the Movies inside the List
 */
public class MovieList {

    private final List<Movie> movieList;

    public MovieList()
    {
        this.movieList = new ArrayList<>();
    }

    public void addMovie(Map<String, String> map) {
        this.movieList.add(new Movie(
                map.get("title"), map.get("image"), Float.parseFloat(map.get("imDbRating")),
                Integer.parseInt(map.get("year")))
        );
    }

    public void showMovies() {
        this.movieList.forEach(System.out::println);
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

}
