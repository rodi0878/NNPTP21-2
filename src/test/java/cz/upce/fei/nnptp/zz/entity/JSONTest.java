/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.zz.entity;

import java.io.File;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONTest {

    public JSONTest() {
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

    @Test
    public void testToJson() {
        List<Password> passwords = new ArrayList<>();
        passwords.add(new Password(0, "Password1!"));
        passwords.add(new Password(1, "Password2?"));
        
        String resultJSON = new JSON().toJson(passwords);
        String expectedJSON = "[{id:0,password:\"Password1!\"},{id:1,password:\"Password2?\"}]";
        assertEquals(expectedJSON, resultJSON);
    }

    @Test
    public void testFromJson() {
        String json = "[{id:0,password:\"Password1!\"},{id:1,password:\"Password2?\"}]";
        List<Password> ExpectedPasswords = new ArrayList<>();
        ExpectedPasswords.add(new Password(0, "Password1!"));
        ExpectedPasswords.add(new Password(1, "Password2?"));

        List<Password>passwords = new JSON().fromJson(json);

        String expectedResult = "";
        for (Password password : ExpectedPasswords) {
            expectedResult += password.getId()+":"+password.getPassword()+",";
        }
        String result = "";
        for (Password password : passwords) {
            result += password.getId()+":"+password.getPassword()+",";
        }

        assertEquals(expectedResult, result);
    }
}
