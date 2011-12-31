package me.tehbeard.BeardAch.dataSource;


import java.sql.Connection;
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


public class SqlDataSource extends AbstractDataSource{

	private HashMap<String,HashSet<String>> writeCache = new HashMap<String,HashSet<String>>();
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
			prepGetAllPlayerAch = conn.prepareStatement("SELECT `achievement` FROM `achievements`,`timestamp` WHERE `player`=?");
			prepAddPlayerAch = conn.prepareStatement("INSERT INTO `achievements` (`player` ,`achievement`) values (?,?)",Statement.RETURN_GENERATED_KEYS);
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
					writeCache = new HashMap<String,HashSet<String>>();
					Bukkit.getScheduler().scheduleAsyncDelayedTask(BeardAch.self, r);
				}

			}, 2400L, 2400L);
			BeardAch.printCon("Initaised MySQL Data Provider.");
		} catch (ClassNotFoundException e) {
			BeardAch.printCon("MySQL Library not found!");
		}
	}




	public HashSet<String> getPlayersAchievements(String player) {

		try {
			prepGetAllPlayerAch.setString(1, player);
			ResultSet rs = prepGetAllPlayerAch.executeQuery();
			HashSet<String> c = new HashSet<String>();
			while(rs.next()){
				c.add(rs.getString(1));
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
			writeCache.put(player, new HashSet<String>());
		}
		writeCache.get(player).add(achievements);

	}


	class SqlFlusher implements Runnable {

		private HashMap<String, HashSet<String>> write;
		SqlFlusher(HashMap<String,HashSet<String>> toWrite){
			write = toWrite;
		}

		public void run() {
			BeardAch.printCon("Flushing to database");
			
			try {
				//if connection is closed, attempt to rebuild connection
				
					for( Entry<String, HashSet<String>> es :write.entrySet()){

						prepAddPlayerAch.setString(1, es.getKey());

						for(String val : es.getValue()){
							prepAddPlayerAch.setString(2,val);
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
		writeCache = new HashMap<String,HashSet<String>>();

	}


	public void clearAchievements(String player) {
		// TODO Auto-generated method stub

	}
}
