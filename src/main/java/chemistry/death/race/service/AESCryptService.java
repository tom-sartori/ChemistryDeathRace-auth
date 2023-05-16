package chemistry.death.race.service;

import com.google.common.io.Resources;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static org.apache.commons.codec.binary.Hex.encodeHex;
import static org.apache.commons.io.FileUtils.writeStringToFile;

@Singleton
public class AESCryptService {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;    // 128 default; 192 and 256 also possible

    public String encrypt(String valueToEncrypt) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(loadKey().getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedValue = cipher.doFinal(valueToEncrypt.getBytes());
        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public String decrypt(String encryptedValue) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(loadKey().getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedValue);
        byte[] decryptedValue = cipher.doFinal(decodedValue);
        return new String(decryptedValue);
    }

    private static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_SIZE);
        return keyGenerator.generateKey();
    }

    private static void saveKey(SecretKey key, File file) throws IOException {
        byte[] encoded = key.getEncoded();
        char[] hex = encodeHex(encoded);
        String data = String.valueOf(hex);
        writeStringToFile(file, data);
    }

    private static String loadKey() {
        URL url = Resources.getResource("aes/key.pem");
        try {
            return Resources.toString(url, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        String fileName = "src/main/resources/aes/key.pem";
//        try {
//            saveKey(generateKey(), new File(fileName));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

