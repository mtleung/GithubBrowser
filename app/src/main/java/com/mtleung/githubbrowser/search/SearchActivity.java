package com.mtleung.githubbrowser.search;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.mtleung.githubbrowser.R;
import com.mtleung.githubbrowser.databinding.ActivitySearchBinding;
import com.mtleung.githubbrowser.di.ViewModelFactory;
import com.mtleung.githubbrowser.search.history.HistoryFragment;

/**
 * Created by marco.t.leung on 3/12/2017.
 */

public class SearchActivity extends AppCompatActivity {
    private final String TAG = SearchActivity.class.getSimpleName();

    SearchViewModel viewModel;
    ActivitySearchBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        viewModel = obtainViewModel(this);
//        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setViewmodel(viewModel);

        populateFragments();

        initSearchInputListener();
        subscribeToSearchResults();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void populateFragments() {
        Log.d(TAG, "populateFragments");
        HistoryFragment historyFragment = obtainHistoryFragment();
        replaceFragment(getSupportFragmentManager(), historyFragment, R.id.search_history);

        SearchResultFragment searchResultFragment = obtainSearchResultFragment();
        replaceFragment(getSupportFragmentManager(), searchResultFragment, R.id.search_results);
    }

    private void initSearchInputListener() {
        binding.searchInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(v);
                return true;
            }
            return false;
        });
        binding.searchInput.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getAction() == KeyEvent.KEYCODE_ENTER) {
                doSearch(v);
                return true;
            }
            return false;
        });
    }

    private void subscribeToSearchResults() {
        viewModel.getHasSearchTerm().observe(this, hasSearchTerm -> {
            if (hasSearchTerm == null) {
                hasSearchTerm = false;
            }
            binding.setHasSearchTerm(hasSearchTerm);
        });
    }

    private void doSearch(View view) {
        String searchTerm = binding.searchInput.getText().toString();
        dismissKeyboard(view.getWindowToken());
        Log.d(TAG, String.format("Searching %s", searchTerm));

        viewModel.setSearchTerm(searchTerm);
    }

    private void dismissKeyboard(IBinder windowToken) {
        binding.searchInput.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    private void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    private HistoryFragment obtainHistoryFragment() {
        HistoryFragment historyFragment = (HistoryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.search_history);
        if (historyFragment == null) {
            historyFragment = HistoryFragment.newInstance();
        }
        return historyFragment;
    }

    private SearchResultFragment obtainSearchResultFragment() {
        SearchResultFragment fragment = (SearchResultFragment) getSupportFragmentManager()
                .findFragmentById(R.id.search_results);
        if (fragment == null) {
            fragment = SearchResultFragment.newInstance();
        }
        return fragment;
    }

    static SearchViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(SearchViewModel.class);
    }
}
