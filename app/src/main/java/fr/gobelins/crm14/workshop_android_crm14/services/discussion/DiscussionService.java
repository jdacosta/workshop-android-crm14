package fr.gobelins.crm14.workshop_android_crm14.services.discussion;

import android.util.Log;

import com.squareup.otto.Subscribe;

import fr.gobelins.crm14.workshop_android_crm14.discussion.Discussion;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.DatabaseService;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthService;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.getDiscussion.GetDiscussionEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.getDiscussionById.GetDiscussionByIdHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.getDiscussionIdByContact.GetDiscussionIdByContactHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.saveDiscussion.SaveDiscussionEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.saveDiscussion.SaveDiscussionHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.user.saveUserData.SaveUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.user.saveUserData.SaveUserDataHandler;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/16/15.
 */
public class DiscussionService {
    private static final String TAG = "DiscussionService";
    private static DiscussionService ourInstance = new DiscussionService();

    public static DiscussionService getInstance() {
        return ourInstance;
    }

    private DiscussionService() {
        BusProvider.getInstance().register(this);
    }

    public void saveDiscussion(Discussion discussion) {
        Log.d(TAG, "Saving discussion " + discussion.toString());
        DatabaseService.getInstance()
                .getFirebase()
                .child("discussion")
                .child(discussion.getUid())
                .setValue(discussion, new SaveDiscussionHandler());
    }

    public void getDiscussionById(String discussionId) {
        Log.d(TAG, "getDiscussionById " + discussionId);
        DatabaseService.getInstance()
                .getFirebase()
                .child("discussion")
                .child(discussionId)
                .addListenerForSingleValueEvent(new GetDiscussionByIdHandler());
    }

    public void addDiscussionToUsers(Discussion discussion) {
        Log.d(TAG, "addDiscussionToUsers " + discussion);
        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .child(discussion.getAuthorUid())
                .child("discussions")
                .child(discussion.getGuestUid())
                .setValue(discussion);

        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .child(discussion.getGuestUid())
                .child("discussions")
                .child(discussion.getAuthorUid())
                .setValue(discussion.getUid());
    }

    public void getDiscussionIdByContact(User contact) {
        Log.d(TAG, "getDiscussionIdByContact " + contact.getUsername() + " " + contact.getUid());
        User currentUser = AuthService.getInstance()
                .getCurrentUser();
        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .child(currentUser.getUid())
                .child("discussions")
                .child(contact.getUid())
                .addListenerForSingleValueEvent(new GetDiscussionIdByContactHandler(currentUser, contact));
    }

    @Subscribe
    public void onSaveDiscussion(SaveDiscussionEvent event) {
        if (!event.hasError()){
            Log.d(TAG, "Save user data success");
        } else {
            Log.d(TAG, "Error saving user data success: " + event.getCode() + " - " + event.getMessage());
        }
    }

    @Subscribe
    public void onGetDiscussion(GetDiscussionEvent event) {
        if (!event.hasError()) {
            Log.d(TAG, "Get user data success: " + event.getDiscussion().toString());
        } else {
            Log.d(TAG, "Error saving user data success: " + event.getCode() + " - " + event.getMessage());
        }
    }
}
