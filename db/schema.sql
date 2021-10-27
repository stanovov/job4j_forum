CREATE TABLE posts(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    author_id INT REFERENCES users(id) NOT NULL
);

CREATE TABLE comments(
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    post_id INT REFERENCES posts(id) NOT NULL,
    author_id INT REFERENCES users(id) NOT NULL
);