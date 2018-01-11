package com.mtleung.githubbrowser.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.mtleung.githubbrowser.model.user.User;

/**
 * Created by marco.t.leung on 30/11/2017.
 */

public interface GithubDAO {
    @Dao
    public interface UserDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void save(User user);

        @Query("SELECT * FROM user WHERE id = :id")
        LiveData<User> load(String id);
    }
}
