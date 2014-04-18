#Pre generating table;
#!sql/makeTable;
#Generating list of players from old table;
INSERT IGNORE INTO ${PREFIX}_entity (`player`,`uuid`) SELECT DISTINCT(`player`),null as c FROM `achievements`;
#Importing achievement data from old table;
INSERT IGNORE INTO ${PREFIX}_link (`playerid`,`slug`,`timestamp`) SELECT ${PREFIX}_entity.id,`achievement`,`timestamp` FROM achievements JOIN ${PREFIX}_entity ON ${PREFIX}_entity.player = achievements.player;