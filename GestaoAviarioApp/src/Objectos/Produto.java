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
public class Produto implements Serializable{
    private String nome,id;
     private int quantidade;
     private double precoUnitario;

    public Produto(String nome, String id,
            int quantidade, double precoUnitario) {
        this.nome = nome;
        this.id = id;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return "Nome " + nome.toUpperCase()
               +"\nId " + id.toUpperCase() +
                "\nQuantidade: " + quantidade +" Unidades"+
                "\nprecoUnitario: " + precoUnitario + "\n";
    }
    
    
}
