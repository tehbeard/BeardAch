package com.tehbeard.beardach.datasource;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.AchievementPlayerLink;
import com.tehbeard.beardach.annotations.DataSourceDescriptor;
import com.tehbeard.utils.sql.DBVersion;
import com.tehbeard.utils.sql.JDBCDataSource;
import com.tehbeard.utils.sql.PostUpgrade;
import com.tehbeard.utils.sql.SQLFragment;
import com.tehbeard.utils.uuid.MojangWebAPI;

/**
 * 
 * @author James
 */
@DataSourceDescriptor(tag = "mysql", version = "2.1")
public class JDBCAchievementDataSource extends JDBCDataSource implements IDataSource {

    private HashMap<String, HashSet<AchievementPlayerLink>> writeCache = new HashMap<String, HashSet<AchievementPlayerLink>>();

    @SQLFragment("player.get")
    private PreparedStatement prepGetAllPlayerAch;

    @SQLFragment("player.set")
    private PreparedStatement prepAddPlayerAch;

    @SQLFragment("ping")
    private PreparedStatement ping;

    @SQLFragment("player.list")
    private PreparedStatement getPlayerList;

    @SQLFragment("player.uuid.set")
    private PreparedStatement setPlayerUUID;

    @SQLFragment("player.name.set")
    private PreparedStatement setPlayerName;

    public JDBCAchievementDataSource() throws ClassNotFoundException, IOException, SQLException {
        super("sql", "com.mysql.jdbc.Driver", BeardAch.instance().getLogger());
        Configuration cfg = BeardAch.instance().getConfig();
        setConnectionUrl(String.format("jdbc:mysql://%s/%s", cfg.getString("ach.database.host"), cfg.getString("ach.database.database")));
        Properties auth = new Properties();
        auth.put("user", cfg.getString("ach.database.username"));
        auth.put("password", cfg.getString("ach.database.password"));
        setTag("PREFIX", cfg.getString("ach.database.table_prefix"));
        setConnectionProperties(auth);

        Properties p = new Properties();
        p.load(BeardAch.instance().getResource("sql/sql.properties"));
        setSqlFragments(p);
        setup();

        //v1 to v2 check
        ResultSet rs = connection.getMetaData().getTables(connection.getCatalog(), null, cfg.getString("ach.database.table_prefix") + "_entity", null);
        if(!rs.next()){
            BeardAch.instance().getLogger().info("No entity table detected, performing upgrade.");
            if(doMigration(1, 2)){
                BeardAch.instance().getConfig().set("ach.database.sql_db_version", 2);
                BeardAch.instance().saveConfig();
            }
        }

        executeScript("sql/makeTable");

        //TODO: Add migration check here.





        Bukkit.getScheduler().scheduleSyncRepeatingTask(BeardAch.instance(), new Runnable() {
            @Override
            public void run() {
                Runnable r = new SqlFlusher(getWriteCache());
                writeCache = new HashMap<String, HashSet<AchievementPlayerLink>>();
                Bukkit.getScheduler().runTaskAsynchronously(BeardAch.instance(), r);
            }

        }, 2400L, 2400L);
    }

    protected synchronized HashMap<String, HashSet<AchievementPlayerLink>> getWriteCache(){
        return writeCache;
    }

    @Override
    protected boolean generateBackup(File file) {
        return true;
        // throw new UnsupportedOperationException("Not supported yet."); //To
        // change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected boolean restoreBackup(File file) {
        return true;
        // throw new UnsupportedOperationException("Not supported yet."); //To
        // change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected File getTempDir() {
        return new File(System.getProperty("java.io.tmpdir"));
    }

    @Override
    protected String getMigrationScriptPath(int toVersion) {
        return "sql/migrate/v" + toVersion;
    }

