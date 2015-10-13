package fr.gobelins.crm14.workshop_android_crm14.services;

import com.firebase.client.Firebase;

/**
 * Created by risq on 10/13/15.
 */
public class DatabaseService {
    private static DatabaseService ourInstance = new DatabaseService();
    private final Firebase firebase;

    public static DatabaseService getInstance() {
        return ourInstance;
    }

    private DatabaseService() {
        firebase = new Firebase("https://android-workshop.firebaseio.com/");
    }

    public Firebase getFirebase() {
        return firebase;
    }
}
