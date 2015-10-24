package fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/14/15.
 */
public class GetUserDataHandler implements ValueEventListener{
    private int requestId;
    private String userId;

    public GetUserDataHandler(int requestId, String userId) {
        this.requestId = requestId;
        this.userId = userId;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        User user = dataSnapshot.getValue(User.class);
        user.setUid(userId);
        BusProvider.getInstance().post(new GetUserDataEvent(user, requestId));
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        BusProvider.getInstance().post(new GetUserDataEvent(firebaseError, requestId));
    }
}
