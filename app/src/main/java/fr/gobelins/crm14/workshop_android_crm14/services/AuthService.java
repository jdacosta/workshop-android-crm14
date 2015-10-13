package fr.gobelins.crm14.workshop_android_crm14.services;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

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
    }

    public void authenticate(final String email, final String password, final LoginHandler loginHandler) {
        DatabaseService.getInstance().getFirebase().authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Log.d(TAG, "Auth success - email: " + email + " - password: " + password);
                currentAuthData = authData;
                currentUser = new User(email);
                loginHandler.onSuccess();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Log.d(TAG, "Auth error - email: " + email + " - password: " + password);
                loginHandler.onFail(firebaseError.toString());
            }
        });
    }

    public interface LoginHandler {
        void onSuccess();
        void onFail(String error);
    }
}
