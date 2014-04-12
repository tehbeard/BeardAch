#Pre generating table
#!sql/makeTable
#Generating list of players from old table;
INSERT INTO ${PREFIX}_entity (`player`) SELECT DISTINCT(`player`) FROM `achievements`;
#Importing achievement data from old table;
INSERT INTO ${PREFIX}_link (`playerid`,`slug`,`timestamp`)
SELECT ${PREFIX}_entity.id,`achievement`,`timestamp` FROM achievements,${PREFIX}_entity
WHERE ${PREFIX}_entity.player = achievements.player