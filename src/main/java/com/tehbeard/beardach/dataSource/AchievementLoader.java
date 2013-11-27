package com.tehbeard.beardach.datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Location;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.datasource.json.ClassBasedParser;
import com.tehbeard.beardach.datasource.json.ClassCatalogue;
import com.tehbeard.beardach.datasource.json.CuboidJSONParser;
import com.tehbeard.beardach.datasource.json.LocationJSONParser;
import com.tehbeard.utils.cuboid.Cuboid;

/**
 * Loads achievements from an external Gson file
 * 
 * @author James
 * 
 */
public class AchievementLoader {

    public static final ClassCatalogue<ITrigger> triggerFactory = new ClassCatalogue<ITrigger>();
    public static final ClassCatalogue<IReward> rewardFactory = new ClassCatalogue<IReward>();

    /**
     * Create prime Gson object, Only export annotated fields Pretty print for
     * human debugging. Also adds type adapters for trigger, reward and location
     */
    private static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().registerTypeAdapter(ITrigger.class, new ClassBasedParser<ITrigger>(triggerFactory))
            .registerTypeAdapter(IReward.class, new ClassBasedParser<IReward>(rewardFactory)).registerTypeAdapter(Cuboid.class, new CuboidJSONParser()).registerTypeAdapter(Location.class, new LocationJSONParser()).create();

    private static List<Achievement> loadAchievementsFromJSONFile(File file) {

        try {
            return gson.fromJson(new FileReader(file), new TypeToken<List<Achievement>>() {
            }.getType());
        } catch (JsonIOException e) {
            BeardAch.printError("An error occured reading " + file.toString(), e);
        } catch (JsonSyntaxException e) {
            BeardAch.printError("There is a problem with the syntax of " + file.toString(), e);
        } catch (FileNotFoundException e) {
            BeardAch.printError(file.toString() + " not found", e);
        }
        return null;
    }

    public static void loadAchievements() {

        try {
            // Load and create file
            File file = new File(BeardAch.instance().getDataFolder(), "ach.json");
            file.createNewFile();
            List<Achievement> achievements = loadAchievementsFromJSONFile(file);
            processAchievements(achievements);

            File achDir = new File(BeardAch.instance().getDataFolder(), "config");
            if (achDir.isDirectory() && achDir.exists()) {
                for (String f : achDir.list(new FilenameFilter() {

                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".json");
                    }
                })) {
                    achievements = loadAchievementsFromJSONFile(new File(achDir, f));
                    processAchievements(achievements);
                }
            }

        } catch (JsonIOException e) {
            BeardAch.printError("An error occured reading ach.json", e);

        } catch (JsonSyntaxException e) {
            BeardAch.printError("There is a problem with the syntax of ach.json", e);
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            BeardAch.printError("ach.json not found", e);
        } catch (IOException e) {
            BeardAch.printError("An error occured reading ach.json", e);
            e.printStackTrace();
        }
    }

    // Cycle each Achievement and postload as needed
    private static void processAchievements(List<Achievement> achievements) {
        if (achievements != null) {
            // Run postLoad() on all achievements and add them to manager if
            // successful
            for (Achievement a : achievements) {
                if (a.postLoad()) {
                    BeardAch.instance().getLogger().log(Level.CONFIG, "Starting achievement {0}", a.getName());
                    BeardAch.instance().getAchievementManager().addAchievement(a);
                } else {
                    BeardAch.instance().getLogger().warning("Could not load " + a.getName());
                }
            }
        }
    }

}
