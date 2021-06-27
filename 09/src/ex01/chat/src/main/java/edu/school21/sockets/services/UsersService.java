package edu.school21.sockets.services;

public interface UsersService {
    int signUp(String login, String password);
    int signIn(String login, String password);
    public void saveMessage(String login, String text);
}
