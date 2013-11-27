package com.tehbeard.beardach.achievement.triggers.spatial;

import java.util.ArrayList;

import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentType;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import com.tehbeard.utils.cuboid.Cuboid;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

/**
 * Checks if a players is in a cuboid
 * @author James
 *
 */
@ComponentHelpDescription(description = "Is player inside an area", name = "Cuboid", type = ComponentType.TRIGGER)
@Configurable(tag="cuboid",name="cuboid")
public class CuboidCheckTrigger implements ITrigger {


    @ComponentValueDescription(description="Area to check")
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
        BeardAch.instance().getCuboidListener().add(cuboid, ach);
    }

    

}
