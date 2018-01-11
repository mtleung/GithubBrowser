package com.mtleung.githubbrowser.model.search.source;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.model.search.SearchItem;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public class SearchRepository implements SearchDataSource {
    private volatile static SearchRepository INSTANCE = null;

    private final SearchDataSource mSearchLocalDataSource;
    private final SearchDataSource mSearchRemoteDataSource;

    private SearchRepository(@NonNull SearchDataSource searchLocalDataSource,
                             @NonNull SearchDataSource searchRemoteDataSource) {
        mSearchLocalDataSource= searchLocalDataSource;
        mSearchRemoteDataSource = searchRemoteDataSource;
    }

    public static SearchRepository getInstance(SearchDataSource searchLocalDataSource,
                                               SearchDataSource searchRemoteDataSource) {
        if (INSTANCE == null) {
            synchronized (SearchRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SearchRepository(searchLocalDataSource, searchRemoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<List<SearchItem>> search(@NonNull String query) {
        checkNotNull(query);
        return null;
//        mSearchRemoteDataSource.
    }

    @Override
    public void getSearchResults(@NonNull String query, @NonNull LoadSearchResultCallback callback) {
        checkNotNull(query);
        checkNotNull(callback);

        // Check local data source first
        mSearchLocalDataSource.getSearchResults(query, new LoadSearchResultCallback() {
            @Override
            public void onSearchResultLoaded(LiveData<List<SearchItem>> searchItemList) {
                callback.onSearchResultLoaded(searchItemList);
            }

            @Override
            public void onDataNotAvailable() {
                getSearchResultsFromRemoteDataSource(query, callback);
            }
        });
    }

    @Override
    public void saveSearchItem(@NonNull String query, @NonNull SearchItem searchItem) {
        // not needed
    }

    @Override
    public void clearCache() {
        mSearchLocalDataSource.clearCache();
    }

    private void saveSearchResultLocalDataSource(String query, List<SearchItem> searchItems) {
        for(SearchItem searchItem : searchItems) {
            mSearchLocalDataSource.saveSearchItem(query, searchItem);
        }
    }

    private void getSearchResultsFromRemoteDataSource(@NonNull String query,
                                                      @NonNull LoadSearchResultCallback callback) {
        // Get data from server
        mSearchRemoteDataSource.getSearchResults(query, new LoadSearchResultCallback() {
            @Override
            public void onSearchResultLoaded(LiveData<List<SearchItem>> searchItemList) {
                saveSearchResultLocalDataSource(query, searchItemList.getValue());
                callback.onSearchResultLoaded(searchItemList);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
