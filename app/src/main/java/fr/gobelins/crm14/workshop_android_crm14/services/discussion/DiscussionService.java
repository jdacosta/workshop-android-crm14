package fr.gobelins.crm14.workshop_android_crm14.services.discussion;

import android.util.Log;

import com.squareup.otto.Subscribe;

import fr.gobelins.crm14.workshop_android_crm14.discussion.Discussion;
import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.DatabaseService;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.getDiscussion.GetDiscussionEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.saveDiscussion.SaveDiscussionEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.saveDiscussion.SaveDiscussionHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.user.saveUserData.SaveUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.user.saveUserData.SaveUserDataHandler;

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
        DatabaseService.getInstance()
                .getFirebase()
                .child("discussion")
                .child(discussion.getUid())
                .setValue(discussion, new SaveDiscussionHandler());
    }

    public void getDiscussion(Discussion discussion) {
        DatabaseService.getInstance()
                .getFirebase()
                .child("discussion")
                .child(discussion.getUid())
                .addValueEventListener(new GetUserDataHandler());
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
