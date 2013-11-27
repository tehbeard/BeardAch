package com.tehbeard.beardach.achievement;

import java.sql.Timestamp;

import com.google.gson.annotations.Expose;
import com.tehbeard.beardach.BeardAch;

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

        this.slug = slug;
        this.date = date;
    }

    public AchievementPlayerLink(String slug) {
        this.slug = slug;

        date = new Timestamp(new java.util.Date().getTime());
        date.setNanos(0);
    }

    public Achievement getAch() {
        return BeardAch.instance().getAchievementManager().getAchievementSlug(slug);
    }
}
