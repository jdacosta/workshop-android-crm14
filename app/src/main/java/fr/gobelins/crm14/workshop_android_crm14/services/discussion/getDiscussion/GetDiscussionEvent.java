package fr.gobelins.crm14.workshop_android_crm14.services.discussion.getDiscussion;

import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.discussion.Discussion;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/14/15.
 */
public class GetDiscussionEvent {
    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;
    private final Discussion mDiscussion;

    public GetDiscussionEvent(Discussion discussion) {
        if (discussion == null) {
            mHasError = true;
        } else {
            mHasError = false;
        }
        mCode = 0;
        mDetails = null;
        mMessage = null;
        mDiscussion = discussion;
    }

    public GetDiscussionEvent(FirebaseError firebaseError) {
        mHasError = true;
        mCode = firebaseError.getCode();
        mDetails = firebaseError.getDetails();
        mMessage = firebaseError.getMessage();
        mDiscussion = null;
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

    public Discussion getDiscussion() {
        return mDiscussion;
    }
}
