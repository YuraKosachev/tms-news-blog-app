CREATE TABLE processed_data
(
    id          UUID                        NOT NULL,
    title       VARCHAR(255)                NOT NULL,
    content     TEXT                        NOT NULL,
    description VARCHAR(255),
    category    VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    source_id   UUID                        NOT NULL,
    sent_at     TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_processed_data PRIMARY KEY (id)
);

CREATE TABLE sources
(
    id                UUID                        NOT NULL,
    link              VARCHAR(255)                NOT NULL,
    task_id           UUID                        NOT NULL,
    status_code       INTEGER                     NOT NULL,
    status            SMALLINT                    NOT NULL,
    processed_data_id UUID,
    created_at        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_sources PRIMARY KEY (id)
);

CREATE TABLE tasks
(
    id       UUID                        NOT NULL,
    source   VARCHAR(255)                NOT NULL,
    start_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_at   TIMESTAMP WITHOUT TIME ZONE,
    status   SMALLINT                    NOT NULL,
    CONSTRAINT pk_tasks PRIMARY KEY (id)
);

ALTER TABLE processed_data
    ADD CONSTRAINT FK_PROCESSED_DATA_ON_SOURCE FOREIGN KEY (source_id) REFERENCES sources (id) ON DELETE CASCADE;

ALTER TABLE sources
    ADD CONSTRAINT FK_SOURCES_ON_PROCESSED_DATA FOREIGN KEY (processed_data_id) REFERENCES processed_data (id);

ALTER TABLE sources
    ADD CONSTRAINT FK_SOURCES_ON_TASK FOREIGN KEY (task_id) REFERENCES tasks (id) ON DELETE CASCADE;