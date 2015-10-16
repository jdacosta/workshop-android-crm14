package fr.gobelins.crm14.workshop_android_crm14.services.crypto;

import android.util.Log;

import com.tozny.crypto.android.AesCbcWithIntegrity;

import org.spongycastle.util.io.pem.PemObject;
import org.spongycastle.util.io.pem.PemWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.SecureRandom;

import fr.gobelins.crm14.workshop_android_crm14.services.utils.Preferences;

public class CryptoService {

    private static final int SALT_LENGTH = 256;
    private static final int PASSPHRASE_LENGTH = 512;
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";
    private static final String TAG = "CryptoService";


    public static String generateSalt() throws GeneralSecurityException {

        AesCbcWithIntegrity.generateSalt();
        SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);

        return new BigInteger(SALT_LENGTH, random).toString(32);
    }

    public static String generatePassphrase() throws GeneralSecurityException {

        AesCbcWithIntegrity.generateSalt();
        SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);

        return new BigInteger(PASSPHRASE_LENGTH, random).toString(32);
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
}
