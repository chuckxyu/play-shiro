# --- First database schema

# --- !Ups

CREATE DATABASE "playshiro"
    WITH
    OWNER = icinga
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    CONNECTION LIMIT = -1;

GRANT ALL ON DATABASE "playshiro" TO icinga;

# --- !Downs

drop table if exists user;
