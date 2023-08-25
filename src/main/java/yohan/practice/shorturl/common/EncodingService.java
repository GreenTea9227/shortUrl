package yohan.practice.shorturl.common;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncodingService {
    public Long shaEncode(String originalUrl) {
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digest = messageDigest.digest(originalUrl.getBytes());

            for (byte hashByte : digest) {
                String hex = Integer.toHexString(0xff & hashByte);
                hexString.append(hex);
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return Long.parseLong(hexString.substring(0, 16), 16);
    }

    public String base62Encode(Long decimalValue) {

        String base = Base62Character.BASE.getBASE62_CHARS();
        StringBuilder result = new StringBuilder();
        while (decimalValue > 0) {
            int index = (int) (decimalValue % 62);
            char base62Char = base.charAt(index);
            result.insert(0, base62Char);
            decimalValue /= 62;
        }
        return result.toString();
    }
}
