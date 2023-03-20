-- changeset creating initial tables
-- Users table
CREATE TABLE users
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    UNIQUE (email)
);
CREATE TABLE accounts
(
    id             SERIAL PRIMARY KEY,
    user_id        INTEGER, -- should be related with table users.
    full_name      VARCHAR(20),
    account_number VARCHAR(20),
    routing_number VARCHAR(20),
    bank_name      VARCHAR(20),
    currency       VARCHAR(20),
    dni            VARCHAR(20),
    created_at TIMESTAMP      NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);
-- Wallets table
CREATE TABLE wallets
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER        NOT NULL REFERENCES users (id),
    balance    DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP      NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP,
    UNIQUE (user_id)
);

-- Transactions table
CREATE TABLE transactions
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER        NOT NULL, --REFERENCES users (id), should be related with table user
    wallet_transaction_id INTEGER NOT NULL,
    amount     DECIMAL(10, 2) NOT NULL,
    fee        DECIMAL(10,2)  NOT NULL,
    created_at TIMESTAMP      NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP
);

-- Payments table
CREATE EXTENSION "pgcrypto";
CREATE TABLE payments
(
    id             SERIAL PRIMARY KEY,
    code           UUID      NOT NULL,
    destination_id INTEGER   NOT NULL REFERENCES accounts (id),
    transaction_id INTEGER   NOT NULL REFERENCES transactions (id),
    status         VARCHAR(20) NOT NULL ,
    created_at     TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at     TIMESTAMP
);


INSERT INTO users (first_name, last_name, email, password, created_at, updated_at)
VALUES ('ON', 'TOP', 'ontop@ontop.com', 'password123', '2022-01-01 12:00:00', NULL),
       ('Jane', 'Doe', 'janedoe@gmail.com', 'password456', '2022-01-02 10:00:00', NULL),
       ('Bob', 'Smith', 'bobsmith@yahoo.com', 'password789', '2022-01-03 08:00:00', NULL),
       ('Alice', 'Johnson', 'alicejohnson@hotmail.com', 'passwordabc', '2022-01-04 14:00:00', NULL),
       ('Tom', 'Wilson', 'tomwilson@gmail.com', 'passworddef', '2022-01-05 16:00:00', NULL),
       ('Sara', 'Brown', 'sarabrown@yahoo.com', 'passwordghi', '2022-01-06 09:00:00', NULL),
       ('Jack', 'Davis', 'jackdavis@hotmail.com', 'passwordjkl', '2022-01-07 11:00:00', NULL),
       ('Amy', 'Lee', 'amylee@gmail.com', 'passwordmno', '2022-01-08 15:00:00', NULL),
       ('Kevin', 'Wang', 'kevinwang@yahoo.com', 'passwordpqr', '2022-01-09 13:00:00', NULL),
       ('Emily', 'Chen', 'emilychen@hotmail.com', 'passwordstu', '2022-01-10 17:00:00', NULL);

INSERT INTO wallets (user_id, balance, created_at)
VALUES
    (1, 1000.00, '2022-01-01 10:00:00'),
    (2, 2000.00, '2022-01-01 11:00:00'),
    (3, 500.00, '2022-01-01 11:00:00');

INSERT INTO accounts (user_id, full_name, account_number, routing_number, currency, bank_name, dni)
values (1,'ONTOP INC', '0245253419','028444018','USD','Bank of America','2589632');
