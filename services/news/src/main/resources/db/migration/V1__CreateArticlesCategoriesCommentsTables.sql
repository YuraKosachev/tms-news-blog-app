CREATE TABLE articles
(
    id          UUID                        NOT NULL,
    title       VARCHAR(255)                NOT NULL,
    description VARCHAR(255)                NOT NULL,
    content     TEXT                        NOT NULL,
    author_id   UUID,
    author_name VARCHAR(255),
    origin_link VARCHAR(255),
    category_id UUID,
    created_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_articles PRIMARY KEY (id)
);

CREATE TABLE categories
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

CREATE TABLE comments
(
    id           UUID                        NOT NULL,
    author_id    UUID                        NOT NULL,
    author       VARCHAR(255)                NOT NULL,
    content      TEXT                        NOT NULL,
    publish_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    article_id   UUID                        NOT NULL,
    CONSTRAINT pk_comments PRIMARY KEY (id)
);

ALTER TABLE articles
    ADD CONSTRAINT FK_ARTICLES_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE SET NULL ;

ALTER TABLE comments
    ADD CONSTRAINT FK_COMMENTS_ON_ARTICLE FOREIGN KEY (article_id) REFERENCES articles (id) ON DELETE CASCADE ;