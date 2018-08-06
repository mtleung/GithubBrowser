package com.mtleung.githubbrowser.model.search.source.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.mtleung.githubbrowser.model.search.SearchTerm;

import java.util.List;

/**
 * Created by marco.t.leung on 11/1/2018.
 */
@Dao
public interface SearchTermDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSearchTerm(SearchTerm searchTerm);

    @Query("SELECT * FROM search_item ORDER BY id DESC")
    LiveData<List<SearchTerm>> getSearchTerms();

    @Query("DELETE FROM search_item")
    void deleteSearchTerms();
}
