INSERT INTO users (login, password) VALUES
('user1', 'pass1'), ('user2', 'pass2'), ('user3', 'pass3'), ('user4', 'pass4'), ('user5', 'pass5');

INSERT INTO chatroom (name, owner_id) VALUES
    ('chat1', 1), ('chat2', 2), ('chat3', 3), ('chat4', 2), ('chat5', 5);

INSERT INTO chatroom_to_user(user_id, chatroom_id) VALUES
    (1, 1), (2, 2), (4, 2), (3, 3), (5, 5), (4, 3), (1, 2);

INSERT INTO massage (user_id, room_id, text) VALUES
    (1, 2, 'text1'),
    (2, 2, 'text2'),
    (3, 2, 'text3'),
    (4, 3, 'text4'),
    (5, 5, 'wqqwwq');