CREATE DATABASE IF NOT EXISTS webhook_db;
USE webhook_db;

CREATE TABLE IF NOT EXISTS webhook_event (
                                             event_id VARCHAR(256) NOT NULL,
                                             event_type VARCHAR(64) NOT NULL,
                                             provider VARCHAR(64) NOT NULL,
                                             provider_tenant_id BIGINT,
                                             payload LONGTEXT,
                                             status VARCHAR(64) NOT NULL,
                                             partition_key VARCHAR(64),
                                             PRIMARY KEY (event_id)
);
SELECT * FROM webhook_event;