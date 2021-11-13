package cz.upce.fei.nnptp.zz.entity;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PasswordDatabaseTest {

    public PasswordDatabaseTest(){
    }

    @BeforeAll
    static void beforeAll() {
    }

    @AfterAll
    static void afterAll() {
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {

        List<Password> passwords = new ArrayList<>();
        passwords.add(new Password(0,"password1"));
        passwords.add(new Password(1,"password2"));

        String expectedPasswordsInString = new JSON().toJson(passwords);

        PasswordDatabase passwordDatabase = new PasswordDatabase(new File("password_database_test.txt"),"password");
        passwordDatabase.add(passwords.get(0));
        passwordDatabase.add(passwords.get(1));

        passwordDatabase.save();

        String passwordsInString = CryptoFile.readFile(new File("password_database_test.txt"),"password");

        assertEquals(expectedPasswordsInString,passwordsInString);
    }
}