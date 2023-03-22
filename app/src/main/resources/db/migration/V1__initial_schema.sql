CREATE SCHEMA users;
CREATE SCHEMA restaurants;

CREATE TABLE users.user (
    id uuid PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE restaurants.restaurant (
    id uuid PRIMARY KEY,
    name VARCHAR NOT NULL
)
