package fr.gobelins.crm14.workshop_android_crm14.services.discussion.getDiscussion;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import fr.gobelins.crm14.workshop_android_crm14.discussion.Discussion;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;

/**
 * Created by risq on 10/14/15.
 */
public class GetDiscussionHandler implements ValueEventListener{

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        BusProvider.getInstance().post(new GetDiscussionEvent(dataSnapshot.getValue(Discussion.class)));
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        BusProvider.getInstance().post(new GetDiscussionEvent(firebaseError));
    }
}
