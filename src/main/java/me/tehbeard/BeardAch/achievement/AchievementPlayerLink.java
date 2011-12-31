package me.tehbeard.BeardAch.achievement;

import java.sql.Date;

import me.tehbeard.BeardAch.BeardAch;

public class AchievementPlayerLink {


	private String slug;
	private Date date;


	public String getSlug() {
		return slug;
	}
	public Date getDate() {
		return date;
	}
	public AchievementPlayerLink(String slug, Date date) {

		BeardAch.printDebugCon("CREATING LINK");

		this.slug = slug;
		this.date = date;
	}

	public AchievementPlayerLink(String slug) {
		BeardAch.printDebugCon("CREATING LINK NO DATE SPECIFIED");
		this.slug = slug;
		this.date = new Date((new java.util.Date()).getTime());
	}

	public Achievement getAch(){
		return AchievementManager.getAchievementSlug(slug);	
	}


}
