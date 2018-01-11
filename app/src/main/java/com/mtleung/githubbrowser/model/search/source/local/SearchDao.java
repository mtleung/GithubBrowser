package com.mtleung.githubbrowser.model.search.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.mtleung.githubbrowser.model.search.SearchItem;

import java.util.List;

/**
 * Created by marco.t.leung on 8/1/2018.
 */
@Dao
public interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSearchItem(SearchItem searchItem);

    @Query("SELECT * FROM search_item WHERE name LIKE :query")
    LiveData<List<SearchItem>> getSearchItems(String query);

    @Update
    int updateSearchItem(SearchItem searchItem);

    @Query("DELETE FROM search_item")
    void deleteSearchItems();
}
