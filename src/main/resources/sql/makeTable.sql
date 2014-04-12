CREATE TABLE IF NOT EXISTS `achievements` ( 
`player` varchar(32) NOT NULL,
`achievement` varchar(255) NOT NULL,
`timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 KEY `player` (`player`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
 CREATE TABLE IF NOT EXISTS `ach_map` (
 `slug` char(255) NOT NULL,
 `name` char(255) NOT NULL,
 `description` char(255) NOT NULL,
 UNIQUE KEY `slug` (`slug`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;