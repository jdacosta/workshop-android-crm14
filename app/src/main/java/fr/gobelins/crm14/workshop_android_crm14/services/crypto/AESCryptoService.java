package fr.gobelins.crm14.workshop_android_crm14.services.crypto;


import android.support.annotation.NonNull;
import android.util.Log;

import com.tozny.crypto.android.AesCbcWithIntegrity;

import org.spongycastle.util.encoders.Base64;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class AESCryptoService {

    private static final String TAG = "AESCryptoService";

    @NonNull
    private static AesCbcWithIntegrity.SecretKeys getSecretKeys(String password, byte[] salt) {
        AesCbcWithIntegrity.SecretKeys keys = null;

        try {
            keys = AesCbcWithIntegrity.generateKeyFromPassword(password, salt);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (keys == null) throw new AssertionError();

        Log.d(TAG, "Secret keys: " + keys.toString());
        return keys;
    }

    private static byte[] getSalt() {
        byte[] salt = new byte[0];

        try {
            salt = AesCbcWithIntegrity.generateSalt();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (salt.length <= 0) throw new AssertionError();

        Log.d(TAG, "Salt: " + Base64.toBase64String(salt));
        return salt;
    }

    public static AesCbcWithIntegrity.CipherTextIvMac encryptAESMessage(String message, AesCbcWithIntegrity.SecretKeys keys) {
        AesCbcWithIntegrity.CipherTextIvMac encrypted = null;
        try {
            encrypted = AesCbcWithIntegrity.encrypt(message, keys);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (encrypted != null)  throw new AssertionError();

        Log.d(TAG, "Encrypted: " + encrypted.toString());
        return encrypted;
    }

    public static String decryptAESMessage(AesCbcWithIntegrity.CipherTextIvMac dataToDecrypt, AesCbcWithIntegrity.SecretKeys keysDecrypt) {
        String decrypted = null;
        try {
            decrypted = AesCbcWithIntegrity.decryptString(dataToDecrypt, keysDecrypt);
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (decrypted != null)  throw new AssertionError();

        Log.d(TAG, "Decrypted: " + decrypted);
        return decrypted;
    }
}
