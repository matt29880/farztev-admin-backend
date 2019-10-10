CREATE TABLE `trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `summary` TEXT DEFAULT NULL,
  `start` datetime(6) DEFAULT NULL,
  `end` datetime(6) DEFAULT NULL,
  `thumbnail` bigint(20),
  `online` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;