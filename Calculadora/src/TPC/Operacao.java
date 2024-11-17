/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPC;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author osval
 */
public class Operacao {
      public double soma(String num){
        int posicao=0;
        double retorno=0;
        String op="";
        try{
         for (int i = 0; i < num.length(); i++) {
            if(num.substring(i, i+1).equals("+")||
               num.substring(i, i+1).equals("-")||
               num.substring(i, i+1).equals("*")||
               num.substring(i, i+1).equals("/")){
                posicao = i;
                op=num.substring(i,i+1);
            }  
        }
        
        double num1 = Double.parseDouble(num.substring(0, posicao));
        double num2 = Double.parseDouble(num.substring(posicao+1, num.length()));
            
            
                if(op.equals("+"))
                    retorno=num1+num2;
                if(op.equals("-"))
                    retorno=num1-num2;
                if(op.equals("*"))
                    retorno=num1*num2;
                if(op.equals("/"))
                    retorno=num1/num2;
            
        }
        catch(NumberFormatException v){
            JOptionPane.showMessageDialog(null,"ERRO SE SINTAXE");
        }
        return retorno;
    }
    
    
    public String delete(String str){
        String aux="";
    
        for (int i = 0; i < str.length()-1; i++) {
        aux+=str.substring(i, i+1);
        
        }
        return aux;
    }
    
    public String raiz(String r){
        DecimalFormat num=new DecimalFormat("##,###.00");
        double raiz=0;
        try{
        raiz=Math.sqrt(Double.parseDouble(r));
    }catch(NumberFormatException v){
    JOptionPane.showMessageDialog(null,"ERRO DE SINTAXE");
}
    return num.format(raiz);}

    
}
