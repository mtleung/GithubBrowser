package com.mtleung.githubbrowser.util;

import android.arch.lifecycle.LiveData;

/**
 * Created by marco.t.leung on 8/1/2018.
 */

public class AbsentLiveData extends LiveData {
    private AbsentLiveData() {
        postValue(null);
    }

    public static <T> LiveData<T> create() {
        //noinspection unchecked
        return new AbsentLiveData();
    }
}
