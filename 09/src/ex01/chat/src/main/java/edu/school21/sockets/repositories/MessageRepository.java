package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class MessageRepository implements CrudRepository<Message>{
    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    @Autowired
    public MessageRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    @Override
    public int save(Message entity) {
        jdbcTemplate.update("INSERT INTO massage (text, user_id) VALUES (? , ?)", entity.getText(), entity.getAuthor().getId());
        return 1;
    }

    @Override
    public void update(Message entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
