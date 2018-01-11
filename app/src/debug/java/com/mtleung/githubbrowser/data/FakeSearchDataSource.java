package com.mtleung.githubbrowser.data;

import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.model.search.SearchItem;
import com.mtleung.githubbrowser.model.search.source.SearchDataSource;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public class FakeSearchDataSource implements SearchDataSource {
    private static FakeSearchDataSource INSTANCE;

    public static FakeSearchDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeSearchDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getSearchResults(@NonNull String query, @NonNull LoadSearchResultCallback callback) {

    }

    @Override
    public void saveSearchItem(@NonNull String query, @NonNull SearchItem searchItem) {

    }

    @Override
    public void clearCache() {

    }
}
