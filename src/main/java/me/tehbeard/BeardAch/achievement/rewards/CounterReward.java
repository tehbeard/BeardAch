package me.tehbeard.BeardAch.achievement.rewards;



import me.tehbeard.BeardStat.containers.PlayerStatManager;

import org.bukkit.entity.Player;

public class CounterReward extends Reward{

	String name = "";
	int count = 0;
	public static IReward getInstance(String config) {
		CounterReward n = new CounterReward();
		String[] opt = config.split("\\:");
		if(opt.length==2){
			n.name = opt[0];
			n.count = Integer.parseInt(opt[1]);
		}
		return n;
	}

	@Override
	public void giveReward(Player player) {		
		PlayerStatManager.getPlayerBlob(player.getName()).getStat("achCount", name).incrementStat(count);
	}

}
