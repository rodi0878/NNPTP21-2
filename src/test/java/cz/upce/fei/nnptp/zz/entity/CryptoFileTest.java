/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.zz.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
    public void testWriteFile() throws FileNotFoundException, IOException {
        //Password has to have 8 symbols
        String password = "password";
        String cipheredContent = "ę(ÝßÇüÔ¬rµď•ÓĹś“‡ç~/§I%a°’?";
        String content = "This is content of cryptoFile";
        File file = new File("testWriteFile.txt");

        CryptoFile.writeFile(file, password, content);

        String fileResult = readFileInString("testWriteFile.txt");
        assertEquals(cipheredContent, fileResult);

        String result = CryptoFile.readFile(file, password);
        assertEquals(content, result);

        file.delete();

    }

    private static String readFileInString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "Windows-1250"))) {

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                contentBuilder.append(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}
