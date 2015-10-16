package fr.gobelins.crm14.workshop_android_crm14.user;

/**
 * Created by risq on 10/13/15.
 */
public class User {
    private transient String uid;
    private String username;

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

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
