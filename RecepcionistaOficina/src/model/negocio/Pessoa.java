package model.negocio;

import java.io.Serializable;

public class Pessoa implements Serializable{

    protected String nome, id, nacionalidade, nrBI, estadoCivil, celular, usuario, senha, sexo;
    protected int idade;

    public Pessoa(String id, String nome, int idade, String nacionalidade, String nrBI, String sexo, String estadoCivil, String celular, String usuario, String senha) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.nacionalidade = nacionalidade;
        this.nrBI = nrBI;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.celular = celular;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Pessoa() {
    }

    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getNrBI() {
        return nrBI;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getCelular() {
        return celular;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setNrBI(String nrBI) {
        this.nrBI = nrBI;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    

}
