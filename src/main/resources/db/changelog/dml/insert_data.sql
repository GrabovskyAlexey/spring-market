-- liquibase formatted sql

-- changeset grabovsky.alexey:add_category_in_db
insert into categories (name)
values ('Food');

-- changeset grabovsky.alexey:add_product_in_db
insert into products (title, description, price, rating, category_id)
values ('Milk', 'Fresh Milk', 98.02, 0, 1),
('Bread', 'Fresh Bread', 23.07, 0, 1);
