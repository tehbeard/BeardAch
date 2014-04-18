CREATE TABLE IF NOT EXISTS `${PREFIX}_entity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player` char(16) NOT NULL,
  `uuid` char(32),
  PRIMARY KEY (`id`),
  UNIQUE (`player`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE IF NOT EXISTS `${PREFIX}_link` (
  `playerid` int(11) NOT NULL,
  `slug` varchar(255) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 CREATE TABLE IF NOT EXISTS `${PREFIX}_map` (
 `slug` char(255) NOT NULL,
 `name` char(255) NOT NULL,
 `description` char(255) NOT NULL,
 UNIQUE KEY `slug` (`slug`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;