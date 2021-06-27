package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {
    public static List<ServerSomthing> serverList = new LinkedList<>();
    private UsersService usersService;

    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    public void start(int PORT)
    {
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server Started");
            try {
                while (true) {
                    Socket socket = server.accept();
                    try {
                        serverList.add(new ServerSomthing(socket, usersService, serverList));
                    } catch (IOException e) {
                        socket.close();
                    }
                }
            } finally {
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class ServerSomthing extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    UsersService usersService;
    private static List<ServerSomthing> serverList;

    public ServerSomthing(Socket socket, UsersService usersService, List<ServerSomthing> serverList)
            throws IOException {
        this.usersService = usersService;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    private void signUp() {
        String login;
        String pass;

        try {
            while (true) {
                send("Inter login:");
                login = in.readLine();
                send("Inter password:");
                pass = in.readLine();
                if (usersService.signUp(login, pass) == 0){
                    send("Successful!");
                    break;
                }
                send("try again:");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String signIn(){
        String pass;
        String login;

        try {
            while (true) {
                send("Inter login:");
                login = in.readLine();
                send("Inter password:");
                pass = in.readLine();
                if (usersService.signIn(login, pass) == 0) {
                    send("Successful!");
                    return login;
                }
                else
                    send("Invalid login or password!");
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void messenger(String login)
    {
        send("Messenger starting");
        String word;
        while (true)
        {
            try {
                word = in.readLine();
                usersService.saveMessage(login,word);
                if (word.equals("Exit"))
                    break;
                for (ServerSomthing vr : Server.serverList) {
                    vr.send(login + ": " + word);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        String word;
        try {
            try {
                while (true) {
                    send("Hello from Server!");
                    while (true){
                    word = in.readLine();
                    if (word.equals("signUp")) {
                        signUp();
                    }
                    else if (word.equals("signIn"))
                    {
                        String login = signIn();
                        if (!login.isEmpty()) {
                            messenger(login);
                            send("stop");
                            downService();
                            break;
                        }
                        else send("Try again!");
                    }
                    else if (word.equals("Exit"))
                        break;
                    else send("Invalid command! Try again!");
                    }
                    send("stop");
                    downService();
                }
            } catch (NullPointerException ignored) {}

        } catch (IOException e) {
            this.downService();
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}

    }

    private void downService() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {}
    }
}
