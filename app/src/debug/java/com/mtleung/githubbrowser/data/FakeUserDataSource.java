package com.mtleung.githubbrowser.data;

import android.support.annotation.NonNull;

import com.mtleung.githubbrowser.model.user.UserDataSource;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public class FakeUserDataSource implements UserDataSource {
    private static FakeUserDataSource INSTANCE;

    public static FakeUserDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeUserDataSource();
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
