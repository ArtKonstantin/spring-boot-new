CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    created TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_roles(
                           user_id BIGINT NOT NULL REFERENCES users,
                           role TEXT NOT NULL
);

CREATE TABLE posts(
                      id BIGSERIAL PRIMARY KEY,
                      author_id BIGINT NOT NULL REFERENCES users,
                      name TEXT NOT NULL UNIQUE,
                      content TEXT NOT NULL,
                      geo_lat FLOAT,
                      geo_lng FLOAT,
                      created TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE post_tags(
    post_id BIGINT NOT NULL REFERENCES posts,
    tag TEXT NOT NULL
);


