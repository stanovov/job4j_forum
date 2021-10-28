INSERT INTO authorities (name) VALUES ('ROLE_USER');
INSERT INTO authorities (name) VALUES ('ROLE_ADMIN');

INSERT INTO users (username, password, enabled, authority_id)
VALUES ('admin', '$2a$10$LVto8ZK.4DtiZUDaCxo8eO4NRNiKCB8/7M4m.aClADDtjraeBxnpS', true,
        (SELECT id FROM authorities WHERE name = 'ROLE_ADMIN'));
INSERT INTO users (username, password, enabled, authority_id)
VALUES ('user', '$2a$10$Y8yfdaLsUw8xoHwMi6w4FuSQD/2TJJWY.PfkwEyrddzSztPjDSgxq', true,
        (SELECT id FROM authorities WHERE name = 'ROLE_USER'));
INSERT INTO users (username, password, enabled, authority_id)
VALUES ('spammer', '$2a$10$her8BFoosCBluua0dN0fpum.AeGsjej438qgJt4Sz9UkE8rXPTguy', true,
        (SELECT id FROM authorities WHERE name = 'ROLE_USER'));

INSERT INTO posts (name, description, author_id)
VALUES ('О чем этот форум', 'Этот форум создан с целью ознакомления и обучения с Spring BOOT', 1),
       ('Правила форума.', 'Спам на форуме запрещен', 1);

INSERT INTO posts(name, description, author_id)
VALUES ('Продаю гараж.', 'Хороший гараж', 2);

INSERT INTO comments(text, post_id, author_id)
VALUES ('Я первый!', 3, 3), ('И второй', 3, 3);