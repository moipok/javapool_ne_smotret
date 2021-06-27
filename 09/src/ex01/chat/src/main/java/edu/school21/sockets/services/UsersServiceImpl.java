package edu.school21.sockets.services;

import edu.school21.sockets.models.Chatroom;
import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.MessageRepository;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class UsersServiceImpl implements UsersService{
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageRepository messageRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository,
                            PasswordEncoder passwordEncoder,
                            MessageRepository messageRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageRepository = messageRepository;
    }

    public void saveMessage(String login, String text)
    {
        messageRepository.save(new Message(null, usersRepository.findByEmail(login).get(),
                null, text, null));
    }

    @Override
    public int signUp(String login, String password) {
        return usersRepository.save(new User(
                0L,
                login,
                passwordEncoder.encode(password),
                new ArrayList<Chatroom>(),
                new ArrayList<Chatroom>()
        ));
    }

    @Override
    public int signIn(String login, String password) {
        Optional<User> user = usersRepository.findByEmail(login);
        if (!user.isPresent())
            return 1;
        else
        {
            if (passwordEncoder.matches(password, user.get().getPassword()))
                return 0;
            else return 1;
        }
    }
}
