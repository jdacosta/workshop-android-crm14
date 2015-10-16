package fr.gobelins.crm14.workshop_android_crm14.services.user.findUserByUserName;

import android.util.Log;

import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/16/15.
 */
public class FindUserByUsernameEvent {
    public static final int FIND_USER_TO_ADD_CONTACT = 0;
    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;
    private final User mUser;
    private final int mRequestId;

    public FindUserByUsernameEvent(String userId, int requestId) {
        if (userId == null) {
            mHasError = true;
        } else {
            mHasError = false;
        }
        mCode = 0;
        mDetails = null;
        mMessage = null;
        mUser = new User(userId);
        mRequestId = requestId;
    }

    public FindUserByUsernameEvent(FirebaseError firebaseError, int requestId) {
        mHasError = true;
        mCode = firebaseError.getCode();
        mDetails = firebaseError.getDetails();
        mMessage = firebaseError.getMessage();
        mUser = null;
        mRequestId = requestId;
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

    public int getRequestId() { return mRequestId; }
}
