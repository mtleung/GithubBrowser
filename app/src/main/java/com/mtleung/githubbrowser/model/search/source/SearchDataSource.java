package com.mtleung.githubbrowser.model.search.source;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.model.search.SearchItem;

import java.util.List;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public interface SearchDataSource {
    interface LoadSearchResultCallback {
        void onSearchResultLoaded(LiveData<List<SearchItem>> searchItemList);
        void onDataNotAvailable();
    }
    void getSearchResults(@NonNull String query, @NonNull LoadSearchResultCallback callback);

    void saveSearchItem(@NonNull String query, @NonNull SearchItem searchItem);
    void clearCache();
}
