package fr.gobelins.crm14.workshop_android_crm14.discussion;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by risq on 10/15/15.
 */
public class Discussion {
    private String uid;
    private String authorUid;
    private String guestUid;
    private String encryptedPassPhrase;
    private String encryptedAuthorChallengeSecret;
    private String encryptedGuestChallengeSecret;
    private Date creationDate;
    private ArrayList<String> messagesUids;
}
