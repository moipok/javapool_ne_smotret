package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository{
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM users WHERE id =?",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null));
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        if (!findById(entity.getID()).isPresent())
            jdbcTemplate.update("INSERT INTO users VALUES (? , ?)", entity.getID(), entity.getEmail());
        else System.err.println("(save) User already in table");;
    }

    @Override
    public void update(User entity) {
        if (findById(entity.getID()).isPresent())
            jdbcTemplate.update("UPDATE users SET email=? WHERE id =?", entity.getEmail(), entity.getID());
        else System.err.println("(save) User not found");;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id =?", id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM users WHERE email =?",
                new Object[]{email}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null));
    }
}

