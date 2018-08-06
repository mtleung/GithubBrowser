package com.mtleung.githubbrowser.model.search;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by marco.t.leung on 11/1/2018.
 */

@Entity(tableName = "search_term")
public class SearchTerm {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
