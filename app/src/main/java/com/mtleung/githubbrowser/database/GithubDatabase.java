package com.mtleung.githubbrowser.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.mtleung.githubbrowser.model.search.SearchItem;
import com.mtleung.githubbrowser.model.search.source.local.SearchDao;
import com.mtleung.githubbrowser.model.user.User;
import com.mtleung.githubbrowser.model.user.source.local.UserDao;

/**
 * Created by marco.t.leung on 30/11/2017.
 */

@Database(entities = {SearchItem.class, User.class}, version = 1)
public abstract class GithubDatabase extends RoomDatabase {
    private static GithubDatabase INSTANCE;

    public abstract SearchDao searchDao();
    public abstract UserDao userDao();

    private static final Object sLock = new Object();

    public static GithubDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        GithubDatabase.class, "GithubDatabase.db")
                        .build();
            }
            return INSTANCE;
        }
    }

}
