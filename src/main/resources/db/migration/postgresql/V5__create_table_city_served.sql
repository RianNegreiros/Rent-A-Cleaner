CREATE SEQUENCE city_served_seq;

CREATE TABLE city_server (
  id bigint NOT NULL DEFAULT NEXTVAL ('city_served_seq'),
  city varchar(255) NOT NULL,
  ibge_code varchar(255) NOT NULL,
  state varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE cities_served_users (
  city_server_id bigint NOT NULL,
  user_id bigint NOT NULL
  CREATE INDEX (user_id)
  CREATE INDEX (city_server_id),
  CONSTRAINT FOREIGN CREATE INDEX (city_server_id) REFERENCES `city_server` (id),
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES `user` (id)
);