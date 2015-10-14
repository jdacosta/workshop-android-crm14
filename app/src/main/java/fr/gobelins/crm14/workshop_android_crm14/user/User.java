package fr.gobelins.crm14.workshop_android_crm14.user;

import com.firebase.client.AuthData;

/**
 * Created by risq on 10/13/15.
 */
public class User {
    private String uid;
    private String username;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
