package model;

import java.util.ArrayList;
import java.util.List;

public class Users extends Person {
    private static int idCounter = 1;
    private final int userId;
    private List<Integer> favoriteGames;
    private List<Integer> comments;

    public Users(String username, String password, String email) {
        super(username, password, email);
        this.userId = idCounter;
        Users.idCounter += 1;
        this.favoriteGames = new ArrayList<>();
        this.comments = new ArrayList<>();
    }


    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getFavoriteGames() {
        return favoriteGames;
    }

    public void addFavoriteGame(Integer gameId) {
        this.favoriteGames.add(gameId);
    }

    public List<Integer> getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments.add(comments);
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    //metodos


}