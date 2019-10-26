CREATE TABLE trip_article (
  trip_id bigint(20) NOT NULL,
  article_id bigint(20) NOT NULL,
  CONSTRAINT trip_article_pk
  PRIMARY KEY (trip_id, article_id)
);