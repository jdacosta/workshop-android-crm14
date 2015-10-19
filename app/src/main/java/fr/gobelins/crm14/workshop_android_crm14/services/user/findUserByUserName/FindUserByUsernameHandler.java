package fr.gobelins.crm14.workshop_android_crm14.services.user.findUserByUserName;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getCurrentUserData.GetCurrentUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/16/15.
 */
public class FindUserByUsernameHandler implements ValueEventListener {
    private int requestId;

    public FindUserByUsernameHandler(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.getChildren().iterator().hasNext()) {
            BusProvider.getInstance().post(new FindUserByUsernameEvent(dataSnapshot.getChildren().iterator().next().getKey(), requestId));
        } else {
            BusProvider.getInstance().post(new FindUserByUsernameEvent(requestId));
        }
        //
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        Log.d("FindUserByUsername", "CANCELLED");
        BusProvider.getInstance().post(new FindUserByUsernameEvent(firebaseError, requestId));
    }
}
