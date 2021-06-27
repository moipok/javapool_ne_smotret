package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private Long     id;
    private String      login;
    private String      password;
    private ArrayList<Chatroom> created_rooms;
    private ArrayList<Chatroom> used_rooms;

    public User() {
    }

    public User(Long id, String login, String password, ArrayList<Chatroom> created_rooms, ArrayList<Chatroom> used_rooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.created_rooms = created_rooms;
        this.used_rooms = used_rooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public ArrayList<Chatroom> getCreated_rooms() {
        return created_rooms;
    }

    public void setCreated_rooms(ArrayList<Chatroom> created_rooms) {
        this.created_rooms = created_rooms;
    }

    public ArrayList<Chatroom> getUsed_rooms() {
        return used_rooms;
    }

    public void setUsed_rooms(ArrayList<Chatroom> used_rooms) {
        this.used_rooms = used_rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login) && password.equals(user.password) && Objects.equals(created_rooms, user.created_rooms) && Objects.equals(used_rooms, user.used_rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, created_rooms, used_rooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", created_rooms=" + created_rooms +
                ", used_rooms=" + used_rooms +
                '}';
    }
}
