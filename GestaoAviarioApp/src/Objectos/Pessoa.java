
package Objectos;

import java.io.Serializable;

public class Pessoa implements Serializable{
    private String nome,apelido,endereco,email,codigo,celular;

    public Pessoa(String nome, String apelido, String endereco, 
            String email, String codigo, String celular) {
        this.nome = nome;
        this.apelido = apelido;
        this.endereco = endereco;
        this.email = email;
        this.codigo = codigo;
        this.celular = celular;
    }

    public Pessoa() {
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return  "\nNome: "+getNome()+" "+getApelido()+""
                + "\nEndereco: "+getEndereco()+""
                + "\nEmail: "+getEmail()+""
                + "\nCecular: "+getCelular()+""
                + "\nCodigo: "+getCodigo()+"\n";
    }
    
    
    
    
}
