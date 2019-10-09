CREATE TABLE `trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `summary` TEXT DEFAULT NULL,
  `from` datetime(6) DEFAULT NULL,
  `to` datetime(6) DEFAULT NULL,
  `thumbnail` bigint(20),
  `online` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;