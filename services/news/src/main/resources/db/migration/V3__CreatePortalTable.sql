CREATE TABLE portals
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    main_link   VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT pk_portals PRIMARY KEY (id)
);

ALTER TABLE articles
    ADD portal UUID;

ALTER TABLE articles
    ADD published_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE articles
    ALTER COLUMN published_at SET NOT NULL;

ALTER TABLE portals
    ADD CONSTRAINT UC_PORTALS_NAME UNIQUE (name);

ALTER TABLE articles
    ADD CONSTRAINT FK_ARTICLES_ON_PORTAL FOREIGN KEY (portal) REFERENCES portals (id) ON DELETE SET NULL;