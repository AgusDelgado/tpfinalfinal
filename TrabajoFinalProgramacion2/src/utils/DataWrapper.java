package utils;

import model.Administrator;
import model.Developers;
import model.Games;
import model.Users;

import java.util.List;

public class DataWrapper {
    private List<Users> users;
    private List<Games> games;
    private Administrator admin;

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public List<Developers> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developers> developers) {
        this.developers = developers;
    }

    private List<Developers> developers;

    public DataWrapper() {
    }

    public DataWrapper(List<Users> users, List<Games> games, Administrator admin, List<Developers> developers) {
        this.users = users;
        this.games = games;
        this.admin = admin;
        this.developers = developers;
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