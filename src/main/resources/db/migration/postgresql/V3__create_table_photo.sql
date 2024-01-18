CREATE SEQUENCE photo_seq;

CREATE TABLE photo (
  id bigint NOT NULL DEFAULT NEXTVAL ('photo_seq'),
  content_length bigint NOT NULL,
  content_type varchar(255) NOT NULL,
  filename varchar(255) NOT NULL,
  url varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (filename)
);