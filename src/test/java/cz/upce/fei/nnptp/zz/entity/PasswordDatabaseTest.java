package cz.upce.fei.nnptp.zz.entity;

import org.junit.jupiter.api.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordDatabaseTest {

    public PasswordDatabaseTest() {
    }

    @BeforeAll
    public static void beforeAll() {
    }

    @AfterAll
    public static void afterAll() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSave() {

        List<Password> passwords = new ArrayList<>();
        passwords.add(new Password(0, "password1"));
        passwords.add(new Password(1, "password2"));

        String expectedPasswordsInString = new JSON().toJson(passwords);

        PasswordDatabase passwordDatabase = new PasswordDatabase(new File("password_database_test.txt"), "password");
        passwordDatabase.add(passwords.get(0));
        passwordDatabase.add(passwords.get(1));

        passwordDatabase.save();

        String passwordsInString = CryptoFile.readFile(new File("password_database_test.txt"), "password");

        assertEquals(expectedPasswordsInString, passwordsInString);
    }

    @Test
    public void testFindEntryByTitle() {
        PasswordDatabase passwordDatabase = new PasswordDatabase(null, "");

        HashMap<String, Parameter> parametersForPassword1 = new HashMap<>();
        parametersForPassword1.put(Parameter.StandardizedParameters.DESCRIPTION, new Parameter.TextParameter("TextParameter1"));
        parametersForPassword1.put(Parameter.StandardizedParameters.TITLE, new Parameter.TextParameter("TextParameter2"));

        HashMap<String, Parameter> parametersForPassword2 = new HashMap<>();
        parametersForPassword2.put(Parameter.StandardizedParameters.TITLE, new Parameter.TextParameter("TextParameter3"));
        parametersForPassword2.put(Parameter.StandardizedParameters.WEBSITE, new Parameter.TextParameter("TextParameter4"));

        Password password1 = new Password(0, "password0", parametersForPassword1);
        Password password2 = new Password(1, "password1", parametersForPassword2);

        passwordDatabase.add(password1);
        passwordDatabase.add(password2);

        Password result1 = passwordDatabase.findEntryByTitle("TextParameter2");
        assertEquals(password1, result1);

        Password result2 = passwordDatabase.findEntryByTitle("TextParameter3");
        assertEquals(password2, result2);

        Password result3 = passwordDatabase.findEntryByTitle("TextParameter1");
        assertNull(result3);

    }

    @Test
    public void load() {
        List<Password> passwords = new ArrayList<>();
        passwords.add(new Password(0, "password1"));
        passwords.add(new Password(1, "password2"));

        PasswordDatabase passwordDatabase1 = new PasswordDatabase(new File("password_database_test.txt"), "password");
        passwordDatabase1.add(passwords.get(0));
        passwordDatabase1.add(passwords.get(1));

        Password expectedPassword = passwordDatabase1.findByIndex(0);
        passwordDatabase1.save();

        PasswordDatabase passwordDatabase2 = new PasswordDatabase(new File("password_database_test.txt"), "password");
        passwordDatabase2.load();
        Password resultPassword = passwordDatabase2.findByIndex(0);

        String expectedResult = expectedPassword.getId()+":"+expectedPassword.getPassword();
        String result = resultPassword.getId()+":"+resultPassword.getPassword();

        assertEquals(expectedResult, result);
    }
}
