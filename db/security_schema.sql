CREATE TABLE authorities(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN DEFAULT true,
    authority_id INT NOT NULL REFERENCES authorities(id)
);

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