DROP TABLE IF EXISTS user_table;
CREATE TABLE user_table
(
    id serial PRIMARY KEY,
    user_id varchar(255) unique not null,
    user_name varchar(255) not null,
    user_email varchar(255) unique not null,
    user_password varchar(255) not null
);