    @Override
    public Set<AchievementPlayerLink> getPlayersAchievements(OfflinePlayer player) {
        HashSet<AchievementPlayerLink> c = new HashSet<AchievementPlayerLink>();
        try {
            if (!checkConnection())
                throw new SQLException("Failed to reconnect to server, aborting load");
            prepGetAllPlayerAch.setString(1, player.getUniqueId().toString().replaceAll("-",""));
            ResultSet rs = prepGetAllPlayerAch.executeQuery();

            while (rs.next()) {
                c.add(new AchievementPlayerLink(rs.getString(1), rs.getTimestamp(2)));

            }
            rs.close();
            if (writeCache.containsKey(player)) {
                c.addAll(writeCache.get(player));
            }
            return c;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized void setPlayersAchievements(OfflinePlayer player, String achievements) {
        if (!writeCache.containsKey(player.getUniqueId().toString().replaceAll("-",""))) {
            writeCache.put(player.getUniqueId().toString().replaceAll("-",""), new HashSet<AchievementPlayerLink>());
        }
        writeCache.get(player.getUniqueId().toString().replaceAll("-","")).add(new AchievementPlayerLink(achievements, new Timestamp(new java.util.Date().getTime())));
    }

    @Override
    public synchronized void flush() {
        new SqlFlusher(writeCache).run();
        writeCache = new HashMap<String, HashSet<AchievementPlayerLink>>();
    }

    @Override
    public void clearAchievements(OfflinePlayer player) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void dumpFancy() {
        try {
            // conn.prepareStatement("DELETE FROM `ach_map`").execute();
            PreparedStatement fancyStat = connection.prepareStatement("INSERT IGNORE INTO `ach_map` values (?,?,?)");

            fancyStat.clearBatch();

            for (Achievement ach : BeardAch.instance().getAchievementManager().getAchievementsList()) {
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

    private synchronized boolean checkConnection() {
        BeardAch.instance().getLogger().fine("Checking connection");
        try {
            if (connection == null || !connection.isValid(0)) {
                BeardAch.instance().getLogger().warning("Connection to database interuptted, attempting to reconnect...");
                setup();
                if (connection == null) {
                    BeardAch.instance().getLogger().severe("Connection reboot failed!");
                }

            }
        } catch (SQLException e) {
            connection = null;
            return false;
        } catch (AbstractMethodError e) {

        }
        BeardAch.instance().getLogger().fine("Checking is " + connection != null ? "up" : "down");
        return connection != null;
    }

    class SqlFlusher implements Runnable {

        private HashMap<String, HashSet<AchievementPlayerLink>> write;

        SqlFlusher(HashMap<String, HashSet<AchievementPlayerLink>> writeCache) {
            write = writeCache;
        }

        @Override
        public void run() {
            if (BeardAch.instance().getConfig().getBoolean("general.verbose", false)) {
                BeardAch.instance().getLogger().fine("Flushing to database");
            }
            try {
                // if connection is closed, attempt to rebuild connection
                if (!checkConnection())
                    throw new SQLException("Failed to reconnect to SQL server");

                ping.execute();

                for (Map.Entry<String, HashSet<AchievementPlayerLink>> es : write.entrySet()) {

                    prepAddPlayerAch.setString(1, es.getKey());

                    for (AchievementPlayerLink val : es.getValue()) {
                        prepAddPlayerAch.setString(2, val.getSlug());
                        prepAddPlayerAch.setTimestamp(3, val.getDate());
                        prepAddPlayerAch.addBatch();
                    }
                }
                prepAddPlayerAch.executeBatch();
                prepAddPlayerAch.clearBatch();
                if (BeardAch.instance().getConfig().getBoolean("general.verbose", false)) {
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

    @Override
    public List<String> getPlayers() {
        try {
            ResultSet rs = getPlayerList.executeQuery();
            List<String> players = new ArrayList<String>();
            while(rs.next()){
                players.add(rs.getString(1));
            }
            return players;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    /**
     * Patch UUID in database post upgrade
     */
    @DBVersion(2)
    @PostUpgrade
    public void _doPostUUIDFix(){
        try {
            for(Entry<String, UUID> entry: MojangWebAPI.lookupUUIDS(getPlayers()).entrySet()){
                setPlayerUUID.setString(1, entry.getValue().toString().replaceAll("-", ""));
                setPlayerUUID.setString(2, entry.getKey());
                setPlayerUUID.executeUpdate();
                
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }


}
