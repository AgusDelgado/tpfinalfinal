package model;

import java.util.ArrayList;
import java.util.List;

public class Games {
    private static int idCounter = 1;
    private final int gameId;
    private String name;
    private Genre genre;
    private Integer developerId;
    private String publisher;
    private List<Comments> comments;
    private double price;
    private double overallRating;

    public Games(String name, Genre genre, Integer developerId, String publisher, double price, double overallRating) {
        this.gameId = idCounter++;
        this.name = name;
        this.genre = genre;
        this.developerId = developerId;
        this.comments = new ArrayList<>();
        this.publisher = publisher;
        this.price = price;
        this.overallRating = Math.max(0, Math.min(5, overallRating));
    }

    public Games(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void addComment( Comments comment) {
        this.comments.add(comment);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(double overallRating) {
        this.overallRating = overallRating;
    }

}
