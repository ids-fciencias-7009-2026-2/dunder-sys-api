CREATE DATABASE dundersys;

CREATE TABLE usuarios (
                          id          SERIAL PRIMARY KEY,
                          email       VARCHAR(255),
                          password    VARCHAR(255),
                          token       VARCHAR(255),
                          nombre      VARCHAR(255),
                          edad        INTEGER,
                          created_at  TIMESTAMP DEFAULT NOW(),
                          updated_at  TIMESTAMP DEFAULT NOW(),
                          deleted_at  TIMESTAMP
);