package com.mtleung.githubbrowser.di;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.Injection;
import com.mtleung.githubbrowser.model.search.source.SearchRepository;
import com.mtleung.githubbrowser.model.user.UserRepository;
import com.mtleung.githubbrowser.search.SearchViewModel;
import com.mtleung.githubbrowser.search.history.HistoryViewModel;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final SearchRepository mSearchRepository;
    private final UserRepository mUserRepository;

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application,
                            Injection.provideSearchRepository(application.getApplicationContext()),
                            Injection.provideUserRepository(application.getApplicationContext()));
                }
            }
        }
        return INSTANCE;
    }

    private ViewModelFactory(Application application,
                             SearchRepository searchRepository, UserRepository userRepository) {
        mApplication = application;
        mSearchRepository = searchRepository;
        mUserRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            //noinspection unchecked
            return (T) new SearchViewModel(mSearchRepository);
        }
        if (modelClass.isAssignableFrom(HistoryViewModel.class)) {
            //noinspection unchecked
            return (T) new HistoryViewModel();
        }

        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass.getName());
    }
}
