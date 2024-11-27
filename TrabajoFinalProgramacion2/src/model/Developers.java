package model;

import java.util.Random;

public class Developers extends Users {
    private String company;
    private final int followers;

    public Developers(String username, String password, String email, String company, int followers) {
        super(username, password, email);
        this.company = company;
        Random random = new Random();
        this.followers = random.nextInt(1_000_001);
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getFollowers() {
        return followers;
    }
}