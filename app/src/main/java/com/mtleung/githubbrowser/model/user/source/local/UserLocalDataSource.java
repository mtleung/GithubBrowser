package com.mtleung.githubbrowser.model.user.source.local;

import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.model.user.UserDataSource;
import com.mtleung.githubbrowser.util.AppExecutors;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public class UserLocalDataSource implements UserDataSource {
    private static UserLocalDataSource INSTANCE;

    private AppExecutors mAppExecutors;
    private UserDao mUserDao;

    private UserLocalDataSource(@NonNull AppExecutors appExecutors,
                                  @NonNull UserDao userDao) {
        mAppExecutors = appExecutors;
        mUserDao = userDao;
    }

    public static UserLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                    @NonNull UserDao userDao) {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource(appExecutors, userDao);
        }
        return INSTANCE;
    }

    @Override
    public void getUsers(@NonNull LoadUsersCallback callback) {

    }

    @Override
    public void refreshUsers() {

    }
}
