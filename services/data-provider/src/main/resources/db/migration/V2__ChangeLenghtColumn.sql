ALTER TABLE processed_data
    DROP COLUMN description;

ALTER TABLE processed_data
    DROP COLUMN title;

ALTER TABLE processed_data
    ADD description VARCHAR(2000);

ALTER TABLE processed_data
    ADD title VARCHAR(2000) NOT NULL;