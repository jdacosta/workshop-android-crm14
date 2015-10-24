package fr.gobelins.crm14.workshop_android_crm14.services.auth.updateEmail;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;

/**
 * Created by julien on 24/10/15.
 */
public class UpdateEmailHandler implements Firebase.ResultHandler {

    @Override
    public void onSuccess() {
        BusProvider.getInstance().post(new UpdateEmailEvent());
    }

    @Override
    public void onError(FirebaseError firebaseError) {
        BusProvider.getInstance().post(new UpdateEmailEvent(firebaseError));
    }
}
