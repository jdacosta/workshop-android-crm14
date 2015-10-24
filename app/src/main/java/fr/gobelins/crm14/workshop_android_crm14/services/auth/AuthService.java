package fr.gobelins.crm14.workshop_android_crm14.services.auth;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.DatabaseService;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.authentication.AuthenticationEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.authentication.AuthenticationHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.updateEmail.UpdateEmailHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.user.UserService;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getCurrentUserData.GetCurrentUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.register.RegisterHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.register.RegisterEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/13/15.
 */
public class AuthService {
    private static final String TAG = "AuthService";
    private static AuthService ourInstance = new AuthService();

    private User currentUser;
    private String currentPassword;
    private String currentEmail;
    private AuthData currentAuthData;

    public static AuthService getInstance() {
        return ourInstance;
    }

    private AuthService() {
        BusProvider.getInstance().register(this);
    }

    public void authenticate(final String email, final String password) {
        currentEmail = email;
        currentPassword = password;
        DatabaseService.getInstance()
                .getFirebase()
                .authWithPassword(email, password, new AuthenticationHandler());
    }

    public void register(final String email, final String username, final String password) {
        DatabaseService.getInstance()
                .getFirebase()
                .createUser(email, password, new RegisterHandler(username));
    }

    public void updateEmail(String newEmail) {
        DatabaseService.getInstance()
                .getFirebase()
                .changeEmail(currentEmail, newEmail, currentPassword, new UpdateEmailHandler());
    }

    @Subscribe
    public void onAuthenticate(AuthenticationEvent event) {
        if (!event.hasError()) {
            Log.d(TAG, "Auth success");
            currentUser = new User(event.getAuthData().getUid());
            currentAuthData = event.getAuthData();
            UserService.getInstance()
                    .getCurrentUserData();
            UserService.getInstance()
                    .getCurrentUserContacts();
        }
    }

    @Subscribe
    public void onRegister(RegisterEvent event) {
        if (!event.hasError()){
            Log.d(TAG, "Register success");
            Log.d(TAG, "New user uid: " + event.getUid());
            User user = new User(event.getUid());
            user.setUsername(event.getUsername());
            UserService.getInstance()
                    .saveUserData(user);
        }
    }

    @Subscribe
    public void onGetCurrentUserData(GetCurrentUserDataEvent event) {
        if (!event.hasError()) {
            currentUser = event.getUser();
            currentUser.setUid(currentAuthData.getUid());
            Log.d(TAG, "Get current user data success: " + event.getUser().toString());
        } else {
            Log.d(TAG, "Error getting current user data: " + event.getCode() + " - " + event.getMessage());
        }
    }


    @Produce
    public GetCurrentUserDataEvent produceGetUserData() {
        return new GetCurrentUserDataEvent(this.currentUser);
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

    public String getCurrentEmail() {
        return currentEmail;
    }
}
