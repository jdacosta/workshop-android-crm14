package fr.gobelins.crm14.workshop_android_crm14.services.user.getCurrentUserContacts;

import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/24/15.
 */
public class GetCurrentUserContactsEvent {
    public static final int CONTACT_ADD = 0;
    public static final int CONTACT_REMOVE = 1;
    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;
    private final String mContactId;
    private final int mAction;

    public GetCurrentUserContactsEvent(String contactId, int action) {
        if (contactId == null) {
            mHasError = true;
        } else {
            mHasError = false;
        }
        mAction = action;
        mCode = 0;
        mDetails = null;
        mMessage = null;
        mContactId = contactId;
    }

    public GetCurrentUserContactsEvent(FirebaseError firebaseError) {
        mHasError = true;
        mCode = firebaseError.getCode();
        mDetails = firebaseError.getDetails();
        mMessage = firebaseError.getMessage();
        mContactId = null;
        mAction = -1;
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

    public String getContactId() {
        return mContactId;
    }

    public int getAction() { return mAction; }

}
