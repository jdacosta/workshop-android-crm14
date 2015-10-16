package fr.gobelins.crm14.workshop_android_crm14.services.discussion.saveDiscussion;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/16/15.
 */
public class SaveDiscussionHandler implements Firebase.CompletionListener {
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        BusProvider.getInstance().post(new SaveDiscussionEvent(firebaseError));
    }
}
