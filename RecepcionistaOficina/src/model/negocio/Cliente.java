
package model.negocio;

import java.io.Serializable;


public class Cliente implements Serializable{
    String id, nome, endereco, celular;

    public Cliente(String id, String nome, String endereco, String celular) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.celular = celular;
    }

    public Cliente() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
