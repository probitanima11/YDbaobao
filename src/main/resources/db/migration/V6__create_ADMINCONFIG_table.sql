CREATE TABLE `ADMINCONFIG` (
  `adminConfigId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `adminDisplayProducts` int(11) DEFAULT '16',
  PRIMARY KEY (`adminConfigId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `ADMINCONFIG` (`adminConfigId`, `adminDisplayProducts`) VALUES (0, 16);
