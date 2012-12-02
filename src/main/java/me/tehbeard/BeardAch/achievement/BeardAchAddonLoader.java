package me.tehbeard.BeardAch.achievement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
            ZipEntry manifest = addon.getEntry("bundle.properties");

            if(manifest!=null){
                BeardAch.printCon("Loading addon");

                Scanner scanner;

                scanner = new Scanner(addon.getInputStream(manifest));
                while(scanner.hasNext()){
                    String ln = scanner.nextLine();
                    String[] l = ln.split("=");
                    if(l[0].equalsIgnoreCase("name")){
                        BeardAch.printCon("Loading addon " + l[1]);
                    }else if(l[0].equalsIgnoreCase("class")){
                        classList.add(l[1]);
                    }
                }
                scanner.close();
            }   

        } catch (IOException e) {
            BeardAch.printError("An I/O error occured while trying to access an addon. " + addon.getName());
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
