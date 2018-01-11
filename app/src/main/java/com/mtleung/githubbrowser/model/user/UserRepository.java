package com.mtleung.githubbrowser.model.user;

import android.support.annotation.NonNull;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by marco.t.leung on 5/12/2017.
 */
public class UserRepository implements UserDataSource {
    private final String TAG = UserRepository.class.getSimpleName();

    private volatile static UserRepository INSTANCE = null;

    private final UserDataSource mUserLocalDataSource;
    private final UserDataSource mUserRemoteDataSource;

    private boolean mCacheIsDirty = false;

    private UserRepository(@NonNull UserDataSource userLocalDataSource,
                           @NonNull UserDataSource userRemoteDataSource) {
        mUserLocalDataSource = userLocalDataSource;
        mUserRemoteDataSource = userRemoteDataSource;
    }

    public static UserRepository getInstance(UserDataSource userLocalDataSource,
                                             UserDataSource userRemoteDataSource) {
        if (INSTANCE == null) {
            synchronized (UserRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserRepository(userLocalDataSource, userRemoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getUsers(@NonNull LoadUsersCallback callback) {
        checkNotNull(callback);


    }

    @Override
    public void refreshUsers() {
        mCacheIsDirty = true;
    }

    private void refreshCache(List<User> users) {

    }
}
