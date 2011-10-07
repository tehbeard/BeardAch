package me.tehbeard.BeardAch.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import org.bukkit.Bukkit;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardStat.DataProviders.IStatDataProvider;
import me.tehbeard.BeardStat.DataProviders.MysqlStatDataProvider;
import me.tehbeard.BeardStat.containers.PlayerStat;
import me.tehbeard.BeardStat.containers.PlayerStatBlob;

public class SqlDataSource extends NullDataSource{

	private HashMap<String,HashSet<String>> writeCache = new HashMap<String,HashSet<String>>();
	Connection conn;


	//protected static PreparedStatement prepGetPlayerStat;
	protected static PreparedStatement prepGetAllPlayerAch;
	protected static PreparedStatement prepAddPlayerAch;





	/*
  try {

		} catch(SQLException e){
			BeardAch.printCon("Failed to initaise MySQL Data Provider. Dumping error.");
			e.printStackTrace();
			BeardAch.printCon("Shutting down BeardAch");
		}
	 */



	public SqlDataSource() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
				String conStr = String.format("jdbc:mysql://%s/%s",
				BeardAch.config.getString("ach.database.host"), 
				BeardAch.config.getString("ach.database.database"));

		BeardAch.printCon("Connecting....");
		conn = DriverManager.getConnection(conStr,
				BeardAch.config.getString("ach.database.username"),
				BeardAch.config.getString("ach.database.password"));

		BeardAch.printCon("Checking for table");

		ResultSet rs = conn.getMetaData().getTables(null, null, "achievements", null);
		if (!rs.next()) {
			BeardAch.printCon("Stats table not found, creating table");
			PreparedStatement ps = conn.prepareStatement("");
			ps.executeUpdate();
			ps.close();
			BeardAch.printCon("created table");
		}
		else
		{
			BeardAch.printCon("Table found");
		}
		rs.close();

		BeardAch.printDebugCon("Preparing statements");
		//prepGetPlayerStat = conn.prepareStatement("SELECT * FROM stats WHERE player=?");
		prepGetAllPlayerAch = conn.prepareStatement("SELECT `achievement` FROM `achievements` WHERE player=?");


		prepAddPlayerAch = conn.prepareStatement("INSERT INTO `achievements` values (?,?)",Statement.RETURN_GENERATED_KEYS);

		Bukkit.getScheduler().scheduleSyncRepeatingTask(BeardAch.self, new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				Runnable r = new sqlFlusher(writeCache);
				writeCache = new HashMap<String,HashSet<String>>();
				Bukkit.getScheduler().scheduleAsyncDelayedTask(BeardAch.self, r);
			}
			
		}, 2400L, 2400L);
		BeardAch.printCon("Initaised MySQL Data Provider.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			BeardAch.printCon("MySQL Library not found!");


		}catch (SQLException e){
			BeardAch.printCon("Something went derp.");
		}



	}




	public HashSet<String> getPlayersAchievements(String player) {
		// TODO Auto-generated method stub
		try {
			prepGetAllPlayerAch.setString(1, player);
			ResultSet rs = prepGetAllPlayerAch.executeQuery();
			HashSet<String> c = new HashSet<String>();
			while(rs.next()){
				c.add(rs.getString(1));
			}
			rs.close();
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public void setPlayersAchievements(String player,
			String achievements) {


		// TODO Auto-generated method stub
		if(!writeCache.containsKey(player)){
			writeCache.put(player, new HashSet<String>());
		}
		writeCache.get(player).add(achievements);

	}


	class sqlFlusher implements Runnable {

		private HashMap<String, HashSet<String>> write;
		sqlFlusher(HashMap<String,HashSet<String>> toWrite){
			 write = toWrite;
		}
		
		public void run() {
			BeardAch.printCon("Flushing to database");
			// TODO Auto-generated method stub
			try {
			for( Entry<String, HashSet<String>> es :write.entrySet()){
				
					prepAddPlayerAch.setString(1, es.getKey());
				
				for(String val : es.getValue()){
					prepAddPlayerAch.setString(2,val);
					prepAddPlayerAch.addBatch();
				}
			}
			prepAddPlayerAch.executeBatch();
			prepAddPlayerAch.clearBatch();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void flush() {
		// TODO Auto-generated method stub
		(new sqlFlusher(writeCache)).run();
		writeCache = new HashMap<String,HashSet<String>>();

	}
}
