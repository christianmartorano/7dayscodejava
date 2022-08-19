package br.alura.daysofcode.classes;

import br.alura.daysofcode.interfaces.Content;

/**
 * This is the Movie class
 */
public record Movie(String title, String urlImage, float rating, int year) implements Content {

    @Override
    public int compareTo(Content content) {

        int rating = Math.round(content.rating());

        return (int) this.rating().compareTo(rating);
    }

}
