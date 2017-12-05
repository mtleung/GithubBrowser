package com.mtleung.githubbrowser.model;

import android.arch.persistence.room.PrimaryKey;

/**
 * Created by marco.t.leung on 4/12/2017.
 */

public class SearchItem {
    @PrimaryKey
    private long id;
    private String name;
    private String avatarUrl;

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
