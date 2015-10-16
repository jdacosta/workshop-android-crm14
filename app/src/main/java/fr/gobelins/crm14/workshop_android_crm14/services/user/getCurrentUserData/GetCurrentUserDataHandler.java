package fr.gobelins.crm14.workshop_android_crm14.services.user.getCurrentUserData;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/16/15.
 */
public class GetCurrentUserDataHandler implements ValueEventListener {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        BusProvider.getInstance().post(new GetCurrentUserDataEvent(dataSnapshot.getValue(User.class)));
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        BusProvider.getInstance().post(new GetCurrentUserDataEvent(firebaseError));
    }
}
