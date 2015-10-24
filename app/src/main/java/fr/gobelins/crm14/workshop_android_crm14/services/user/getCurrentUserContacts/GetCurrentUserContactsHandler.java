package fr.gobelins.crm14.workshop_android_crm14.services.user.getCurrentUserContacts;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.user.findUserByUserName.FindUserByUsernameEvent;

/**
 * Created by risq on 10/24/15.
 */
public class GetCurrentUserContactsHandler implements ChildEventListener {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Log.d("GetCurrentUserContacts", "onChildAdded" + dataSnapshot.getKey());
        BusProvider.getInstance()
                .post(new GetCurrentUserContactsEvent(dataSnapshot.getKey().toString(), GetCurrentUserContactsEvent.CONTACT_ADD));
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//        Log.d("GetCurrentUserContacts", "onChildChanged" + dataSnapshot.getKey());
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        Log.d("GetCurrentUserContacts", "onChildRemoved" + dataSnapshot.getKey());
        BusProvider.getInstance()
                .post(new GetCurrentUserContactsEvent(dataSnapshot.getKey().toString(), GetCurrentUserContactsEvent.CONTACT_REMOVE));
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//        Log.d("GetCurrentUserContacts", "onChildMoved" + dataSnapshot.getKey());
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
//        Log.d("GetCurrentUserContacts", firebaseError.getDetails());
        BusProvider.getInstance()
                .post(new GetCurrentUserContactsEvent(firebaseError));
    }
}
