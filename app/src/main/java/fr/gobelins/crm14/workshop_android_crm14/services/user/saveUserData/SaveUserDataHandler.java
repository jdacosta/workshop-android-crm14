package fr.gobelins.crm14.workshop_android_crm14.services.user.saveUserData;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/14/15.
 */
public class SaveUserDataHandler implements Firebase.CompletionListener {
    @Override
    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
        BusProvider.getInstance().post(new SaveUserDataEvent(firebaseError));
    }
}
