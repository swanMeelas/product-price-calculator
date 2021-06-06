USE `product`;
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `unitsPerCarton` varchar(45) DEFAULT NULL,
  `cartonPrice` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

LOCK TABLES `item` WRITE;

INSERT INTO `item` VALUES (1,'Penguin','20',175),(2,'Horse Shoe','5',825);
UNLOCK TABLES;