package model.negocio;

import java.io.Serializable;

public class Peca implements Serializable{

    String id, nome, idFicha;
    int quantidade;
    float preco;

    public Peca(String id, String nome, int quantidade, float preco, String idFicha) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.idFicha = idFicha;
    }

    public String getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(String idFicha) {
        this.idFicha = idFicha;
    }

    public Peca() {
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

}
