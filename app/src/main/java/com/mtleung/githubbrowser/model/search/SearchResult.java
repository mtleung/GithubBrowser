package com.mtleung.githubbrowser.model.search;

import java.util.ArrayList;

/**
 * Created by marco.t.leung on 4/12/2017.
 */

public class SearchResult {
    int count;
    ArrayList<SearchItem> searchResult;

    public int getCount() {
        return count;
    }

    public ArrayList<SearchItem> getSearchResult() {
        return searchResult;
    }
}
