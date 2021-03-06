package fr.gobelins.crm14.workshop_android_crm14.services.auth.register;

import com.firebase.client.AuthData;
import com.firebase.client.FirebaseError;

/**
 * Created by risq on 10/14/15.
 */
public class RegisterEvent {
    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;
    private final String mUid;
    private final String mUsername;

    public RegisterEvent() {
        mHasError = false;
        mCode = 0;
        mDetails = null;
        mMessage = null;
        mUid = null;
        mUsername = null;
    }

    public RegisterEvent(FirebaseError firebaseError) {
        mHasError = true;
        mCode = firebaseError.getCode();
        mDetails = firebaseError.getDetails();
        mMessage = firebaseError.getMessage();
        mUid = null;
        mUsername = null;
    }

    public RegisterEvent(String uid, String username) {
        mHasError = false;
        mCode = 0;
        mDetails = null;
        mMessage = null;
        mUid = uid;
        mUsername = username;
    }

    public String getUsername() {
        return mUsername;
    }

    public boolean hasError() {
        return mHasError;
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

    public String getUid() {
        return mUid;
    }
}
