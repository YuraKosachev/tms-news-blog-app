ALTER TABLE articles
    DROP COLUMN author_name;

ALTER TABLE articles
    DROP COLUMN description;

ALTER TABLE articles
    DROP COLUMN origin_link;

ALTER TABLE articles
    DROP COLUMN title;

ALTER TABLE articles
    ADD author_name VARCHAR(400);

ALTER TABLE articles
    ADD description VARCHAR(2000);

ALTER TABLE articles
    ADD origin_link VARCHAR(2000);

ALTER TABLE articles
    ADD title VARCHAR(2000) NOT NULL;