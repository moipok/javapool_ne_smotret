package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl implements UsersRepository {
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        if (id == null)
            return Optional.empty();
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM users WHERE id =?",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public int save(User entity) {
        if (!findByEmail(entity.getLogin()).isPresent())
            jdbcTemplate.update("INSERT INTO users (login, password) VALUES (? , ?)", entity.getLogin(), entity.getPassword());
        else return 1;
        return 0;
    }

    @Override
    public void update(User entity) {
        if (findByEmail(entity.getLogin()).isPresent())
            jdbcTemplate.update("UPDATE users SET login=?, password=? WHERE id =?",
                    entity.getLogin(), entity.getPassword(), entity.getId());
        else System.err.println("(save) User not found");;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id =?", id);
    }

    @Override
    public Optional<User> findByEmail(String login) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM users WHERE login =?",
                new Object[]{login}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null));
    }
}

