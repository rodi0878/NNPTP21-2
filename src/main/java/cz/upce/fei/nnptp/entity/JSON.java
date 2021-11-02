/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.upce.fei.nnptp.entity;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Roman
 */
public class JSON {
    
    public String toJson(List<Password> passwords) {
        // TODO: support all parameters!!!
        StringBuilder output = new StringBuilder("[");
        for (Password password : passwords) {
            if (!output.toString().equals("[")) {
                output.append(",");
            }
            output.append("{");
            output.append("id:");
            output.append(password.getId());
            output.append(",");
            output.append("password:\"");
            output.append(password.getPassword());
            output.append("\"");
            for (Map.Entry<String, Parameter> parameter : password.getParameters().entrySet()) {
                output.append(",");
                output.append(parameter.getKey());
                output.append(":\"");
                output.append(parameter.getValue());
                output.append("\"");
            }
            output.append("}");
        }
        output.append("]");
        
        return output.toString();
    }
    
    public List<Password> fromJson(String json) {
        throw new RuntimeException("NYI");
    }
}
