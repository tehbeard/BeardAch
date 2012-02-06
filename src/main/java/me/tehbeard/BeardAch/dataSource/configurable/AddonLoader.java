package me.tehbeard.BeardAch.dataSource.configurable;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;

/**
 * Loads achievements triggers and rewards from a directory of JAR's
 */
public class AddonLoader {

	private File dir;
	private BeardAch plugin;
	private URLClassLoader loader;
	public AddonLoader(File dir, BeardAch plugin) {
		this.dir = dir;
		this.plugin = plugin;
		if (!dir.isDirectory()) {
			throw new IllegalStateException("dir must be a directory!");
		}
	}

	@SuppressWarnings("unchecked")
	public void loadAddons() {
		// get list of files
		String[] flist = dir.list(new FilenameFilter() {

			public boolean accept(File file, String filename) {
				return !(file.isFile() && filename.endsWith(".jar"));
			}
		});
		HashSet<String> classList = new HashSet<String>();
		ZipFile addon;
		URL[] urls = new URL[flist.length];
		int i =0;
		BeardAch.printCon(":: "+flist.length);
		for (String file : flist) {
			try{
				File addonFile = new File(dir, file);

				urls[i] = addonFile.toURI().toURL();
				i++;
				BeardAch.printCon(addonFile.toURI().toURL().toString());

				addon = new ZipFile(addonFile);
				ZipEntry manifest = addon.getEntry("achaddon.yml");
				if (manifest != null) {
					YamlConfiguration addonConfig = new YamlConfiguration();
					addonConfig.load(addon.getInputStream(manifest));
					BeardAch.printCon("Loading addon " + addonConfig.getString("name","N/A"));
					for(String className:addonConfig.getStringList("classes")){
						classList.add(className);
					}
				}	
			} catch (ZipException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		loader = new URLClassLoader(urls,getClass().getClassLoader());
		// add to triggers/rewards
		try {


			for(String t :classList){

				Class<?> c = loader.loadClass(t);
				if(c!=null){
					if(ITrigger.class.isAssignableFrom(c)){
						plugin.addTrigger((Class<? extends ITrigger>) c);
					}else if(IReward.class.isAssignableFrom(c)){
						plugin.addReward((Class<? extends IReward>) c);
					}
				}

			}



		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
