package model;

import interfaces.ShowInfo;

import java.util.ArrayList;
import java.util.List;

public class Users extends Person implements ShowInfo {
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

    public void showFavoriteGame(List<Games> gamesList) {
        for (Integer gameId : this.favoriteGames) {
            for (Games game : gamesList) {
                if (game.getGameId() == gameId) {
                    game.showInfo();
                }
            }
        }
    }

    @Override
    public void showInfo() {
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
    }

    public void showComments(List<Games> gamesList, Users usuarioLogeado) {
        gamesList.forEach(game -> {
            game.getComments().forEach(comment -> {
                if(comment.getUserId().equals(usuarioLogeado.getUserId())) {
                    System.out.println("Comment: " + comment.getComment());
                }
            });
        });
    }
}