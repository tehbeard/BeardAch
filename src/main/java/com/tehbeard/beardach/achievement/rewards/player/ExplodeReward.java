package com.tehbeard.beardach.achievement.rewards.player;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.achievement.Achievement;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.annotations.Configurable;
import com.tehbeard.beardach.datasource.json.editor.EditorField;
import com.tehbeard.beardach.datasource.json.editor.EditorFieldType;
import com.tehbeard.beardach.datasource.json.help.ComponentHelpDescription;
import com.tehbeard.beardach.datasource.json.help.ComponentValueDescription;

@ComponentHelpDescription(description = "BE AS AWESOME AS MR TORGUE. EXPLOSIONS!")
@Configurable(tag = "explode", name = "EXPLOSIONS?")
public class ExplodeReward implements IReward {

    @ComponentValueDescription(description = "ADD PYRO TECHNICS TO THIS BADASS EXPLOSION")
    @Expose
    @EditorField(alias = "explosion causes fire", type = EditorFieldType.bool)
    private boolean doFire = false;
    @ComponentValueDescription(description = "HOW BADASS IS THIS EXPLOSION? (4.0 IS TNT)")
    @Expose
    @EditorField(alias = "Explosion power", type = EditorFieldType.text)
    private float power = 4.0f;

    @Override
    public void giveReward(Player player) {
        World world = player.getWorld();
        world.createExplosion(player.getLocation(), power, doFire);
    }

    @Override
    public void configure(Achievement ach) {
    }
}
