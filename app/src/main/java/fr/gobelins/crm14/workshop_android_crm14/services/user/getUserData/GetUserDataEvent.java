package fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData;

import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/14/15.
 */
public class GetUserDataEvent {
    public static final int GET_USER_DATA_TO_APPEND_CONTACTS_LIST = 0;

    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;
    private final User mUser;
    private final int mRequestId;

    public GetUserDataEvent(User user, int requestId) {
        if (user == null) {
            mHasError = true;
        } else {
            mHasError = false;
        }
        mCode = 0;
        mDetails = null;
        mMessage = null;
        mUser = user;
        mRequestId = requestId;
    }

    public GetUserDataEvent(FirebaseError firebaseError, int requestId) {
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
