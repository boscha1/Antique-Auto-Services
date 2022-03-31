CREATE TABLE customer(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL
);

CREATE TABLE car(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    customer_id INT NOT NULL,
    code        VARCHAR NOT NULL,
    make        VARCHAR NOT NULL,
    model       VARCHAR NOT NULL,
    year        int4 NOT NULL,
    notes       VARCHAR,
    FOREIGN KEY (customer_id)
        REFERENCES customer (id) ON DELETE CASCADE
);

CREATE TABLE billing_info(
    id                    BIGSERIAL NOT NULL PRIMARY KEY,
    car_id                INT NOT NULL,
    hourly_rate           FLOAT NOT NULL,
    materials_percentage  FLOAT NOT NULL,
    insurance_rate        FLOAT NOT NULL,
    first_invoice         DATE,
    first_invoice_mailed  BOOLEAN NOT NULL,
    second_invoice        DATE,
    second_invoice_mailed BOOLEAN NOT NULL,
    FOREIGN KEY (car_id)
        REFERENCES car(id) ON DELETE CASCADE
);