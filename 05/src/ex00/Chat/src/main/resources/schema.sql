CREATE ROLE root WITH PASSWORD 'root' LOGIN SUPERUSER;
CREATE DATABASE chat WITH OWNER root;
GRANT ALL PRIVILEGES ON DATABASE chat TO root;

CREATE TABLE users (
                       id SERIAL PRIMARY KEY NOT NULL,
                       login VARCHAR UNIQUE NOT NULL,
                       password VARCHAR NOT NULL
);

CREATE TABLE chatroom (
                          id SERIAL PRIMARY KEY NOT NULL,
                          name VARCHAR UNIQUE NOT NULL,
                          owner_id INT NOT NULL,
                          CONSTRAINT fk_users1 FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE chatroom_to_user (
                                  user_id INT NOT NULL,
                                  chatroom_id INT NOT NULL,
                                  CONSTRAINT fk_users2 FOREIGN KEY(user_id) REFERENCES users(id),
                                  CONSTRAINT fk_chatroom1 FOREIGN KEY (chatroom_id) REFERENCES chatroom(id)
);

CREATE TABLE massage (
                         id SERIAL PRIMARY KEY NOT NULL,
                         user_id INT NOT NULL,
                         room_id INT NOT NULL,
                         text TEXT,
                         date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT fk_users3 FOREIGN KEY (user_id) REFERENCES users(id),
                         CONSTRAINT fk_chatroom2 FOREIGN KEY (room_id) REFERENCES chatroom(id)
);