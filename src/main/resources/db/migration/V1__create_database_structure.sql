CREATE TABLE Accounts
(
    Account_ID      SERIAL PRIMARY KEY,
    Document_Number VARCHAR(255) NOT NULL
);

CREATE TABLE OperationTypes
(
    OperationType_ID SERIAL PRIMARY KEY,
    Description      VARCHAR(255) NOT NULL
);

INSERT INTO OperationTypes (OperationType_ID, Description)
VALUES (1, 'COMPRA A VISTA'),
       (2, 'COMPRA PARCELADA'),
       (3, 'SAQUE'),
       (4, 'PAGAMENTO');

CREATE TABLE Transactions
(
    Transaction_ID   SERIAL PRIMARY KEY,
    Account_ID       INTEGER NOT NULL,
    OperationType_ID INTEGER NOT NULL,
    Amount           DOUBLE PRECISION NOT NULL,
    EventDate        TIMESTAMP NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (Account_ID) REFERENCES Accounts (Account_ID),
    CONSTRAINT fk_operation_type FOREIGN KEY (OperationType_ID) REFERENCES OperationTypes (OperationType_ID)
);