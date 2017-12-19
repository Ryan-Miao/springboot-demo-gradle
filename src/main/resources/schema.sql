DROP TABLE if EXISTS  `user`;
CREATE TABLE `user`(
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(8) NOT NULL UNIQUE,
  `name` VARCHAR(12),
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;