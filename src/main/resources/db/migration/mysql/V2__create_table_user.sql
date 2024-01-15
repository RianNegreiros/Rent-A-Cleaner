CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` enum('ADMIN','CLIENT','HOUSEKEEPER') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`email`)
)