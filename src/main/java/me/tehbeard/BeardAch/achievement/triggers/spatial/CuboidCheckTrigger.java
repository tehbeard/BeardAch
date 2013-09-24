package me.tehbeard.BeardAch.achievement.triggers.spatial;

import java.util.ArrayList;

import me.tehbeard.BeardAch.BeardAch;
import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorFieldType;
import me.tehbeard.utils.cuboid.Cuboid;


import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

/**
 * Checks if a players is in a cuboid
 * @author James
 *
 */
@Configurable(tag="cuboid",name="cuboid")
public class CuboidCheckTrigger implements ITrigger {


    @Expose
    @EditorField(alias="cuboid",type=EditorFieldType.cuboid)
	private Cuboid cuboid = new Cuboid();

	public Cuboid getCuboid(){
	    return cuboid;
	}
	
	public void configure(Achievement ach,String config) {
			cuboid.setCuboid(config);
	}

	public boolean checkAchievement(Player player) {
		//if player has stat
		//if(player.getWorld().getName().equals(world)){

		return cuboid.isInside(player.getLocation());
	}
	
	public ArrayList<String> getCache(){
		return cuboid.getChunks();
	}

    public void configure(Achievement ach) {
        //Register with cuboid listener
        BeardAch.self.getCuboidListener().add(cuboid, ach);
    }

    

}
