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
    private String signature;
    private String authorSignature;
    private String guestSignature;
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

    public String getAuthorSignatureSecret() {
        return authorSignature;
    }

    public void setAuthorSignatureSecret(String authorSignature) {
        this.authorSignature = authorSignature;
    }

    public String getGuestSignatureSecret() {
        return guestSignature;
    }

    public void setGuestSignatureSecret(String guestSignature) {
        this.guestSignature = guestSignature;
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Discussion{" +
                "uid='" + uid + '\'' +
                ", authorUid='" + authorUid + '\'' +
                ", guestUid='" + guestUid + '\'' +
                ", encryptedPassPhrase='" + encryptedPassPhrase + '\'' +
                ", signature='" + signature + '\'' +
                ", authorSignature='" + authorSignature + '\'' +
                ", guestSignature='" + guestSignature + '\'' +
                ", creationDate=" + creationDate +
                ", messagesUids=" + messagesUids +
                '}';
    }
}
