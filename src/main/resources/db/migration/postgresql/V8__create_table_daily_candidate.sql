CREATE TABLE daily_candidate (
  daily_id bigint NOT NULL,
  candidate_id bigint NOT NULL
  CREATE INDEX (candidate_id)
  CREATE INDEX (daily_id),
  CONSTRAINT FOREIGN CREATE INDEX (candidate_id) REFERENCES `user` (id),
  CONSTRAINT FOREIGN KEY (daily_id) REFERENCES `daily` (id)
);