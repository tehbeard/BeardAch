package com.tehbeard.beardach.achievement;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;
import com.tehbeard.beardach.achievement.rewards.IReward;
import com.tehbeard.beardach.achievement.triggers.ITrigger;
import java.util.HashSet;
import java.util.Set;
import org.spongepowered.api.entity.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;

/**
 * Represents an achievement.
 * 
 * @author James
 * 
 */
public class Achievement {

    public static enum Display {

        BROADCAST, PERSON, NONE
    }

    @Expose
    private String slug;
    @Expose
    private String name;
    @Expose
    private String descrip;
    private transient int id = 0;
    private static int nextId = 1;
    @Expose
    private Set<ITrigger> triggers = new HashSet<ITrigger>();
    @Expose
    private Set<IReward> rewards = new HashSet<IReward>();
    @Expose
    Display broadcast;
    @Expose
    private boolean hidden;
    private static final String exemptAll = "ach.exempt.*";
    private String exempt;

    public boolean isHidden() {
        return hidden;
    }

    public Achievement() {
        id = nextId;
        nextId++;
    }

    public boolean postLoad() {
        try {
            exempt ="ach.exempt." + slug;
            for (ITrigger t : triggers) {
                t.configure(this);
            }
            for (IReward r : rewards) {
                r.configure(this);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void resetId() {
        nextId = 1;
    }

    public int getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getDescrip() {
        return descrip;
    }

    public void addTrigger(ITrigger trigger) {
        if (trigger == null)
            return;
        triggers.add(trigger);
    }

    public void addReward(IReward reward) {
        if (reward == null)
            return;
        rewards.add(reward);
    }

    public boolean checkAchievement(Player player) {
        if (player == null)
            return false;

        if (has(player))
            return false;

        for (ITrigger trigger : triggers) {
            if(trigger == null){continue;}
            if (!trigger.checkAchievement(player))
                return false;
        }

        unlock(player);

        return true;
    }

    public void unlock(Player player) {
        if (!player.hasPermission(exemptAll) && !player.hasPermission(exempt)) {
            for (IReward reward : rewards) {
                if(reward == null){continue;}
                reward.giveReward(player);
            }
        } else {
            BeardAch.getLogger().info(player.getName() + " Exempt from achievement rewards.");
        }

        switch (broadcast) {
            case BROADCAST: {
                BeardAch.getGame().getServer().getBroadcastSink().sendMessage(
                
                Texts.builder(player.getName()).color(TextColors.AQUA).append(
                        Texts.builder("Unlocked: ").color(TextColors.WHITE).build(),
                        Texts.builder(name).color(TextColors.GOLD).build()
                ).build()
                );
                
                player.sendMessage(Texts.builder(descrip).color(TextColors.BLUE).build());

            }
                break;

            case PERSON: {
                player.sendMessage(
                        Texts.builder("Achievement Get! ").append(
                                Texts.builder(name).color(TextColors.GOLD).build()
                        ).build());
                player.sendMessage(Texts.builder(descrip).color(TextColors.BLUE).build());
            }
                break;
            case NONE:
                BeardAch.getLogger().info("Achievement get! " + player + " , " + name);
                break;
            default:
                break;

        }

        BeardAch.getAchievementManager().makeAchievementLink(player.getUniqueId(), slug);
        BeardAch.getAchievementManager().removeCheck(this, player.getUniqueId());
    }

    public Set<ITrigger> getTrigs() {
        return triggers;
    }

    public Set<IReward> getRewards() {
        return rewards;
    }

    public boolean has(Player player) {
        return BeardAch.getAchievementManager().playerHas(player.getUniqueId(), slug);
    }
}
