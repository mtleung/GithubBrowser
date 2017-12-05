package com.mtleung.githubbrowser.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.mtleung.githubbrowser.model.User;

/**
 * Created by marco.t.leung on 30/11/2017.
 */

@Database(entities = {User.class}, version = 1)
public abstract class GithubDatabase extends RoomDatabase {
}
