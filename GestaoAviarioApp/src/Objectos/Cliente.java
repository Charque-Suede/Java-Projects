
package Objectos;

import java.io.Serializable;

public class Cliente extends Pessoa implements Serializable{

    
    public Cliente( String nome, String apelido, 
            String endereco, String email, String codigo, String celular) {
        super(nome, apelido, endereco, email, codigo, celular);
    }

   

    @Override
    public String toString() {
        return "\n"+super.toString()+"\n";
               
    }

    
    
    
}
