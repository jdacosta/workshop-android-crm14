package fr.gobelins.crm14.workshop_android_crm14.services.crypto;

import android.support.annotation.NonNull;
import android.util.Log;

import com.tozny.crypto.android.AesCbcWithIntegrity;

import org.spongycastle.util.io.pem.PemObject;
import org.spongycastle.util.io.pem.PemWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import fr.gobelins.crm14.workshop_android_crm14.discussion.Discussion;
import fr.gobelins.crm14.workshop_android_crm14.services.auth.AuthService;
import fr.gobelins.crm14.workshop_android_crm14.services.discussion.DiscussionService;
import fr.gobelins.crm14.workshop_android_crm14.services.user.UserService;
import fr.gobelins.crm14.workshop_android_crm14.services.utils.Preferences;
import fr.gobelins.crm14.workshop_android_crm14.user.User;

public class RSACryptoService {

    private static final int PASSPHRASE_LENGTH = 512;
    private static final int ID_LENGTH = 128;
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";
    private static final String TAG = "RSACryptoService";

    @NonNull
    public static String generatePassphrase() throws GeneralSecurityException {
        SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);
        return new BigInteger(PASSPHRASE_LENGTH, random).toString(32);
    }

    @NonNull
    public static String generateId() throws GeneralSecurityException {
        SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);
        return new BigInteger(ID_LENGTH, random).toString(32);
    }

    @NonNull
    public static KeyPair generateKeyPair() {
        KeyPair keyPair = null;
        try {
            keyPair = RsaEcbService.generateKeys();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (keyPair == null) throw new AssertionError();

        try {
            Log.d(TAG, "public key: " + RsaEcbService.getPublicKeyString(keyPair.getPublic()));
            Log.d(TAG, "private key: " + RsaEcbService.getPrivateKeyString(keyPair.getPrivate()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return keyPair;
    }

    @NonNull
    public static String generateSignature(String passphrase) {
        if (passphrase == null) throw new AssertionError();

        String signature = null;
        try {
            signature = RsaEcbService.genSignature(passphrase, getPrivateKey());
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (signature == null) throw new AssertionError();

        return signature;
    }

    @NonNull
    public static PrivateKey getPrivateKey() {
        String privateKeyString = Preferences.getString(Preferences.RSA_PRIVATE_KEY);

        PrivateKey privateKey = null;
        try {
            privateKey = RsaEcbService.getRSAPrivateKeyFromString(privateKeyString);
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (privateKey == null) throw new AssertionError();

        return privateKey;
    }

    @NonNull
    public static PublicKey getPublicKey() {
        String publicKeyString = Preferences.getString(Preferences.RSA_PUBLIC_KEY);

        PublicKey publicKey = null;
        try {
            publicKey = RsaEcbService.getRSAPublicKeyFromString(publicKeyString);
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (publicKey == null) throw new AssertionError();

        return publicKey;
    }

    public static void writePublicKeyToPreferences(KeyPair keyPair) {
        StringWriter publicStringWriter = new StringWriter();
        try {
            PemWriter pemWriter = new PemWriter(publicStringWriter);
            pemWriter.writeObject(new PemObject("PUBLIC KEY", keyPair.getPublic().getEncoded()));
            pemWriter.flush();
            pemWriter.close();
            Preferences.putString(Preferences.RSA_PUBLIC_KEY, publicStringWriter.toString());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writePrivateKeyToPreferences(KeyPair keyPair) {
        StringWriter privateStringWriter = new StringWriter();
        try {
            PemWriter pemWriter = new PemWriter(privateStringWriter);
            pemWriter.writeObject(new PemObject("PRIVATE KEY", keyPair.getPrivate().getEncoded()));
            pemWriter.flush();
            pemWriter.close();
            Preferences.putString(Preferences.RSA_PRIVATE_KEY, privateStringWriter.toString());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    public static void storeKeys(KeyPair keyPair) {
        if (keyPair == null) throw new AssertionError();

        Log.d(TAG, "Saving the private key in preferences");
        writePublicKeyToPreferences(keyPair);

        Log.d(TAG, "Saving the public key in preferences");
        writePrivateKeyToPreferences(keyPair);

        Log.d(TAG, "Update the state for RSA (RSA_GENERATED : true)");
        Preferences.putBoolean(Preferences.RSA_GENERATED, true);

        Log.d(TAG, "Saving the public key in firebase");
        User currentUser = AuthService.getInstance().getCurrentUser();
        UserService.getInstance().saveUserPublicKey(currentUser,
                Preferences.getString(Preferences.RSA_PUBLIC_KEY));
    }

    public static void storeSignature(String signature) {
        if (signature == null) throw new AssertionError();

        //Log.d(TAG, "Saving the signature in preferences");
        //Preferences.putString(Preferences.SIGNATURE, signature);
        //Log.d(TAG, Preferences.getString(Preferences.SIGNATURE));

        //Log.d(TAG, "Update the state for signature (SIGNATURE_GENERATED : true)"); // TODO : Fix by conversation
        //Preferences.putBoolean(Preferences.SIGNATURE_GENERATED, true);

        Log.d(TAG, "Saving the signature in firebase"); // TODO : Fix with Valentin
        //Discussion currentDiscussion = DiscussionService.getInstance().getCurrentDiscussion();
        //UserService.getInstance().saveUserPublicKey(currentUser,
        //        Preferences.getString(Preferences.RSA_PUBLIC_KEY));
    }

    public static void generateAndStoreRSAKeys() {
        if (!Preferences.getBoolean(Preferences.RSA_GENERATED)) {
            Log.d(TAG, "Generate and store RSA KeyPair");
            storeKeys(generateKeyPair());
        }
        else {
            Log.d(TAG, "Private key : " + Preferences.getString(Preferences.RSA_PRIVATE_KEY));
            Log.d(TAG, "Public key : " + Preferences.getString(Preferences.RSA_PUBLIC_KEY));
        }
    }

    public static void generateAndStoreSignature() {

        if (!Preferences.getBoolean(Preferences.RSA_GENERATED)) {
            generateAndStoreRSAKeys();
        }

        if (!Preferences.getBoolean(Preferences.SIGNATURE_GENERATED)) { // TODO FIX FIREBASE
            Log.d(TAG, "Generate passphrase");
            String passphrase = null;
            try {
                passphrase = generatePassphrase();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "Generate and store signature");
            storeSignature(generateSignature(passphrase));
        }
        else {
            Log.d(TAG, "Signature " + Preferences.getString(Preferences.SIGNATURE)); // TODO FIX FIREBASE
        }
    }

    public static void checkSignature() {

        // TODO : Get signature via Firebase
        String signature = Preferences.getString(Preferences.SIGNATURE);

        // check signature
        boolean checkSignature = false;
        try {
            // TODO : GET PUBLIC KEY WITH FIREBASE (SENDER)
            // TODO : Fix with sender passphrase
            checkSignature = RsaEcbService.checkSignature(signature, "PASSPHRASE", getPublicKey());
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (!checkSignature) {
            throw new AssertionError();
        } else {
            Log.d(TAG, "RSA Signing [ OK ]");
        }
    }

    public static String encryptRSAMessage(String message) {
        String encryptedMessage = null;
        try {
            encryptedMessage = RsaEcbService.encrypt(message, getPublicKey()); // TODO : Get Public key via Firebase
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (encryptedMessage == null) throw new AssertionError();

        return encryptedMessage;
    }

    public static String decryptRSAMessage(String encryptedMessage) {
        String message = null;
        try {
            message = RsaEcbService.decrypt(encryptedMessage, getPrivateKey());
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (message != null)  throw new AssertionError();

        return message;
    }
}
