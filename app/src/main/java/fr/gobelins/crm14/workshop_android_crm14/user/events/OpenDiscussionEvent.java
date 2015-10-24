package fr.gobelins.crm14.workshop_android_crm14.user.events;

import fr.gobelins.crm14.workshop_android_crm14.user.User;

/**
 * Created by risq on 10/24/15.
 */
public class OpenDiscussionEvent {
    private final User contact;


    public OpenDiscussionEvent(User contact) {
        this.contact = contact;
    }

    public User getContact() {
        return contact;
    }
}
