package com.tehbeard.beardach.achievement.triggers.env;

import org.spongepowered.api.entity.player.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "Checks if the worlds time is between two values",categories = "enviromental")
@Configurable(tag = "time", name = "Between two times")
public class TimeTrigger implements ITrigger {

    @ComponentValueDescription(value = "World time must have progressed past")
    @Expose
    @EditorField(alias = "After this time(ticks)")
    long after = 0;

    @ComponentValueDescription(value = "World time must be before")
    @Expose
    @EditorField(alias = "Before this time(ticks)")
    long before = 0;

    @Override
    public boolean checkAchievement(Player p) {
        Long t = 0L;//p.getWorld().
        return after < before ? after < t && before > t : after < t || before > t;
    }

    @Override
    public void configure(Achievement ach) {
        throw new UnsupportedOperationException("NOT IMPLEMENTED");
    }

}
