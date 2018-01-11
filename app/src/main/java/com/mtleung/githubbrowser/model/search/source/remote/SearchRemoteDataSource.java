package com.mtleung.githubbrowser.model.search.source.remote;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.model.search.SearchItem;
import com.mtleung.githubbrowser.model.search.SearchResult;
import com.mtleung.githubbrowser.model.search.source.SearchDataSource;
import com.mtleung.githubbrowser.model.search.source.local.SearchDao;
import com.mtleung.githubbrowser.network.Webservice;
import com.mtleung.githubbrowser.util.AppExecutors;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public class SearchRemoteDataSource implements SearchDataSource {
    private static SearchRemoteDataSource INSTANCE;

    private AppExecutors mAppExecutors;
    private SearchDao mSearchDao;
    private Webservice mWebservice;

    private SearchRemoteDataSource(@NonNull AppExecutors appExecutors,
                                   @NonNull SearchDao searchDao,
                                   @NonNull Webservice webService) {
        mAppExecutors = appExecutors;
        mSearchDao = searchDao;
        mWebservice = webService;
    }

    public static SearchRemoteDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                    @NonNull SearchDao searchDao,
                                                    @NonNull Webservice webService) {
        if (INSTANCE == null) {
            INSTANCE = new SearchRemoteDataSource(appExecutors, searchDao, webService);
        }
        return INSTANCE;
    }

    public LiveData<SearchResult> createCall(String query) {
        return mWebservice.getUser(query);
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
