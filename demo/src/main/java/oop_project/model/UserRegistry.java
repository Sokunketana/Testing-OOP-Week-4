package oop_project.model;

import java.util.ArrayList;

public class UserRegistry {
    private final ArrayList<User> users;

    public UserRegistry() {
        this.users = new ArrayList<>();
    }

    public void registerUser(String username, String password, String role) {
        User newUser = new User(username, password, role);
        users.add(newUser);
    }

    public boolean validateLogin(String inputUsername, String inputPassword) {
        for (User u : users) {
            if (u.getUsername().equals(inputUsername) && u.getPassword().equals(inputPassword)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }   
        return null;
    } 
}
