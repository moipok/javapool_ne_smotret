package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.swing.text.html.Option;
import java.sql.*;
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

    public void save(Message mes) {
        try {
            Connection connection = dataSource.getConnection();
            if (connection != null)
            {
                PreparedStatement stmt;
                if (mes.getAuthor().getId() == 0 || mes.getRoom().getId() == 0)
                    throw new NotSavedSubEntityException(0L);
                stmt = connection.prepareStatement("SELECT * FROM users WHERE id =" + mes.getAuthor().getId());
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    closeAll(connection, stmt, rs);
                    throw new NotSavedSubEntityException(mes.getAuthor().getId());
                }
                Long user_id = rs.getLong(1);

                stmt = connection.prepareStatement("SELECT * FROM chatroom WHERE id =" + mes.getRoom().getId());
                rs = stmt.executeQuery();
                if (!rs.next()) {
                    closeAll(connection, stmt, rs);
                    throw new NotSavedSubEntityException(mes.getAuthor().getId());
                }
                Long room_id = rs.getLong(1);
                String text = mes.getText();
                if (text == null)
                    text = new String("null");
                else
                    text = new String("\'" + text + "\'");
                String date;
                if (mes.getDate() == null)
                    date = "null";
                else {
                    date = Timestamp.valueOf(mes.getDate()).toString();
                    date = "\'" + date + "\'";
                }
                stmt = connection.prepareStatement("INSERT INTO massage (user_id, room_id, text, date) VALUES  ( " + user_id +
                        ", " + room_id + ", " + text + ", " + date +"  ) RETURNING id");
                rs = stmt.executeQuery();
                if (!rs.next()) {
                    closeAll(connection, stmt, rs);
                    throw new NotSavedSubEntityException(mes.getAuthor().getId());
                }
                mes.setId(rs.getLong(1));

                closeAll(connection, stmt, rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private String makeSQLupdate(Message mes)
    {
        String text = mes.getText();
        if (text == null)
            text = new String("null");
        else
            text = new String("\'" + text + "\'");
        String date;
        if (mes.getDate() == null)
            date = "null";
        else {
            date = Timestamp.valueOf(mes.getDate()).toString();
            date = "\'" + date + "\'";
        }

        String sql = new String("UPDATE massage " +
                " SET text = " + text+
                ", date = " + date +
                ", user_id = " + mes.getAuthor().getId() +
                " WHERE id = " + mes.getId() + ";");
        return sql;
    }

    public void update(Message mes) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE id =" + mes.getAuthor().getId());
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new NotSavedSubEntityException(mes.getAuthor().getId());
            }
            stmt.close();
            rs.close();
            stmt = connection.prepareStatement("SELECT * FROM massage WHERE id =" + mes.getId());
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new NotSavedSubEntityException(mes.getId());
            }
            stmt.close();
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return;
        }

        String sql = makeSQLupdate(mes);
        try {
            connection = dataSource.getConnection();
            System.out.println("1");
            Statement statement = connection.createStatement();
            System.out.println("2");
            int rows = statement.executeUpdate(sql);
            System.out.printf("Updated %d rows\n", rows);

        } catch (SQLException throwables) {
            System.out.println("Error connection\n");
        }

    }

}

class NotSavedSubEntityException extends RuntimeException{
    private Long id;
    public NotSavedSubEntityException(Long i)
    {
        this.id = i;
    }
    public void printStackTrace(){
        System.err.println("NotSavedSubEntityException - id = "+ id);
    }
}
