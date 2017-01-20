# --- First database schema

# --- !Ups

# -- CREATE DATABASE "playshiro"
# --     WITH
# --     OWNER = icinga
# --     ENCODING = 'UTF8'
# --     LC_COLLATE = 'en_US.UTF-8'
# --     CONNECTION LIMIT = -1;
# --
# -- GRANT ALL ON DATABASE "playshiro" TO icinga;

CREATE TABLE public.subject_data
(
    id bigint NOT NULL,
    nickname character varying(64) NOT NULL,
    password character varying(256),
    PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.subject_data
    OWNER to icinga;

# --- !Downs
