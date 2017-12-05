package com.mtleung.githubbrowser.di;

import android.app.Application;

import com.mtleung.githubbrowser.GithubApplication;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by marco.t.leung on 2/12/2017.
 */

@Component(modules = {
        AndroidInjectionModule.class,
        GithubApplicationModule.class
})
public interface GithubApplicationComponent extends AndroidInjector<GithubApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        GithubApplicationComponent.Builder application(Application application);

        GithubApplicationComponent build();
    }
}
