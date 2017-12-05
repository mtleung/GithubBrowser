package com.mtleung.githubbrowser.search;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.database.Observable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.model.SearchItem;

/**
 * Created by marco.t.leung on 3/12/2017.
 */

public class SearchViewModel extends AndroidViewModel {

    // Must be application context to prevent leaks
    private final Context mContext;

    public final ObservableField<String> searchTerm = new ObservableField<>();
    public final ObservableField<Integer> numberSearchResult = new ObservableField<>();
    public final ObservableList<SearchItem> searchResultList = new ObservableArrayList<>();
    public final ObservableList<String> searchHistory = new ObservableArrayList<>();

    public SearchViewModel(@NonNull Application context) {
        super(context);
        mContext = context.getApplicationContext();
    }
}
