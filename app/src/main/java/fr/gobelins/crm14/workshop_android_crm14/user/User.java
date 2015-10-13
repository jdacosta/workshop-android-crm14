package fr.gobelins.crm14.workshop_android_crm14.user;

import com.firebase.client.AuthData;

/**
 * Created by risq on 10/13/15.
 */
public class User {
    private String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
