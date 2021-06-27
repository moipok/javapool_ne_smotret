package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private DataSource dataSource;
    private static Connection connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            System.err.println("No connection =(");
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                user.setID(resultSet.getLong(1));
                user.setEmail(resultSet.getString(2));
                return Optional.of(user);
            }
            else
                System.err.println("(findById) User not found");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                User user = new User();

                user.setID(rs.getLong("id"));
                user.setEmail(rs.getString("email"));

                people.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }

    @Override
    public void save(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id =?");
            preparedStatement.setLong(1, entity.getID());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                System.err.println("(save) User already in table");
                return;
            }
            preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?,?)");
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setLong(1, entity.getID());
            preparedStatement.executeUpdate();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void update(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id =?");
            preparedStatement.setLong(1, entity.getID());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.err.println("(update) User not found");
                return;
            }

            preparedStatement = connection.prepareStatement("UPDATE users SET email =? WHERE id =?");
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.setLong(2, entity.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            int i = preparedStatement.executeUpdate();
            if (i == 0)
                System.err.println("(delete) User not found");
            else
                System.out.println("(delete) User deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                user.setID(resultSet.getLong(1));
                user.setEmail(email);
                return Optional.of(user);
            }
            else
                System.err.println("(findByEmail) User not found");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}
