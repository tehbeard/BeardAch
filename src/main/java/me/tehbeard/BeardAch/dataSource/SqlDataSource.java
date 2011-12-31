package me.tehbeard.BeardAch.dataSource;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Properties;

import org.bukkit.Bukkit;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.AchievementPlayerLink;


public class SqlDataSource extends AbstractDataSource{

	private HashMap<String,HashSet<AchievementPlayerLink>> writeCache = new HashMap<String,HashSet<AchievementPlayerLink>>();
	Connection conn;


	//protected static PreparedStatement prepGetPlayerStat;
	protected static PreparedStatement prepGetAllPlayerAch;
	protected static PreparedStatement prepAddPlayerAch;


	protected void createConnection(){
		String conUrl = String.format("jdbc:mysql://%s/%s",
				BeardAch.config.getString("ach.database.host"), 
				BeardAch.config.getString("ach.database.database"));
		BeardAch.printCon("Configuring....");
		Properties conStr = new Properties();
		conStr.put("user",BeardAch.config.getString("ach.database.username",""));
		conStr.put("password",BeardAch.config.getString("ach.database.password",""));
		conStr.put("autoReconnect","true");
		conStr.put("maxReconnects","600");
		BeardAch.printCon("Connecting....");
		try {
			conn = DriverManager.getConnection(conUrl,conStr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void checkAndBuildTable(){
		try{
			BeardAch.printCon("Checking for table");
			ResultSet rs = conn.getMetaData().getTables(null, null, "achievements", null);
			if (!rs.next()) {
				BeardAch.printCon("Achievements table not found, creating table");
				PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS `achievements` ( `player` varchar(32) NOT NULL,  `achievement` varchar(255) NOT NULL,  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  UNIQUE KEY `player` (`player`,`achievement`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;");
				ps.executeUpdate();
				ps.close();
				BeardAch.printCon("created table");
			}
			else
			{
				BeardAch.printCon("Table found");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void prepareStatements(){
		BeardAch.printDebugCon("Preparing statements");
		try{
			prepGetAllPlayerAch = conn.prepareStatement("SELECT `achievement`,`timestamp` FROM `achievements` WHERE player=?");
			prepAddPlayerAch = conn.prepareStatement("INSERT INTO `achievements` (`player` ,`achievement`,`timestamp`) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public SqlDataSource() {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			createConnection();
			checkAndBuildTable();
			prepareStatements();


			Bukkit.getScheduler().scheduleSyncRepeatingTask(BeardAch.self, new Runnable(){
				public void run() {
					Runnable r = new SqlFlusher(writeCache);
					writeCache = new HashMap<String,HashSet<AchievementPlayerLink>>();
					Bukkit.getScheduler().scheduleAsyncDelayedTask(BeardAch.self, r);
				}

			}, 2400L, 2400L);
			BeardAch.printCon("Initaised MySQL Data Provider.");
		} catch (ClassNotFoundException e) {
			BeardAch.printCon("MySQL Library not found!");
		}
	}




	public HashSet<AchievementPlayerLink> getPlayersAchievements(String player) {

		try {
			prepGetAllPlayerAch.setString(1, player);
			ResultSet rs = prepGetAllPlayerAch.executeQuery();
			HashSet<AchievementPlayerLink> c = new HashSet<AchievementPlayerLink>();
			while(rs.next()){
				c.add(new AchievementPlayerLink(rs.getString(1),rs.getDate(2)));
				
			}
			rs.close();
			if(writeCache.containsKey(player)){
				c.addAll(writeCache.get(player));
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void setPlayersAchievements(String player,
			String achievements) {


		if(!writeCache.containsKey(player)){
			writeCache.put(player, new HashSet<AchievementPlayerLink>());
		}
		writeCache.get(player).add(new AchievementPlayerLink(achievements,new Date((new java.util.Date()).getTime())));

	}


	class SqlFlusher implements Runnable {

		private HashMap<String, HashSet<AchievementPlayerLink>> write;
		SqlFlusher(HashMap<String, HashSet<AchievementPlayerLink>> writeCache){
			write = writeCache;
		}

		public void run() {
			BeardAch.printCon("Flushing to database");
			
			try {
				//if connection is closed, attempt to rebuild connection
				
					for( Entry<String, HashSet<AchievementPlayerLink>> es :write.entrySet()){

						prepAddPlayerAch.setString(1, es.getKey());

						for(AchievementPlayerLink val : es.getValue()){
							prepAddPlayerAch.setString(2,val.getSlug());
							prepAddPlayerAch.setDate(3,val.getDate());
							prepAddPlayerAch.addBatch();
						}
					}
					prepAddPlayerAch.executeBatch();
					prepAddPlayerAch.clearBatch();
					BeardAch.printCon("Flushed to database");
				
				
			} catch (SQLException e) {
				BeardAch.printCon("Connection Could not be established, attempting to reconnect...");
				createConnection();
				prepareStatements();
			}
		}

	}

	public void flush() {
		(new SqlFlusher(writeCache)).run();
		writeCache = new HashMap<String,HashSet<AchievementPlayerLink>>();

	}


	public void clearAchievements(String player) {
		// TODO Auto-generated method stub

	}
}
