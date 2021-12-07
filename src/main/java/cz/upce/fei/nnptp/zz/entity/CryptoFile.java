/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.zz.entity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Roman
 */
public class CryptoFile {

    private static final String ALGORITHM_NAME = "DES";
    private static final String TRANSFORMATION_NAME = "DES/ECB/PKCS5Padding";

    public static String readFile(File file, String password) {
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), ALGORITHM_NAME);
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_NAME);
            CipherInputStream cipherInputStream = new CipherInputStream(fileInputStream, cipher);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            String result;
            try (DataInputStream dataInputStream = new DataInputStream(cipherInputStream)) {
                result = dataInputStream.readUTF();
            }
            cipher.doFinal();

            return result;
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException | IllegalBlockSizeException | BadPaddingException exception) {
            return null;
        }
    }

    public static void writeFile(File file, String password, String contents) {
        SecretKey secretKey = new SecretKeySpec(password.getBytes(), ALGORITHM_NAME);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_NAME);
            CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            try (DataOutputStream dataOutputStream = new DataOutputStream(cipherOutputStream)) {
                dataOutputStream.writeUTF(contents);
            }
            cipher.doFinal();
        } catch (IllegalBlockSizeException | BadPaddingException | IOException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException exception) {
            Logger.getLogger(CryptoFile.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
}
