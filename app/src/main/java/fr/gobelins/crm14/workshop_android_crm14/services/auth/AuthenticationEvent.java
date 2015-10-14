package fr.gobelins.crm14.workshop_android_crm14.services.auth;

import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;

/**
 * Created by risq on 10/14/15.
 */
public class AuthenticationEvent {
    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;
    private final AuthData mAuthData;

    public AuthenticationEvent() {
        mHasError = false;
        mCode = 0;
        mDetails = null;
        mMessage = null;
        mAuthData = null;
    }

    public AuthenticationEvent(FirebaseError firebaseError) {
        mHasError = true;
        mCode = firebaseError.getCode();
        mDetails = firebaseError.getDetails();
        mMessage = firebaseError.getMessage();
        mAuthData = null;
    }

    public AuthenticationEvent(AuthData authData) {
        mHasError = false;
        mCode = 0;
        mDetails = null;
        mMessage = null;
        mAuthData = authData;
    }

    public int getCode() {
        return mCode;
    }

    public String getDetails() {
        return mDetails;
    }

    public String getMessage() {
        return mMessage;
    }

    public boolean hasError() {
        return mHasError;
    }

    public AuthData getmAuthData() {
        return mAuthData;
    }
}