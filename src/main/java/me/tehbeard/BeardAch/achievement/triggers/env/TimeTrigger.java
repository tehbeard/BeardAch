package me.tehbeard.BeardAch.achievement.triggers.env;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.configurable.Configurable;
import me.tehbeard.BeardAch.dataSource.json.editor.EditorField;


@Configurable(tag="time",name="Between two times")
public class TimeTrigger implements ITrigger {

    @Expose
    @EditorField(alias="After this time(ticks)")
    long after = 0;
    @Expose
    @EditorField(alias="Before this time(ticks)")
    long  before = 0;
    public void configure(Achievement ach, String config) {
        String[] c = config.split(":");
        after      = Integer.parseInt(c[0]);
        before     = Integer.parseInt(c[1]);
        
    }

    public boolean checkAchievement(Player p) {
        Long t = p.getWorld().getTime();
        return after<before ? (after < t && before > t) : (after < t || before > t);
    }

	public void configure(Achievement ach) {
		// TODO Auto-generated method stub
		
	}

}
