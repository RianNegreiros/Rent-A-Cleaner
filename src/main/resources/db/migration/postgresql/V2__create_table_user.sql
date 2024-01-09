CREATE SEQUENCE user_seq;

CREATE TABLE user (
  id bigint NOT NULL DEFAULT NEXTVAL ('user_seq'),
  email varchar(255) NOT NULL,
  fullName varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  user_type varchar(8) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email)
);