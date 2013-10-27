package me.tehbeard.BeardAch.achievement;

import com.google.gson.annotations.Expose;
import java.sql.Timestamp;

import me.tehbeard.BeardAch.BeardAch;

public class AchievementPlayerLink {

    @Expose
    private String slug;
    @Expose
    private Timestamp date;

    public String getSlug() {
        return slug;
    }

    public Timestamp getDate() {
        return date;
    }

    public AchievementPlayerLink(String slug, Timestamp date) {

        BeardAch.printDebugCon("CREATING LINK");

        this.slug = slug;
        this.date = date;
    }

    public AchievementPlayerLink(String slug) {
        BeardAch.printDebugCon("CREATING LINK NO DATE SPECIFIED");
        this.slug = slug;

        this.date = new Timestamp((new java.util.Date()).getTime());
        date.setNanos(0);
    }

    public Achievement getAch() {
        return BeardAch.self.getAchievementManager().getAchievementSlug(slug);
    }
}
