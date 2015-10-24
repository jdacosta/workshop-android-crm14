package fr.gobelins.crm14.workshop_android_crm14.services.discussion.getDiscussionIdByContact;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.security.GeneralSecurityException;

import fr.gobelins.crm14.workshop_android_crm14.discussion.Discussion;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.crypto.RSACryptoService;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.DiscussionService;
import fr.gobelins.crm14.workshop_android_crm14.services.user.findUserByUserName.FindUserByUsernameEvent;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/24/15.
 */
public class GetDiscussionIdByContactHandler implements ValueEventListener {
    private final User guest;
    private final User author;
    private int requestId;

    public GetDiscussionIdByContactHandler(User author, User guest) {
        this.author = author;
        this.guest = guest;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.getValue() != null) {
            String discussionId = dataSnapshot.getValue().toString();
            DiscussionService.getInstance()
                    .getDiscussionById(discussionId);
        } else {
            Log.d("discussionID", "null");
            try {
                String uid = RSACryptoService.generateId();
                Discussion discussion = new Discussion(uid);
                discussion.setAuthorUid(author.getUid());
                discussion.setGuestUid(guest.getUid());
                DiscussionService.getInstance()
                        .saveDiscussion(discussion);

                DiscussionService.getInstance()
                        .addDiscussionToUsers(discussion);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
//        if (dataSnapshot.getChildren().iterator().hasNext()) {
//            BusProvider.getInstance()
//                    .post(new FindUserByUsernameEvent(dataSnapshot.getChildren().iterator().next().getKey(), requestId));
//        } else {
//            BusProvider.getInstance()
//                    .post(new FindUserByUsernameEvent(requestId));
//        }
        //
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        Log.d("FindUserByUsername", "CANCELLED");
        BusProvider.getInstance().post(new FindUserByUsernameEvent(firebaseError, requestId));
    }
}
