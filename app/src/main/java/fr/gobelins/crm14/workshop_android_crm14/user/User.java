package fr.gobelins.crm14.workshop_android_crm14.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by risq on 10/13/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonIgnore
    private String uid;
    private String username;
    private String pubKey;

    public User() {
    }

    public User(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", pubKey='" + pubKey + '\'' +
                '}';
    }
}
