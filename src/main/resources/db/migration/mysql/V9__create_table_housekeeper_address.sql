CREATE TABLE `cleaner_address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `neighborhood` varchar(30) NOT NULL,
  `zip_code` varchar(8) NOT NULL,
  `city` varchar(30) NOT NULL,
  `complement` varchar(255) DEFAULT NULL,
  `state` varchar(2) NOT NULL,
  `street` varchar(60) NOT NULL,
  `number` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
);

ALTER TABLE `user`
  ADD `address_id` bigint DEFAULT NULL,
  ADD KEY (`address_id`),
  ADD CONSTRAINT FOREIGN KEY (`address_id`) REFERENCES `cleaner_address` (`id`);