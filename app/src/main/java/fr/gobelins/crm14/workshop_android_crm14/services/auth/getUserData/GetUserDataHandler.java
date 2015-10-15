package fr.gobelins.crm14.workshop_android_crm14.services.auth.getUserData;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.otto.Produce;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/14/15.
 */
public class GetUserDataHandler implements ValueEventListener{
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        BusProvider.getInstance().post(new GetUserDataEvent(dataSnapshot.getValue(User.class)));
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        BusProvider.getInstance().post(new GetUserDataEvent(firebaseError));
    }
}
