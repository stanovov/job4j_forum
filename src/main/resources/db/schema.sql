CREATE TABLE IF NOT EXISTS authorities(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled BOOLEAN DEFAULT true,
    authority_id INT NOT NULL,
    FOREIGN KEY (authority_id) REFERENCES authorities(id)
);

CREATE TABLE IF NOT EXISTS posts(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    author_id INT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS comments(
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    post_id INT NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(id),
    FOREIGN KEY (author_id) REFERENCES users(id)
);