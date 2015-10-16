package fr.gobelins.crm14.workshop_android_crm14.services.user.findUserByUserName;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getCurrentUserData.GetCurrentUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/16/15.
 */
public class FindUserByUsernameHandler implements ChildEventListener {
    private int requestId;

    public FindUserByUsernameHandler(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        BusProvider.getInstance().post(new FindUserByUsernameEvent(dataSnapshot.getKey().toString(), requestId));
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        BusProvider.getInstance().post(new FindUserByUsernameEvent(firebaseError, requestId));
    }
}
