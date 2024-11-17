package model.negocio;

import java.io.Serializable;

public class Mecanico extends Pessoa implements Serializable{


    public Mecanico(String id, String nome, int idade, String nacionalidade, String nrBI, String sexo,
            String estadoCivil, String celular, String ususario, String senha) {
        super(id, nome, idade, nacionalidade, nrBI, sexo, estadoCivil, celular, ususario, senha);
        
    }

    public Mecanico() {
    }  

}
