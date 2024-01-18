CREATE SEQUENCE payment_seq;

CREATE TABLE payment (
  id bigint NOT NULL DEFAULT NEXTVAL ('payment_seq'),
  created_at timestamp(6) NOT NULL,
  updated_at timestamp(6) NOT NULL,
  status varchar(20) NOT NULL,
  transaction_id varchar(255) NOT NULL,
  value decimal(19,2) NOT NULL,
  daily_id bigint NOT NULL,
  PRIMARY KEY (id)
  CREATE INDEX (daily_id),
  CONSTRAINT FOREIGN CREATE INDEX (daily_id) REFERENCES `daily` (id)
);