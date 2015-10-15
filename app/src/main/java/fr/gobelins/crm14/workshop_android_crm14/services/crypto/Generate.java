package fr.gobelins.crm14.workshop_android_crm14.services.crypto;

import com.tozny.crypto.android.AesCbcWithIntegrity;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

public class Generate {

    private static final int SALT_LENGTH = 256;
    private static final int PASSPHRASE_LENGTH = 512;
    private static final String RANDOM_ALGORITHM = "SHA1PRNG";


    public static String salt() throws GeneralSecurityException {

        AesCbcWithIntegrity.generateSalt();
        SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);

        return new BigInteger(SALT_LENGTH, random).toString(32);
    }

    public static String passphrase() throws GeneralSecurityException {

        AesCbcWithIntegrity.generateSalt();
        SecureRandom random = SecureRandom.getInstance(RANDOM_ALGORITHM);

        return new BigInteger(PASSPHRASE_LENGTH, random).toString(32);
    }
}
