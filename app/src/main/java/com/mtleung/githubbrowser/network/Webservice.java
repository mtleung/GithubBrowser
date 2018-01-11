package com.mtleung.githubbrowser.network;

import android.arch.lifecycle.LiveData;

import com.mtleung.githubbrowser.model.search.SearchResult;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marco.t.leung on 5/12/2017.
 */

public interface Webservice {
    @GET("/search/users")
    LiveData<SearchResult> getUser(@Path("q") String searchTerm);
}
