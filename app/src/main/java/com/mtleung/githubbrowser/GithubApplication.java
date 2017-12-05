package com.mtleung.githubbrowser;

import com.mtleung.githubbrowser.di.DaggerGithubApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by marco.t.leung on 2/12/2017.
 */

public class GithubApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerGithubApplicationComponent.builder().application(this).build();
    }
}
