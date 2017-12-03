create database if not exists springboot_demo charset utf8 collate utf8_general_ci;
use springboot_demo;

create table if not exists room (
  id INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `comment` VARCHAR(200),
  create_date DATETIME,
  update_date DATETIME,
  PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


