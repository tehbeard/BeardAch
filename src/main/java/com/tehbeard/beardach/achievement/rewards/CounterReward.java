package com.tehbeard.beardach.achievement.rewards;

import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;
import com.tehbeard.beardstat.manager.EntityStatManager;

@ComponentHelpDescription(description = "Increment counter value. Counters are stored in BeardStat and accessible from the stats triggers", dependencies = { "BeardStat" })
@Configurable(tag = "counter", name = "Increment counter")
public class CounterReward implements IReward {

    private static final String DOMAIN = "beardach";
    @ComponentValueDescription(value = "Name of the counter")
    @Expose
    @EditorField(alias = "Counter name")
    String name = "";
    @ComponentValueDescription(value = "Amount to increment counter by")
    @Expose
    @EditorField(alias = "Amount to increment")
    int count = 0;
    private static EntityStatManager manager = null;

    @Override
    public void giveReward(Player player) {
        if (manager != null) {
            manager.getBlobForPlayer(player).getStat(DOMAIN, player.getWorld().getName(), "achCount", name).incrementStat(count);
        } else {
            BeardAch.instance().getLogger().severe("[ERROR] " + "BeardStat not loaded, reward not given!");
        }
    }

    @Override
    public void configure(Achievement ach) {
        if (manager == null) {
            manager = BeardAch.instance().getStats();
        }
    }
}
