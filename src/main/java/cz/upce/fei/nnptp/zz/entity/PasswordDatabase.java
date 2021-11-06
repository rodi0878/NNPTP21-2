/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.zz.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Roman
 */
public class PasswordDatabase {
    private final File file;
    private final String password;
    
    private List<Password> passwords;

    public PasswordDatabase(File file, String password) {
        this.file = file;
        this.password = password;
    }
    
    public void load() throws FileNotFoundException {
        String passwordsInString = CryptoFile.readFile(file, password);
        if(passwordsInString == null){
            throw new FileNotFoundException("Something at reading file was wrong, look at log to more informations.");
        }
        passwords = new JSON().fromJson(passwordsInString);
    }
    
    public void save() {
        String passwordsContext = new JSON().toJson(passwords);
        CryptoFile.writeFile(file, password, passwordsContext);
    }
    
    public void add(Password password) {
        passwords.add(password);
    }
    
    public Password findEntryByTitle(String title) {
        for (Password password : passwords) {
            
            if (password.hasParameter(Parameter.StandardizedParameters.TITLE)) {
                Parameter.TextParameter titleParam;
                titleParam = (Parameter.TextParameter)password.getParameter(Parameter.StandardizedParameters.TITLE);
                if (titleParam.getValue().equals(titleParam)) {
                    return password;
                }
            }
        }
        return null;
    }    
}
