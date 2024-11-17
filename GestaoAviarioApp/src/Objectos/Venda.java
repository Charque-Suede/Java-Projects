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
public class Venda implements Serializable{
    private String recibo;

    public Venda(String recibo) {
        this.recibo=recibo;
    }

    
    public String getRecibo() {
        return recibo;
    }

     @Override
    public String toString() {
        return  recibo;
    }
    
    
    
}
