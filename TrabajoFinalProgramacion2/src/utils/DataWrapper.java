package utils;

import model.Games;
import model.Users;

import java.util.List;

public class DataWrapper {
    private List<Users> users;
    private List<Games> games;

    public DataWrapper() {
        // Default constructor for Jackson
    }

    public DataWrapper(List<Users> users, List<Games> games) {
        this.users = users;
        this.games = games;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Games> getGames() {
        return games;
    }

    public void setGames(List<Games> games) {
        this.games = games;
    }
}