package me.tehbeard.BeardAch.achievement.triggers;

import java.util.ArrayList;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.help.Argument;
import me.tehbeard.BeardAch.achievement.help.Usage;
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
@Usage(arguments={
        @Argument(name="World",desc=""),
        @Argument(name="x1",desc=""),
        @Argument(name="y",desc=""),
        @Argument(name="z1",desc=""),
        @Argument(name="x2",desc=""),
        @Argument(name="y2",desc=""),
        @Argument(name="z2",desc="")
        },packageName="base",blurb="Provides a dependency free area entry trigger")
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
        
    }

    

}
