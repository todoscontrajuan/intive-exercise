package com.me.squad.intivefdvapp.model;

import java.io.Serializable;

public class Picture implements Serializable {
    private String large;
    private String thumbnail;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
