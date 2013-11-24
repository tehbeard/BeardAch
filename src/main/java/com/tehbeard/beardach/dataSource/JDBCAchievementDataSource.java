package com.tehbeard.beardach.dataSource;

import com.tehbeard.utils.sql.JDBCDataSource;
import com.tehbeard.utils.sql.SQLFragment;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;

/**
 *
 * @author James
 */
public class JDBCAchievementDataSource extends JDBCDataSource implements IDataSource{
    
    private HashMap<String,HashSet<AchievementPlayerLink>> writeCache = new HashMap<String,HashSet<AchievementPlayerLink>>();
    
    @SQLFragment("player.get")
    private PreparedStatement prepGetAllPlayerAch;
    
    @SQLFragment("player.set")
    private PreparedStatement prepAddPlayerAch;
    
    @SQLFragment("ping")
    private PreparedStatement ping;

    public JDBCAchievementDataSource(String host,String database,String username,String password) throws ClassNotFoundException, IOException, SQLException {
        super("sql","com.mysql.jdbc.Driver", Logger.getGlobal());
        setConnectionUrl(String.format("jdbc:mysql://%s/%s",host,database));
        
        Properties auth = new Properties();
        auth.put("user",username);
        auth.put("password",password);
        setConnectionProperties(auth);
                
        Properties p = new Properties();
        p.load(getClass().getClassLoader().getResourceAsStream("sql.properties"));
        setSqlFragments(p);
        setup();
        executeScript("makeTable");
    }

    @Override
    protected boolean generateBackup(File file) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean restoreBackup(File file) {
        return true;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected File getTempDir() {
        return new File(System.getProperty("java.io.tmpdir"));
   }

    @Override
    protected String getMigrationScriptPath(int fromVersion, int toVersion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Set<AchievementPlayerLink> getPlayersAchievements(String player) {
        HashSet<AchievementPlayerLink> c = new HashSet<AchievementPlayerLink>();
        try {
        	if(!checkConnection()){
        		throw new SQLException("Failed to reconnect to server, aborting load");
        	}
            prepGetAllPlayerAch.setString(1, player);
            ResultSet rs = prepGetAllPlayerAch.executeQuery();

            while(rs.next()){
                c.add(new AchievementPlayerLink(rs.getString(1),rs.getTimestamp(2)));

            }
            rs.close();
            if(writeCache.containsKey(player)){
                c.addAll(writeCache.get(player));
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

     public void setPlayersAchievements(String player, String achievements) {
        if(!writeCache.containsKey(player)){
            writeCache.put(player, new HashSet<AchievementPlayerLink>());
        }
        writeCache.get(player).add(new AchievementPlayerLink(achievements,new Timestamp((new java.util.Date()).getTime())));
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearAchievements(String player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dumpFancy() {
        try {
            //conn.prepareStatement("DELETE FROM `ach_map`").execute();
            PreparedStatement fancyStat = connection.prepareStatement("INSERT IGNORE INTO `ach_map` values (?,?,?)");

            fancyStat.clearBatch();


            for(Achievement ach : BeardAch.instance().getAchievementManager().getAchievementsList()){
                fancyStat.setString(1, ach.getSlug());
                fancyStat.setString(2, ach.getName());
                fancyStat.setString(3, ach.getDescrip());
                fancyStat.addBatch();
            }
            fancyStat.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
     private synchronized boolean checkConnection(){
		BeardAch.instance().getLogger().fine("Checking connection");
		try {
			if(connection == null || !connection.isValid(0)){
				BeardAch.instance().getLogger().warning("Connection to database interuptted, attempting to reconnect...");
				setup();
				if(connection==null)
				{
					BeardAch.instance().getLogger().severe("Connection reboot failed!");
				}

			}
		} catch (SQLException e) {
			connection = null;
			return false;
		}catch(AbstractMethodError e){

		}
		BeardAch.instance().getLogger().fine("Checking is " + connection != null ? "up" : "down");
		return connection != null;
	}

    
    class SqlFlusher implements Runnable {

        private HashMap<String, HashSet<AchievementPlayerLink>> write;
        SqlFlusher(HashMap<String, HashSet<AchievementPlayerLink>> writeCache){
            write = writeCache;
        }

        public void run() {
            if(BeardAch.instance().getConfig().getBoolean("general.verbose",false)){
                BeardAch.instance().getLogger().fine("Flushing to database");
            }
            try {
                //if connection is closed, attempt to rebuild connection
            	if(!checkConnection()){
            		throw new SQLException("Failed to reconnect to SQL server");
            	}
            	
            	ping.execute();

                for( Map.Entry<String, HashSet<AchievementPlayerLink>> es :write.entrySet()){

                    prepAddPlayerAch.setString(1, es.getKey());

                    for(AchievementPlayerLink val : es.getValue()){
                        prepAddPlayerAch.setString(2,val.getSlug());
                        prepAddPlayerAch.setTimestamp(3,val.getDate());
                        prepAddPlayerAch.addBatch();
                    }
                }
                prepAddPlayerAch.executeBatch();
                prepAddPlayerAch.clearBatch();
                if(BeardAch.instance().getConfig().getBoolean("general.verbose",false)){
                    BeardAch.instance().getLogger().fine("Flushed to database");
                }


            } catch (SQLException e) {
                BeardAch.instance().getLogger().fine("Connection Could not be established, attempting to reconnect...");
                e.printStackTrace();
                try {
                    setup();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCAchievementDataSource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
