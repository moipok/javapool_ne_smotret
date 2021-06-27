package edu.school21.chat.app;


import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;


public class Program {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        MessagesRepository mess = new MessagesRepositoryJdbcImpl(dataSource);


        User creator = new User(2L, "user_!@21", "user", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom room = new Chatroom(1L, "room", creator, new ArrayList());
        Message message = new Message(null, author, room, null, LocalDateTime.now());

        mess.save(message);
        System.out.println(message.getId());

        Optional<Message> messageOptional = mess.findById(12L);
        if (messageOptional.isPresent()) {
            Message message1 = messageOptional.get();
            message1.setText("121212211212");
            message1.setDate(LocalDateTime.now());
            message1.setAuthor(creator);
            mess.update(message1);
        }
        try {
            System.out.println(mess.findById(12L).get().toString());
        } catch (Exception e) {
            System.out.println("null");
        }
        System.exit(1);
    }
}
