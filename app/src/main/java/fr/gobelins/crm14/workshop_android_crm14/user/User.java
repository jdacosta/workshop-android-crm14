package fr.gobelins.crm14.workshop_android_crm14.user;

import com.firebase.client.AuthData;

/**
 * Created by risq on 10/13/15.
 */
public class User {
    private String uid;
    private String email;
    private String username;

    public User(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
