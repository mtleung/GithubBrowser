package com.mtleung.githubbrowser.network;

import dagger.Provides;

/**
 * Created by marco.t.leung on 2/12/2017.
 */

public class NetworkApi {
    public boolean validateUser(String username, String password) {
        // imagine an actual network call here
        // for demo purpose return false in "real" life
        if (username == null || username.length() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
