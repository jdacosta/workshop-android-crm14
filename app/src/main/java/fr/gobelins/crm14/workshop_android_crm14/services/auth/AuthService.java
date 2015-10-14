package fr.gobelins.crm14.workshop_android_crm14.services.auth;

import android.util.Log;

import com.firebase.client.AuthData;
import com.squareup.otto.Subscribe;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.DatabaseService;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.authentication.AuthenticationEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.authentication.AuthenticationHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.register.RegisterHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.saveUserData.SaveUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.saveUserData.SaveUserDataHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.register.RegisterEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/13/15.
 */
public class AuthService {
    private static final String TAG = "AuthService";
    private static AuthService ourInstance = new AuthService();

    private User currentUser;
    private AuthData currentAuthData;

    public static AuthService getInstance() {
        return ourInstance;
    }

    private AuthService() {
        BusProvider.getInstance().register(this);
    }

    public void authenticate(final String email, final String password) {
        DatabaseService.getInstance()
                .getFirebase()
                .authWithPassword(email, password, new AuthenticationHandler());
    }

    public void register(final String email, final String username, final String password) {
        DatabaseService.getInstance()
                .getFirebase()
                .createUser(email, password, new RegisterHandler(username));
    }

    public void saveUserData(String uid, User user) {
        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .child(uid)
                .setValue(user, new SaveUserDataHandler());
    }

    @Subscribe
    public void onAuthenticate(AuthenticationEvent event) {
        if (!event.hasError()) {
            Log.d(TAG, "Auth success");
            currentAuthData = event.getAuthData();
            currentUser = new User();
        }
    }

    @Subscribe
    public void onRegister(RegisterEvent event) {
        if (!event.hasError()){
            Log.d(TAG, "Register success");
            Log.d(TAG, "New user uid: " + event.getUid());
            User user = new User();
            user.setUsername(event.getUsername());
            saveUserData(event.getUid(), user);
        }
    }

    @Subscribe
    public void onSaveUserData(SaveUserDataEvent event) {
        if (!event.hasError()){
            Log.d(TAG, "Save user data success");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public AuthData getCurrentAuthData() {
        return currentAuthData;
    }

    public void setCurrentAuthData(AuthData currentAuthData) {
        this.currentAuthData = currentAuthData;
    }
}
