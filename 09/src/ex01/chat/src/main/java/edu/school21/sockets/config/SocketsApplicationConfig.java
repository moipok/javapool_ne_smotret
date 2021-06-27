package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.sockets.repositories.MessageRepository;
import edu.school21.sockets.repositories.UsersRepository;
import edu.school21.sockets.repositories.UsersRepositoryImpl;
import edu.school21.sockets.server.Server;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {
    private final Environment env;

    @Autowired
    public SocketsApplicationConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public Server server(){
        return new Server(usersService());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsersRepository usersRepository(){
        return new UsersRepositoryImpl(dataSource());
    }

    @Bean
    public MessageRepository messageRepository() {
        return new MessageRepository(dataSource());
    }
    @Bean
    public UsersService usersService(){
        return new UsersServiceImpl(usersRepository(), passwordEncoder(), messageRepository());
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setJdbcUrl(env.getProperty("db.url"));
        dataSource.setDriverClassName(env.getProperty("db.driver.name"));
        return dataSource;
    }
}