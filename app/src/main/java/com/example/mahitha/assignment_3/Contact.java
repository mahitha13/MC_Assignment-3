package com.example.mahitha.assignment_3;

/**
 * Created by Mahitha on 01/10/2016.
 */
public class Contact {
    int id;
    String username, email, name, password;

    public void setName(String name) {
        this.name = name;
    }
    public void setId(){
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
