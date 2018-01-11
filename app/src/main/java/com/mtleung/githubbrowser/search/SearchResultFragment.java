package com.mtleung.githubbrowser.search;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtleung.githubbrowser.R;
import com.mtleung.githubbrowser.databinding.FragmentSearchResultBinding;
import com.mtleung.githubbrowser.di.ViewModelFactory;

/**
 * Created by marco.t.leung on 3/12/2017.
 */

public class SearchResultFragment extends Fragment {
    FragmentSearchResultBinding binding;
    SearchViewModel viewModel;

    public static SearchResultFragment newInstance() {
        return new SearchResultFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_result, container, false);

        if (binding == null) {
            binding = FragmentSearchResultBinding.bind(root);
        }
        viewModel = obtainViewModel(getActivity());
        return root;
    }

    private SearchViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(SearchViewModel.class);
    }
}
