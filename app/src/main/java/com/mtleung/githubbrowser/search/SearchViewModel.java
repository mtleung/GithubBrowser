package com.mtleung.githubbrowser.search;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mtleung.githubbrowser.model.search.SearchItem;
import com.mtleung.githubbrowser.model.search.source.SearchRepository;
import com.mtleung.githubbrowser.util.AbsentLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marco.t.leung on 3/12/2017.
 */

public class SearchViewModel extends ViewModel {
    private final String TAG = SearchViewModel.class.getSimpleName();

    private final SearchRepository mSearchRepository;

    public final MutableLiveData<String> searchTerm = new MutableLiveData<>();

    public final LiveData<Boolean> hasSearchTerm;
    public final LiveData<List<SearchItem>> searchItemList;

//    public final LiveData<Integer> numberSearchHistory;
//    public final MutableLiveData<List<String>> searchHistory = new MutableLiveData<>();


    public SearchViewModel(SearchRepository searchRepository) {
        Log.d(TAG, "Creating SearchViewModel");
//        searchHandler = new SearchHandler(userRepository);
        mSearchRepository = searchRepository;

        hasSearchTerm = Transformations.switchMap(searchTerm, search -> {
            Log.d(TAG, "searchTerm updated, updating hasSearchTerm");

            MutableLiveData<Boolean> output = new MutableLiveData<>();
            output.setValue(search != null && search.trim().length() > 0);
            return output;
        });

        searchItemList = Transformations.switchMap(searchTerm, search -> {
                    Log.d(TAG, "searchTerm updated, updating searchItemList");
                    if (search == null || search.trim().length() == 0) {
                        Log.d(TAG, "Nulling searchItemList");
                        return AbsentLiveData.create();
                    } else {
                        Log.d(TAG, "Populating searchItemList");
                        MutableLiveData<List<SearchItem>> output = new MutableLiveData<>();

                        List<SearchItem> itemList = new ArrayList<>();
                        String term = search.trim();
                        int length = term.length();
                        for (int i = 0; i < length; i++) {
                            SearchItem item = new SearchItem();
                            item.setName(term.substring(0, i));
                            itemList.add(item);
                        }
                        output.setValue(itemList);
                        return output;
                    }
                });

//                MutableLiveData<List<SearchItem>> results = new MutableLiveData<>();
//                searchRepository.getSearchResults(search, new SearchDataSource.LoadSearchResultCallback() {
//                    @Override
//                    public void onSearchResultLoaded(LiveData<List<SearchItem>> searchItemList) {
//                        results.setValue(searchItemList.getValue());
//                    }
//
//                    @Override
//                    public void onDataNotAvailable() {
//
//                    }
//                });
//                return results;
//            }
//        });

//        hasSearchResult = Transformations.switchMap(searchItemList, results -> {
//            MutableLiveData<Boolean> output = new MutableLiveData<>();
//            if (searchItemList.getValue() != null) {
//                output.setValue(searchItemList.getValue().size() > 0);
//            }
//            return output;
//        });
//
//        numberSearchHistory = Transformations.switchMap(searchHistory, history -> {
//            MutableLiveData<Integer> output = new MutableLiveData<>();
//            output.setValue(searchHistory.getValue() != null ? searchHistory.getValue().size() : 0);
//            return output;
//        });
    }

    void setSearchTerm(String newSearchTerm) {
        // Input validation
        String input = newSearchTerm.toLowerCase().trim();
        if (searchTerm.getValue() != null && searchTerm.getValue().equals(input)) {
            // Currently searched term
            Log.d(TAG, "searchTerm not changed, ignoring");
            return;
        }
//        if (searchHistory.getValue() == null) {
//            Log.d(TAG, "init searchHistory");
//            searchHistory.setValue(new ArrayList<>());
//        }
//        Log.d(TAG, String.format("Adding searchHistory: %s", input));
//        searchHistory.getValue().add(input);
        Log.d(TAG, String.format("Setting searchTerm: %s", input));

        mSearchRepository.

        searchTerm.setValue(input);
    }

    public LiveData<Boolean> getHasSearchTerm() {
        return hasSearchTerm;
    }

    public LiveData<List<SearchItem>> getSearchItemList() {
        return searchItemList;
    }
}
