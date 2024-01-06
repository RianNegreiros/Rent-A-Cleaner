CREATE TABLE user (
  `id` bigint NOT NULL AUTO_INCREMENT
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` varchar(8) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`email`)
);