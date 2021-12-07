/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.zz.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Roman
 */
public class CryptoFileTest {

    public CryptoFileTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of readFile method, of class CryptoFile.
     */
    @Test
    public void testReadFile() {
        //Password has to have 8 symbols
        String password = "password";
        String wrongPassword = "WrongPas";
        String content = "This is content of cryptoFile";
        File file = new File("testReadFile.txt");
        CryptoFile.writeFile(file, password, content);

        String result = CryptoFile.readFile(file, password);

        assertEquals(content, result);

        String resultWrong = CryptoFile.readFile(file, wrongPassword);

        assertNull(resultWrong);

        File fileDeleted = new File("testReadFile.txt");
        fileDeleted.delete();
    }

    /**
     * Test of writeFile method, of class CryptoFile.
     */
    @Test
    public void testWriteFile() throws FileNotFoundException {
        //Password has to have 8 symbols
        String password = "password";
        String cipheredContent = "ę(ÝßÇüÔ¬rµď•ÓĹś“‡ç~/§I%a°’?";
        String content = "This is content of cryptoFile";
        File file = new File("testWriteFile.txt");

        CryptoFile.writeFile(file, password, content);

        String fileResult = null;
        try {
            fileResult = new String(Files.readAllBytes(Paths.get("testWriteFile.txt")));
        } catch (IOException ex) {
            Logger.getLogger(CryptoFileTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        assertEquals(cipheredContent, fileResult);

        String result = CryptoFile.readFile(file, password);
        assertEquals(content, result);

        file.delete();

    }

}
