CREATE TABLE jobs
(
    id                      BIGSERIAL PRIMARY KEY,
    client_id               BIGSERIAL REFERENCES clients,
    job_reference_id        VARCHAR(100) NOT NULL,
    title                   VARCHAR(100) NOT NULL,
    forecast_commission     BIGDECIMAL(22, 4),
    created_at              TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at              TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);