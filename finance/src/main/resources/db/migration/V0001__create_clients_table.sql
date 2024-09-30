CREATE TABLE clients
(
    id                  BIGSERIAL PRIMARY KEY,
    client_reference_id VARCHAR(100) NOT NULL UNIQUE,
    name                VARCHAR(100) NOT NULL,
    country_code        VARCHAR(5),
    currency_code       VARCHAR(5),
    created_at          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at          TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);
