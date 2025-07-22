INSERT INTO users (name, password_hash, email) VALUES
('admin', '$2a$12$Mj2A1T5OMvgODl6RSlIYMezrxHbJSg20.ERZTUyisUoq5gpGF0GfC', 'admin@gmail.com');

INSERT INTO roles (role_name) VALUES
('ROLE_guest'), ('ROLE_premium_user'), ('ROLE_admin');

INSERT INTO user_roles (user_id, role_id) VALUES
(1, 3)