CREATE TABLE `city_served` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `ibge_code` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cities_served_users` (
  `city_served_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY (`user_id`),
  KEY (`city_served_id`),
  CONSTRAINT FOREIGN KEY (`city_served_id`) REFERENCES `city_served` (`id`),
  CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);