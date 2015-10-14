package fr.gobelins.crm14.workshop_android_crm14.services.auth;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;

/**
 * Created by risq on 10/14/15.
 */
public class CreateUserHandler implements Firebase.ValueResultHandler {
    @Override
    public void onSuccess(Object o) {
        Map stringObjectMap = (Map<String, Object>)o;
        BusProvider.getInstance().post(new RegisterEvent((String)stringObjectMap.get("uid")));
    }

    @Override
    public void onError(FirebaseError firebaseError) {
        BusProvider.getInstance().post(new RegisterEvent(firebaseError));
    }
}
