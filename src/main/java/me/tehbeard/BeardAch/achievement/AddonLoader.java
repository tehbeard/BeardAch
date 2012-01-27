package me.tehbeard.BeardAch.achievement;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;

/**
 * Loads achievements from a directory of JAR's
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
				return (file.isFile() && filename.endsWith(".jar"));
			}
		});
		HashSet<String> classList = new HashSet<String>();
		ZipFile addon;
		ArrayList<URL> urls = new ArrayList<URL>();
		for (String file : flist) {
			try{
				File addonFile = new File(dir, file);

				urls.add(addonFile.toURI().toURL());


				addon = new ZipFile(addonFile);
				ZipEntry manifest = addon.getEntry("BA.addon.properties");
				if (manifest != null) {
					Properties prop = new Properties();
					prop.load(addon.getInputStream(manifest));
					for (String className : prop.stringPropertyNames()) {

						classList.add(className);

					}
				}
			} catch (ZipException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		loader = new URLClassLoader((URL[])urls.toArray(),getClass().getClassLoader());
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
