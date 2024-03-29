CREATE SEQUENCE service_seq;

CREATE TABLE service (
  id bigint NOT NULL DEFAULT NEXTVAL ('service_seq'),
  name varchar(50) NOT NULL,
  min_value decimal(19,2) NOT NULL,
  num_hours int NOT NULL,
  percentage_commission decimal(19,2) NOT NULL,
  bedroom_hours int NOT NULL,
  bedroom_value decimal(19,2) NOT NULL,
  living_room_hours int NOT NULL,
  living_room_value decimal(19,2) NOT NULL,
  bathroom_hours int NOT NULL,
  bathroom_value decimal(19,2) NOT NULL,
  kitchen_hours int NOT NULL,
  kitchen_value decimal(19,2) NOT NULL,
  backyard_hours int NOT NULL,
  backyard_value decimal(19,2) NOT NULL,
  others_hours int NOT NULL,
  others_value decimal(19,2) NOT NULL,
  position int NOT NULL,
  PRIMARY KEY (id)
);