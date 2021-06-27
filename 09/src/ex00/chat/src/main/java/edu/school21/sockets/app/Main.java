package edu.school21.sockets.app;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("edu.school21.sockets")
public class Main {
    public static void main(String[] args) {
        if (args.length != 1 || args[0].indexOf("--port=") != 0)
            System.exit(1);
        int PORT = Integer.valueOf(args[0].split("=")[1]);
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        Server server = context.getBean(Server.class);

        server.start(PORT);
    }

}
