CREATE TABLE `rating` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `nota` double NOT NULL,
  `visibility` bit(1) NOT NULL,
  `rated_id` bigint NOT NULL,
  `reviewer_id` bigint DEFAULT NULL,
  `daily_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`rated_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`daily_id`) REFERENCES `daily` (`id`),
  FOREIGN KEY (`reviewer_id`) REFERENCES `user` (`id`)
);