

CREATE TABLE IF NOT EXISTS `user` ( `id` INT NOT NULL AUTO_INCREMENT, `username` VARCHAR(45) NOT NULL,
 `password` VARCHAR(100) NOT NULL,
 PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS `authority` ( `id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`user` INT NOT NULL,
PRIMARY KEY (`id`));

DELETE FROM `user`;
DELETE FROM `authority`;
INSERT  INTO `user` (`id`, `username`, `password`) VALUES (1, 'john', '$2a$10$xn3LI/AjqicFYZFruSwve.681477XaVNaUQbr1gioaWPn4t1KsnmG');
INSERT  INTO `authority` (`id`, `name`, `user`) VALUES (1, 'READ', 1);
INSERT INTO `authority` (`id`, `name`, `user`) VALUES (2, 'WRITE', 1);


CREATE TABLE IF NOT EXISTS `product` ( `id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`price` DECIMAL NOT NULL, PRIMARY KEY (`id`));

DELETE from `product`;
INSERT  INTO `product` (`id`, `name`, `price`) VALUES (1, 'Chocolate', 10);
INSERT  INTO `product` (`id`, `name`, `price`) VALUES (2, 'Biscuit', 20);
