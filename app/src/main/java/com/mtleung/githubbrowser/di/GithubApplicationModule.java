package com.mtleung.githubbrowser.di;

import com.mtleung.githubbrowser.UserDetailActivity;
import com.mtleung.githubbrowser.network.NetworkApi;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by marco.t.leung on 2/12/2017.
 */

@Module
public abstract class GithubApplicationModule {
    @ContributesAndroidInjector
    abstract UserDetailActivity contributeActivityInjector();

    @Provides
    static NetworkApi provideNetworkApi() {
        return new NetworkApi();
    }
}
