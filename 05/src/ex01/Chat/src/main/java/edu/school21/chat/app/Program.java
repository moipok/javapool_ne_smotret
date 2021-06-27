package edu.school21.chat.app;


import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;
import java.util.Scanner;


public class Program {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        MessagesRepository mess = new MessagesRepositoryJdbcImpl(dataSource);
        Scanner scanner = new Scanner(System.in);

        for (; ; ) {
            System.out.print("Enter a message ID\n--->");
            Long a = scanner.nextLong();


            Optional<Message> test = mess.findById(a);
            try {
                System.out.println(mess.findById(a).get().toString());
            } catch (Exception e) {
                System.out.println("null");
            }
            if (a == 4242)
                break;
        }
        System.exit(0);
    }
}
