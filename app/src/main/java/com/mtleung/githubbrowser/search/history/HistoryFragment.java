package com.mtleung.githubbrowser.search.history;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mtleung.githubbrowser.R;
import com.mtleung.githubbrowser.databinding.FragmentSearchHistoryBinding;
import com.mtleung.githubbrowser.di.ViewModelFactory;

/**
 * Created by marco.t.leung on 4/12/2017.
 */

public class HistoryFragment extends Fragment {

    FragmentSearchHistoryBinding binding;
    HistoryViewModel viewModel;


    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_search_history, container, false);

        if (binding == null) {
            binding = FragmentSearchHistoryBinding.bind(root);
        }
        viewModel = obtainViewModel(getActivity());
        return root;
    }

    private HistoryViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(HistoryViewModel.class);
    }
}