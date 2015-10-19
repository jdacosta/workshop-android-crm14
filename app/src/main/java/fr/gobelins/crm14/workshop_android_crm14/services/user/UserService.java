package fr.gobelins.crm14.workshop_android_crm14.services.user;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import fr.gobelins.crm14.workshop_android_crm14.services.BusProvider;
import fr.gobelins.crm14.workshop_android_crm14.services.DatabaseService;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthService;
import fr.gobelins.crm14.workshop_android_crm14.services.user.findUserByUserName.FindUserByUsernameEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.user.findUserByUserName.FindUserByUsernameHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getCurrentUserData.GetCurrentUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getCurrentUserData.GetCurrentUserDataHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.user.getUserData.GetUserDataHandler;
import fr.gobelins.crm14.workshop_android_crm14.services.user.saveUserData.SaveUserDataEvent;
import fr.gobelins.crm14.workshop_android_crm14.services.user.saveUserData.SaveUserDataHandler;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/16/15.
 */
public class UserService {
    private static final String TAG = "UserService";
    private static UserService ourInstance = new UserService();

    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
        BusProvider.getInstance().register(this);
    }

    public void saveUserData(User user) {
        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .child(user.getUid())
                .setValue(user, new SaveUserDataHandler());
    }

    public void saveUserPublicKey(User user, String pubKey) {
        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .child(user.getUid())
                .child("pubKey")
                .setValue(pubKey); // TODO : ADD CUSTOM HANDLER
    }

    public void getCurrentUserData() {
        User user = AuthService.getInstance()
                .getCurrentUser();
        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .child(user.getUid())
                .addValueEventListener(new GetCurrentUserDataHandler());
    }

    public void getUserData(User user) {
        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .child(user.getUid())
                .addValueEventListener(new GetUserDataHandler());
    }

    public void addContactByUsername(String contactUsername) {
        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .orderByChild("username")
                .equalTo(contactUsername)
                .addListenerForSingleValueEvent(new FindUserByUsernameHandler(FindUserByUsernameEvent.FIND_USER_TO_ADD_CONTACT));
    }

    private void addContactToCurrentUser(User contact) {
        User user = AuthService.getInstance()
                .getCurrentUser();

        DatabaseService.getInstance()
                .getFirebase()
                .child("user")
                .child(user.getUid())
                .child("contacts")
                .child(contact.getUid())
                .setValue(true);
    }

    @Subscribe
    public void onSaveUserData(SaveUserDataEvent event) {
        if (!event.hasError()) {
            Log.d(TAG, "Save user data success");
        } else {
            Log.d(TAG, "Error saving user data: " + event.getCode() + " - " + event.getMessage());
        }
    }

    @Subscribe
    public void onGetUserData(GetUserDataEvent event) {
        if (!event.hasError()) {
            Log.d(TAG, "Get user data success: " + event.getUser().toString());
        } else {
            Log.d(TAG, "Error saving user data: " + event.getCode() + " - " + event.getMessage());
        }
    }

    @Subscribe
    public void onFindUserByUsername(FindUserByUsernameEvent event) {
        if (!event.hasError() && event.hasFoundUser()) {
            Log.d(TAG, "Find user by username success: " + event.getUser().toString());
            if (event.getRequestId() == FindUserByUsernameEvent.FIND_USER_TO_ADD_CONTACT) {
                Log.d(TAG, "Adding found user to contacts: " + event.getUser().toString());
                addContactToCurrentUser(event.getUser());
            }
        } else {
            Log.d(TAG, "Error finding user by username: " + event.getCode() + " - " + event.getMessage());
        }
    }
}
