CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE password
(
    password_id      uuid                     NOT NULL,
    encrypted_text   text NOT NULL,
    created_date     timestamp with time zone NOT NULL,
    last_updated     timestamp with time zone NOT NULL,
    created_by       text                     NOT NULL,
    last_modified_by text                     NOT NULL,
    version          bigint                   NOT NULL,
    PRIMARY KEY (password_id)
);

CREATE TABLE users
(
    user_id          uuid                     NOT NULL,
    dob              timestamp with time zone NOT NULL,
    email_address    text                     NOT NULL UNIQUE,
    first_name       text                     NOT NULL,
    is_active        boolean                  NOT NULL,
    last_name        text,
    phone_number     text                     NOT NULL UNIQUE,
    password_id      uuid,
    created_date     timestamp with time zone NOT NULL,
    last_updated     timestamp with time zone NOT NULL,
    created_by       text                     NOT NULL,
    last_modified_by text                     NOT NULL,
    version          bigint                   NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (password_id) REFERENCES password (password_id)
);

CREATE TABLE role
(
    role_id          uuid                     NOT NULL,
    type             text                     NOT NULL UNIQUE,
    description      text                     NOT NULL,
    created_date     timestamp with time zone NOT NULL,
    last_updated     timestamp with time zone NOT NULL,
    created_by       text                     NOT NULL,
    last_modified_by text                     NOT NULL,
    version          bigint                   NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE user_roles
(
    user_id uuid NOT NULL,
    role_id uuid NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_id) REFERENCES role (role_id)
);

