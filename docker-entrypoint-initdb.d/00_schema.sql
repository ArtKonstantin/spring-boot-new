CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    created TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE posts(
                      id BIGSERIAL PRIMARY KEY,
                      name TEXT NOT NULL UNIQUE,
                      content TEXT NOT NULL,
                      created TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
