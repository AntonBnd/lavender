package by.lavender.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
    private static Logger logger = LogManager.getLogger(PasswordHash.class);

    public static String SHAHashing(String st) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such parameter for MessageDigest.getInstance method");
        }

        BigInteger bigInteger = new BigInteger(1, digest);
        String sHAHex = bigInteger.toString(16);

        while( sHAHex.length() < 32 ){
            sHAHex = "0" + sHAHex;
        }

        return sHAHex;
    }
}
