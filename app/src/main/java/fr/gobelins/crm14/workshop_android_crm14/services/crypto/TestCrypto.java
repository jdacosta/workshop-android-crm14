package fr.gobelins.crm14.workshop_android_crm14.services.crypto;


import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import fr.gobelins.crm14.workshop_android_crm14.services.utils.Preferences;

public class TestCrypto {

    private static final String TAG = "TestCrypto";

    /**
     * STEP 1: Generate Public/Private RSA keys
     * and saved keys in local storage.
     */
    public static void generateAndStoreRSAKeys() {
        if (!Preferences.getBoolean(Preferences.RSA_GENERATED)) {
            Log.d(TAG, "Generate and store RSA KeyPair");
            CryptoService.storeKeys(CryptoService.generateKeyPair());
        } else {
            Log.d(TAG, "Private key : " + Preferences.getString(Preferences.RSA_PRIVATE_KEY));
            Log.d(TAG, "Public key : " + Preferences.getString(Preferences.RSA_PUBLIC_KEY));
        }
    }

    /**
     * STEP 2: Generate passphrase and signature (sender)
     */
    public static void generateAndStoreSignature() {

        if (!Preferences.getBoolean(Preferences.RSA_GENERATED)) {
            generateAndStoreRSAKeys();
        }

        if (!Preferences.getBoolean(Preferences.SIGNATURE_GENERATED)) {
            Log.d(TAG, "Generate passphrase");
            String passphrase = null;
            try {
                passphrase = CryptoService.generatePassphrase();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "Generate and store signature");
            CryptoService.storeSignature(CryptoService.generateSignature(passphrase));
        }
        else {
            Log.d(TAG, "Signature " + Preferences.getString(Preferences.SIGNATURE));
        }
    }


    /**
     * STEP 3: Check signature (receive)
     */
    public static void checkSignature() {

        // TODO : Get signature via Firebase
        String signature = Preferences.getString(Preferences.SIGNATURE);

        // check signature
        boolean checkSignature = false;
        try {
            // TODO : GET PUBLIC KEY WITH FIREBASE (SENDER)
            // TODO : Fix with sender passphrase
            checkSignature = RsaEcbService.checkSignature(signature, "PASSPHRASE", CryptoService.getPublicKey());
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (!checkSignature) {
            throw new AssertionError();
        } else {
            Log.d(TAG, "RSA Signing [ OK ]");
        }
    }


    /**
     * STEP 4 : Encrypt message (sender)
     */
    public static String encryptMessage(String message) {
        String encryptedMessage = null;
        try {
            encryptedMessage = RsaEcbService.encrypt(message, CryptoService.getPublicKey()); // TODO : Get Public key via Firebase
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (encryptedMessage == null) throw new AssertionError();

        return encryptedMessage;
    }

    /**
     * STEP 5 : Decrypt message (sender)
     */
    public static String decryptMessage(String encryptedMessage) {
        String message = null;
        try {
            message = RsaEcbService.decrypt(encryptedMessage, CryptoService.getPrivateKey());
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (message != null)  throw new AssertionError();

        return message;
    }
}
