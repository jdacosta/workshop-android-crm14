package fr.gobelins.crm14.workshop_android_crm14.services.user.saveUserData;

import com.firebase.client.FirebaseError;

/**
 * Created by risq on 10/14/15.
 */
public class SaveUserDataEvent {

    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;

    public SaveUserDataEvent() {
        mHasError = false;
        mCode = 0;
        mDetails = null;
        mMessage = null;
    }

    public SaveUserDataEvent(FirebaseError firebaseError) {
        if (firebaseError == null) {
            mHasError = false;
            mCode = 0;
            mDetails = null;
            mMessage = null;
        } else {
            mHasError = true;
            mCode = firebaseError.getCode();
            mDetails = firebaseError.getDetails();
            mMessage = firebaseError.getMessage();
        }
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
}
