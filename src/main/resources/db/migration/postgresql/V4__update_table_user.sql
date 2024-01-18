ALTER TABLE user
  ADD pix_key varchar(255) DEFAULT NULL,
  ADD cpf varchar(11) DEFAULT NULL,
  ADD birth date DEFAULT NULL,
  ADD reputation double DEFAULT NULL,
  ADD telephone varchar(11) DEFAULT NULL,
  ADD document_photo bigint DEFAULT NULL,
  ADD user_photo bigint DEFAULT NULL,
  ADD UNIQUE KEY (cpf),
  ADD UNIQUE KEY (pix_key),
  ADD FOREIGN KEY (user_photo) REFERENCES `photo` (id),
  ADD FOREIGN KEY (document_photo) REFERENCES `photo` (id);