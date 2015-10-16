package fr.gobelins.crm14.workshop_android_crm14.discussion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by risq on 10/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Discussion {
    @JsonIgnore
    private String uid;
    private String authorUid;
    private String guestUid;
    private String encryptedPassPhrase;
    private String encryptedAuthorChallengeSecret;
    private String encryptedGuestChallengeSecret;
    private Date creationDate;
    private List<String> messagesUids;

    public Discussion() {
    }

    public Discussion(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(String authorUid) {
        this.authorUid = authorUid;
    }

    public String getGuestUid() {
        return guestUid;
    }

    public void setGuestUid(String guestUid) {
        this.guestUid = guestUid;
    }

    public String getEncryptedPassPhrase() {
        return encryptedPassPhrase;
    }

    public void setEncryptedPassPhrase(String encryptedPassPhrase) {
        this.encryptedPassPhrase = encryptedPassPhrase;
    }

    public String getEncryptedAuthorChallengeSecret() {
        return encryptedAuthorChallengeSecret;
    }

    public void setEncryptedAuthorChallengeSecret(String encryptedAuthorChallengeSecret) {
        this.encryptedAuthorChallengeSecret = encryptedAuthorChallengeSecret;
    }

    public String getEncryptedGuestChallengeSecret() {
        return encryptedGuestChallengeSecret;
    }

    public void setEncryptedGuestChallengeSecret(String encryptedGuestChallengeSecret) {
        this.encryptedGuestChallengeSecret = encryptedGuestChallengeSecret;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getMessagesUids() {
        return messagesUids;
    }

    public void setMessagesUids(List<String> messagesUids) {
        this.messagesUids = messagesUids;
    }
}
