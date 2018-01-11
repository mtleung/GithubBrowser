package com.mtleung.githubbrowser.search;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.mtleung.githubbrowser.model.search.SearchResult;
import com.mtleung.githubbrowser.model.user.UserRepository;

/**
 * Created by marco.t.leung on 5/12/2017.
 */

public class SearchHandler implements Observer<SearchResult> {
    private UserRepository mUserRepository;

    private String query;
    private LiveData<SearchResult> searchResultLiveData;

    public SearchHandler(UserRepository mUserRepository) {
        this.mUserRepository = mUserRepository;
    }

    @Override
    public void onChanged(@Nullable SearchResult searchResult) {

    }
}
