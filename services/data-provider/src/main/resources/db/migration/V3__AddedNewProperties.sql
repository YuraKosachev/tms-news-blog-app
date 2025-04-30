ALTER TABLE sources
    ADD absolute_link VARCHAR(255);

ALTER TABLE sources
    ADD main_link VARCHAR(255);

ALTER TABLE sources
    ADD portal VARCHAR(255);

ALTER TABLE sources
    ALTER COLUMN absolute_link SET NOT NULL;

ALTER TABLE processed_data
    ADD author VARCHAR(255);

ALTER TABLE processed_data
    ADD published_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE processed_data
    ALTER COLUMN author SET NOT NULL;

ALTER TABLE sources
    ALTER COLUMN main_link SET NOT NULL;

ALTER TABLE sources
    ALTER COLUMN portal SET NOT NULL;

ALTER TABLE processed_data
    ALTER COLUMN published_at SET NOT NULL;