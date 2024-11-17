/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objectos;

import java.io.Serializable;

/**
 *
 * @author Charque-Suede
 */
public class Funcionario extends Pessoa implements Serializable{
    private String user,password;

    public Funcionario(String user, String password, 
            String nome, String apelido, String endereco, String email,
            String codigo, String celular) {
        super(nome, apelido, endereco, email, codigo, celular);
        this.user = user;
        this.password = password;
    }
    
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    

    @Override
    public String toString() {
        return    "\n"+super.toString()+""
                + "\nUser: "+getUser()+""
                + "\nPassword: "+getPassword()
                + "\n----------------------------------";
    }
    
    
    
}
