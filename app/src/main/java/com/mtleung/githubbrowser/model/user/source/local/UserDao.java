package com.mtleung.githubbrowser.model.user.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mtleung.githubbrowser.model.user.User;

/**
 * Created by marco.t.leung on 8/1/2018.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("SELECT * FROM user WHERE id = :id")
    LiveData<User> getUser(String id);

    @Update
    int updateUser(User user);

    @Query("DELETE FROM user")
    void deleteUser();
}
