/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.zz.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Roman
 */
public class JSON {
    
    
    public String toJson(List<Password> passwords)  {
        // TODO: support all parameters!!!
        String output = "[";
        for (Password password : passwords) {
            if (!output.isEmpty() && !output.equals("["))
                output += ",";
            output += "{";
            output += "id:" + password.getId() + ",";
            output += "password:\"" + password.getPassword()+"\"";
            
            output += "}";
        }
        output += "]";
        
        return output;
    }
    
    public List<Password> fromJson(String json) {
        List<Password> passwords = new ArrayList<>();
        String[] jsonParts = json.split("\\{|},|}");
        for (int i = 0; i < jsonParts.length; i++) {
            if(!jsonParts[i].equals("[") && !jsonParts[i].equals("]") && !jsonParts[i].isEmpty()){
                String[] passwordParts = jsonParts[i].split(":|,");
                int passwordId = Integer.parseInt(passwordParts[1]);
                String passwordString = passwordParts[3].substring(1,passwordParts[3].length()-1);
                Password password = new Password(passwordId, passwordString);
                passwords.add(password);
            }
        }
        return passwords;
    }
}
