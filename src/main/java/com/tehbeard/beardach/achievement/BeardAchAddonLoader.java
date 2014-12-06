package com.tehbeard.beardach.achievement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.datasource.configurable.IConfigurable;
import com.tehbeard.utils.addons.AddonLoader;

public class BeardAchAddonLoader extends AddonLoader<IConfigurable> {

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

            if (manifest != null) {
                BeardAch.getLogger().debug("Addon manifest located");

                Scanner scanner;

                scanner = new Scanner(addon.getInputStream(manifest));
                while (scanner.hasNext()) {
                    String ln = scanner.nextLine();
                    String[] l = ln.split("=");
                    if (l[0].equalsIgnoreCase("name")) {
                        BeardAch.getLogger().debug("Loading addon {0}", l[1]);
                    } else if (l[0].equalsIgnoreCase("class")) {
                        classList.add(l[1]);
                    }
                }
                scanner.close();
            }

        } catch (IOException e) {
            BeardAch.getLogger().error("[ERROR] " + "An I/O error occured while trying to access an addon. " + addon.getName());
            if (BeardAch.getConfig().getBoolean("general.debug")) {
                e.printStackTrace();
            }
        }
        return classList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void makeClass(Class<? extends IConfigurable> classType) {
        if (classType != null) {
            if (ITrigger.class.isAssignableFrom(classType)) {
                BeardAch.triggersMetric++;
                BeardAch.addTrigger((Class<? extends ITrigger>) classType);
            } else if (IReward.class.isAssignableFrom(classType)) {
                BeardAch.rewardsMetric++;
                BeardAch.addReward((Class<? extends IReward>) classType);
            }
        }
    }

}
