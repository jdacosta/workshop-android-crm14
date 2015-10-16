package fr.gobelins.crm14.workshop_android_crm14;

import android.app.Application;

import com.firebase.client.Firebase;

import fr.gobelins.crm14.workshop_android_crm14.services.utils.Preferences;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Preferences.setContext(this);
    }
}
