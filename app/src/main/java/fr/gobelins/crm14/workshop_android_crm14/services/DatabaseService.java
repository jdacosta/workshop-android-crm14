package fr.gobelins.crm14.workshop_android_crm14.services;

import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.Firebase.ValueResultHandler;
import com.firebase.client.FirebaseError;

import java.util.Map;

import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/13/15.
 */
public class DatabaseService {
    private static final String TAG = "DatabaseService";
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
