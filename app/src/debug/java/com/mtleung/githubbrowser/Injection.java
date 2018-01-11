package com.mtleung.githubbrowser;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.data.FakeSearchDataSource;
import com.mtleung.githubbrowser.data.FakeUserDataSource;
import com.mtleung.githubbrowser.database.GithubDatabase;
import com.mtleung.githubbrowser.model.search.source.SearchRepository;
import com.mtleung.githubbrowser.model.search.source.local.SearchLocalDataSource;
import com.mtleung.githubbrowser.model.user.UserRepository;
import com.mtleung.githubbrowser.model.user.source.local.UserLocalDataSource;
import com.mtleung.githubbrowser.util.AppExecutors;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by marco.t.leung on 8/1/2018.
 */

public class Injection {
    public static SearchRepository provideSearchRepository(@NonNull Context context) {
        checkNotNull(context);
        GithubDatabase database = GithubDatabase.getInstance(context);
        return SearchRepository.getInstance(
                SearchLocalDataSource.getInstance(new AppExecutors(), database.searchDao()),
                FakeSearchDataSource.getInstance()
        );
    }

    public static UserRepository provideUserRepository(@NonNull Context context) {
        checkNotNull(context);
        GithubDatabase database = GithubDatabase.getInstance(context);
        return UserRepository.getInstance(
                UserLocalDataSource.getInstance(new AppExecutors(), database.userDao()),
                FakeUserDataSource.getInstance()
        );
    }
}
