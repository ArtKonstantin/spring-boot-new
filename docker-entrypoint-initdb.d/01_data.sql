INSERT INTO users(login, password)
VALUES (
        'vasya',
        '$argon2id$v=19$m=4096,t=3,p=1$yJI2kPfsUqP8vMF5dD+SIw$VO64AekHwudhuFlYusfubTyaeKZ9tJgmyv6SCsuYFEk'
        );

INSERT INTO user_roles(user_id, role) SELECT id, 'ROLE_USER' FROM users WHERE login = 'vasya';
INSERT INTO user_roles(user_id, role) SELECT id, 'ROLE_ADMIN' FROM users WHERE login = 'vasya';

INSERT INTO posts(author_id, name, content, geo_lat, geo_lng)
SELECT id, 'first', 'first post', 55.0, 45.0 FROM users WHERE login = 'vasya';