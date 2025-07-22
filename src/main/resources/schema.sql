DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
    id BIGSERIAL NOT NULL,
    name varchar(100) NOT NULL UNIQUE,
    password_hash varchar(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id BIGSERIAL NOT NULL,
    role_name varchar(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);
