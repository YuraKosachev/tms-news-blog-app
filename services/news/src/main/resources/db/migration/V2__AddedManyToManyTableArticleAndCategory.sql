CREATE TABLE category_article
(
    article_id  UUID NOT NULL,
    category_id UUID NOT NULL
);

ALTER TABLE category_article
    ADD CONSTRAINT FK_CATART_ON_ARTICLE FOREIGN KEY (article_id) REFERENCES articles (id);

ALTER TABLE category_article
    ADD CONSTRAINT FK_CATART_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE articles
    DROP CONSTRAINT fk_articles_on_category;

ALTER TABLE articles
    DROP COLUMN category_id;