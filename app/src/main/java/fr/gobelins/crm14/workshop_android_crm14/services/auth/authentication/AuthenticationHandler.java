package fr.gobelins.crm14.workshop_android_crm14.services.auth.authentication;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;

/**
 * Created by risq on 10/14/15.
 */
public class AuthenticationHandler implements Firebase.AuthResultHandler {
    @Override
    public void onAuthenticated(AuthData authData) {
        BusProvider.getInstance().post(new AuthenticationEvent(authData));
    }

    @Override
    public void onAuthenticationError(FirebaseError firebaseError) {
        BusProvider.getInstance().post(new AuthenticationEvent(firebaseError));
    }
}
