package com.example.nordwest.my_uni_app;

import java.io.Serializable;

public class User implements Serializable {

    private String login, password;

    //generated constructor for User class

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    //generated getters and setters for both private variables

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
