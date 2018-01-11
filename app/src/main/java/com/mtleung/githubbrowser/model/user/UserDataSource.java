package com.mtleung.githubbrowser.model.user;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public interface UserDataSource {
    interface LoadUsersCallback {
        void onUsersLoaded(List<User> users);
        void onDataNotAvailable();
    }

    void getUsers(@NonNull LoadUsersCallback callback);
    void refreshUsers();
}
