-- liquibase formatted sql

-- changeset grabovsky.alexey:add_category_in_db
insert into categories (name)
values ('Food');

-- changeset grabovsky.alexey:add_product_in_db
insert into products (title, description, price, rating, category_id)
values ('Milk', 'Fresh Milk', 98.02, 0, 1),
       ('Bread', 'Fresh Bread', 23.07, 0, 1);

-- changeset grabovsky.alexey:add_roles_and_autorities_in_db
INSERT INTO users (email, password, username, enabled, activated, activation_code)
VALUES ('admin@test.ru', '$2a$12$0lCh0ZBnMJzs0gnluRi1q.00DLal0ILpBWg7xUPlfYv7aKdMQUvPW', 'admin', true, true,
        'dsfafdas9fdsa9f-sadfsa8f9asdf-asdf'),
       ('user@test.ru', '$2a$12$0lCh0ZBnMJzs0gnluRi1q.00DLal0ILpBWg7xUPlfYv7aKdMQUvPW', 'user', true, true,
        'dsfafdas9fdsa9f-sadfsa8f9asdf-asdf');
INSERT INTO roles (role, description)
VALUES ('ROLE_USER', 'Зарегистрированный пользователь'),
       ('ROLE_SELLER', 'Продавец'),
       ('ROLE_MODERATOR', 'Модератор'),
       ('ROLE_ADMIN', 'Администратор');
INSERT INTO authorities (authority, description)
VALUES ('editProfile', 'Редактирование профиля'),         -- 1
       ('addProduct', 'Добавление товара'),              -- 2
       ('editProduct', 'Редактирование товара'),         -- 3
       ('deleteProduct', 'Удаление товара'),             -- 4
       ('addCategory', 'Добавление категории'),          -- 5
       ('editCategory', 'Редактирование категории'),     -- 6
       ('deleteCategory', 'Удаление категории'),         -- 7
       ('addReview', 'Добавление отзыва'),               -- 8
       ('editReview', 'Редактирование отзыва'),          -- 9
       ('deleteReview', 'Удаление отзыва'),              -- 10
       ('changeOrderStatus', 'Изменение статуса заказа') -- 11
;
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 4),
       (2, 1);

INSERT INTO roles_authorities (role_id, authority_id)
VALUES (1, 1),
       (1, 8),
       (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 11),
       (3, 1),
       (3, 9),
       (3, 10),
       (4, 1),
       (4, 2),
       (4, 3),
       (4, 4),
       (4, 5),
       (4, 6),
       (4, 7),
       (4, 8),
       (4, 9),
       (4, 10),
       (4, 11);
