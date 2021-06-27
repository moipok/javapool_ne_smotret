package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.swing.text.html.Option;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl  implements MessagesRepository{
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    private void closeAll(Connection connection, PreparedStatement stmt, ResultSet rs) throws SQLException {
        rs.close();
        stmt.close();
        connection.close();
    }

    public Optional<Message> findById(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            if (connection != null) {
                PreparedStatement stmt;
                stmt = connection.prepareStatement("SELECT * FROM massage WHERE id =" + id);

                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    closeAll(connection, stmt, rs);
                    return null;
                }


                Message mes = new Message();
                mes.setId(rs.getLong(1));
                mes.setText(rs.getString(4));
                Timestamp time = rs.getTimestamp(5);
                if (time != null)
                    mes.setDate(rs.getTimestamp(5).toLocalDateTime());
                else
                    mes.setDate(null);

                Long user_id = rs.getLong(2);
                Long chatroomId = rs.getLong(3);

                stmt = connection.prepareStatement("SELECT * FROM users WHERE id = " + user_id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    User user = new User();
                    user.setId(user_id);
                    user.setLogin(rs.getString(2));
                    user.setPassword(rs.getString(3));
                    mes.setAuthor(user);
                }

                stmt = connection.prepareStatement("SELECT * FROM chatroom WHERE id = " + chatroomId);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    Chatroom room = new Chatroom();
                    room.setId(rs.getLong(1));
                    room.setName(rs.getString(2));

                    mes.setRoom(room);
                }

                closeAll(connection, stmt, rs);

                return Optional.of(mes);
            }
            else {
                System.out.println("not good");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

}
