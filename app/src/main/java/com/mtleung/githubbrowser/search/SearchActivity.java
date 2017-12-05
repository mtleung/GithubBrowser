package com.mtleung.githubbrowser.search;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import com.mtleung.githubbrowser.R;
import com.mtleung.githubbrowser.databinding.ActivitySearchBinding;

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

        viewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        binding.setViewmodel(viewModel);

        initSearchInputListener();
    }

    private void initSearchInputListener() {
        binding.inputSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch(v);
                return true;
            }
            return false;
        });
        binding.inputSearch.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getAction() == KeyEvent.KEYCODE_ENTER) {
                doSearch(v);
                return true;
            }
            return false;
        });
        binding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onSearchTextChanged(s.toString());
            }
        });
    }

    private void onSearchTextChanged(String searchTerm) {
        Log.d(TAG, String.format("SearchTerm %s", searchTerm));
    }

    private void doSearch(View view) {
        String searchTerm = binding.inputSearch.getText().toString();
        dismissKeyboard(view.getWindowToken());
        Log.d(TAG, String.format("Searching %s", searchTerm));
    }

    private void dismissKeyboard(IBinder windowToken) {
        binding.inputSearch.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(windowToken, 0);
        }
    }
}
