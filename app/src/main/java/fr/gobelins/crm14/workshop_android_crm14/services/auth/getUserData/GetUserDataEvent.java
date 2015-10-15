package fr.gobelins.crm14.workshop_android_crm14.services.auth.getUserData;

import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/14/15.
 */
public class GetUserDataEvent {
    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;
    private final User mUser;

    public GetUserDataEvent(User user) {
        if (user == null) {
            mHasError = true;
        } else {
            mHasError = false;
        }
        mCode = 0;
        mDetails = null;
        mMessage = null;
        mUser = user;
    }

    public GetUserDataEvent(FirebaseError firebaseError) {
        mHasError = true;
        mCode = firebaseError.getCode();
        mDetails = firebaseError.getDetails();
        mMessage = firebaseError.getMessage();
        mUser = null;
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

    public User getUser() {
        return mUser;
    }
}
