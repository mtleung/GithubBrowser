package com.mtleung.githubbrowser.model.search.source.local;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.model.search.SearchItem;
import com.mtleung.githubbrowser.model.search.source.SearchDataSource;
import com.mtleung.githubbrowser.util.AppExecutors;

import java.util.List;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public class SearchLocalDataSource implements SearchDataSource {
    private static SearchLocalDataSource INSTANCE;

    private AppExecutors mAppExecutors;
    private SearchDao mSearchDao;
    private SearchTermDao mSearchTermDao;

    private SearchLocalDataSource(@NonNull AppExecutors appExecutors,
                                  @NonNull SearchDao searchDao,
                                  @NonNull SearchTermDao searchTermDao) {
        mAppExecutors = appExecutors;
        mSearchDao = searchDao;
        mSearchTermDao = searchTermDao;
    }

    public static SearchLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                    @NonNull SearchDao searchDao,
                                                    @NonNull SearchTermDao searchTermDao) {
        if (INSTANCE == null) {
            INSTANCE = new SearchLocalDataSource(appExecutors, searchDao, searchTermDao);
        }
        return INSTANCE;
    }

    @Override
    public void getSearchResults(@NonNull final String query, @NonNull LoadSearchResultCallback callback) {
        Runnable runnable = () -> {
            final LiveData<List<SearchItem>> searchItems = mSearchDao.getSearchItems(query);
            mAppExecutors.getMainThread().execute(() -> {
                if (searchItems.getValue() != null) {
                    callback.onSearchResultLoaded(searchItems);
                } else {
                    callback.onDataNotAvailable();
                }
            });
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void saveSearchItem(@NonNull String query, @NonNull SearchItem searchItem) {
        Runnable runnable = () -> {
            searchItem.setQuery(query);
            mSearchDao.insertSearchItem(searchItem);
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }

    @Override
    public void clearCache() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        mAppExecutors.getDiskIO().execute(runnable);
    }
}
