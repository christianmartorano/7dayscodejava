package br.alura.daysofcode.interfaces;

public interface Content  extends Comparable<Content> {

    String title();

    String urlImage();

    float rating();

    int year();

    @Override
    int compareTo(Content content);

}
