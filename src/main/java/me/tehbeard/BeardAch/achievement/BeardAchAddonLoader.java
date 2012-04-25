package me.tehbeard.BeardAch.achievement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.AbstractDataSource;
import me.tehbeard.BeardAch.dataSource.configurable.IConfigurable;
import me.tehbeard.utils.addons.AddonLoader;

public class BeardAchAddonLoader extends  AddonLoader<IConfigurable> {
    
    public int addonTriggersMetric = 0;
    public int addonRewardsMetric = 0;
    
    public BeardAchAddonLoader(File dir) {
        super(dir, IConfigurable.class);
    }

    @Override
    public List<String> getClassList(ZipFile addon) {
        List<String> classList = new ArrayList<String>();
        try {
            ZipEntry manifest = addon.getEntry("achaddon.yml");
            if (manifest != null) {
                YamlConfiguration addonConfig = new YamlConfiguration();

                addonConfig.load(addon.getInputStream(manifest));

                BeardAch.printCon("Loading addon " + addonConfig.getString("name","N/A"));
                for(String className:addonConfig.getStringList("classes")){
                    classList.add(className);
                }
            }
        } catch (IOException e) {
            BeardAch.printCon("[ERROR] An I/O error occured while trying to access an addon. " + addon.getName());
            if(BeardAch.self.getConfig().getBoolean("general.debug")){
                e.printStackTrace();
            }
        } catch (InvalidConfigurationException e) {
            BeardAch.printCon("[ERROR] Configuration header for "+ addon.getName() + " appears to be corrupt");
            if(BeardAch.self.getConfig().getBoolean("general.debug")){
                e.printStackTrace();
            }
        }
        return classList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void makeClass(Class<? extends IConfigurable> classType) {
        if(classType!=null){
            if(ITrigger.class.isAssignableFrom(classType)){
                BeardAch.triggersMetric ++;
                AbstractDataSource.triggerFactory.addProduct((Class<? extends ITrigger>) classType);
            }else if(IReward.class.isAssignableFrom(classType)){
                BeardAch.rewardsMetric ++; 
                AbstractDataSource.rewardFactory.addProduct((Class<? extends IReward>) classType);
            }
        }
    }


}
