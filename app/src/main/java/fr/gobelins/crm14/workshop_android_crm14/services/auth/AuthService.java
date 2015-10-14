package fr.gobelins.crm14.workshop_android_crm14.services.auth;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.squareup.otto.Subscribe;

import java.util.Map;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.DatabaseService;
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
                .authWithPassword(email, password, new AuthWithPasswordHandler());
    }

    public void register(final String email, final String username, final String password) {
        DatabaseService.getInstance()
                .getFirebase()
                .createUser(email, password, new CreateUserHandler());
    }

    @Subscribe
    public void onUserAuthenticate(AuthenticationEvent event) {
        if (!event.hasError()){
            Log.d(TAG, "Auth success");
            currentAuthData = event.getmAuthData();
            Log.d(TAG, "Auth data: " + currentAuthData.toString());
            currentUser = new User(currentAuthData.getUid());
        }
    }

    @Subscribe
    public void onUserRegister(RegisterEvent event) {
        if (!event.hasError()){
            Log.d(TAG, "Register success");
            String uid = event.getUid();
            Log.d(TAG, "New user uid: " + uid);
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

    public interface RegisterHandler {
        void onRegisterSuccess();
        void onRegisterFail(String error);
    }
}
