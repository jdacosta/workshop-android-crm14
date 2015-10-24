package fr.gobelins.crm14.workshop_android_crm14.services.auth.updateEmail;

import com.firebase.client.FirebaseError;

public class UpdateEmailEvent {
    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;

    public UpdateEmailEvent() {
        mHasError = false;
        mCode = 0;
        mDetails = null;
        mMessage = null;
    }

    public UpdateEmailEvent(FirebaseError firebaseError) {
        mHasError = true;
        mCode = firebaseError.getCode();
        mDetails = firebaseError.getDetails();
        mMessage = firebaseError.getMessage();
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

}