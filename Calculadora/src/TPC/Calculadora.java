/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Charque Junior
 */


public class Calculadora extends JFrame implements ActionListener {
    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,bpi,braiz;
    JButton bdel,blimpar,bponto,bvez,bmais,bmenos,bdiv,big,bzero;
    JTextField area;
    int cvez=0;
    int cmas=0;
    int cmen=0;
    int cdiv=0;
    Operacao op=new Operacao();
    Container contein= new JPanel();
    //Container teclados=new JPanel();
    Container principal=getContentPane();
    public Calculadora() {
        super("CALCULADORA");
        super.setBackground(new Color(25, 11, 65));
        
        area =new JTextField(100);
        area.setEditable(false);
        area.setAlignmentY(RIGHT_ALIGNMENT);
        area.setFont(new Font("Times New Roman",17,17));
        area.setSize(50, 20);
        contein.setLayout(new GridLayout(5,4));
        principal.add(area,BorderLayout.NORTH);
        principal.add(contein,BorderLayout.CENTER);
        butoes();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350,244);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void butoes(){
        b1=new JButton("1");
        b1.setBackground(new Color(0, 0, 0));
        b1.setForeground(new Color(255, 255, 255));
        b1.addActionListener(this);
        
        b2=new JButton("2");
        b2.setBackground(new Color(0, 0, 0));
        b2.setForeground(new Color(255, 255, 255));
        b2.addActionListener(this);
        
        b3=new JButton("3");
        b3.setBackground(new Color(0, 0, 0));
        b3.setForeground(new Color(255, 255, 255));
        b3.addActionListener(this);
        
        b4=new JButton("4");
        b4.setBackground(new Color(0, 0, 0));
        b4.setForeground(new Color(255, 255, 255));
        b4.addActionListener(this);
        
        b5=new JButton("5");
        b5.setBackground(new Color(0, 0, 0));
        b5.setForeground(new Color(255, 255, 255));
        b5.addActionListener(this);
        
        b6=new JButton("6");
        b6.setBackground(new Color(0, 0, 0));
        b6.setForeground(new Color(255, 255, 255));
        b6.addActionListener(this);
        
        b7=new JButton("7");
        b7.setBackground(new Color(0, 0, 0));
        b7.setForeground(new Color(255, 255, 255));
        b7.addActionListener(this);
        
        b8=new JButton("8");
        b8.setBackground(new Color(0, 0, 0));
        b8.setForeground(new Color(255, 255, 255));
        b8.addActionListener(this);
        
        b9=new JButton("9");
        b9.setBackground(new Color(0, 0, 0));
        b9.setForeground(new Color(255, 255, 255));
        b9.addActionListener(this);
        
        bvez=new JButton("×");
        bvez.setBackground(new Color(0, 0, 0));
        bvez.setForeground(new Color(255, 255, 255));
        bvez.addActionListener(this);
        
        bdel=new JButton("Del");
        bdel.setBackground(new Color(0, 0, 0));
        bdel.setForeground(new Color(255, 255, 255));
        bdel.addActionListener(this);
        
        bzero=new JButton("0");
        bzero.setBackground(new Color(0, 0, 0));
        bzero.setForeground(new Color(255, 255, 255));
        bzero.addActionListener(this);
        
        bmais=new JButton("+");
        bmais.setBackground(new Color(0, 0, 0));
        bmais.setForeground(new Color(255, 255, 255));
        bmais.addActionListener(this);
        
        bponto=new JButton(".");
        bponto.setBackground(new Color(0, 0, 0));
        bponto.setForeground(new Color(255, 255, 255));
        bponto.addActionListener(this);
        
        blimpar=new JButton("C");
        blimpar.setBackground(new Color(155, 0, 0));
        blimpar.setForeground(new Color(255, 255, 255));
        blimpar.addActionListener(this);
        
        bmenos=new JButton("-");
        bmenos.setBackground(new Color(0, 0, 0));
        bmenos.setForeground(new Color(255, 255, 255));
        bmenos.addActionListener(this);
        
        bdiv=new JButton("÷");
        bdiv.setBackground(new Color(0, 0, 0));
        bdiv.setForeground(new Color(255, 255, 255));
        bdiv.addActionListener(this);
        
        bpi=new JButton("π");
        bpi.setBackground(new Color(0, 0, 0));
        bpi.setForeground(new Color(255, 255, 255));
        bpi.addActionListener(this);
        
        big= new JButton("=");
        big.setBackground(new Color(53, 111, 197));
        big.setForeground(new Color(255,255,255));
        big.setSize(100,200);
        big.addActionListener(this);
        
        braiz= new JButton("√");
        braiz.setBackground(new Color(25, 11, 65));
        braiz.setForeground(new Color(255,255,255));
        braiz.setSize(100,200);
        braiz.addActionListener(this);
        
        contein.add(blimpar);
        contein.add(bdel);
        contein.add(braiz);
        contein.add(bdiv);
        contein.add(b7);
        contein.add(b8);
        contein.add(b9);
        contein.add(bmenos);
        contein.add(b4);
        contein.add(b5);
        contein.add(b6);
        contein.add(bmais);
        contein.add(b1);
        contein.add(b2);
        contein.add(b3);
        contein.add(bvez);
        contein.add(bpi);
        contein.add(bzero);
        contein.add(bponto);
        contein.add(big);
        }
   
    
    public static void main(String[] args) {
        new Calculadora();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getSource()==b1){
       area.setText(area.getText()+"1");
       }
       if(ae.getSource()==b2){
       area.setText(area.getText()+"2");
       }
       if(ae.getSource()==b3){
       area.setText(area.getText()+"3");
       }
       if(ae.getSource()==b4){
       area.setText(area.getText()+"4");
       }
       if(ae.getSource()==b5){
       area.setText(area.getText()+"5");
       }
       if(ae.getSource()==b6){
       area.setText(area.getText()+"6");
       }
       if(ae.getSource()==b7){
       area.setText(area.getText()+"7");
       }
       if(ae.getSource()==b8){
       area.setText(area.getText()+"8");
       }
       if(ae.getSource()==b9){
       area.setText(area.getText()+"9");
       }
       if(ae.getSource()==bponto){
       area.setText(area.getText()+".");
       }
       if(ae.getSource()==bdel){
       area.setText(op.delete(area.getText()));
       }
       if(ae.getSource()==braiz){
       area.setText(""+op.raiz(area.getText()));
       }
       if(ae.getSource()==blimpar){
       area.setText("");
           cvez=0;
           cmas=0;
           cmen=0;
           cdiv=0;
       }
       if(ae.getSource()==bzero){
           area.setText(area.getText().trim()+"0");
       }
       if(ae.getSource()==bpi){
           area.setText(area.getText()+"3.14");
       }
       if(ae.getSource()==bvez){
           if(cvez>0){
                area.setText(op.soma(area.getText().trim())+"*");
           }else{
               area.setText(area.getText().trim()+"*");
               cvez++;
           }
       }
       if(ae.getSource()==bdiv){
           if(cdiv>0){
                area.setText(op.soma(area.getText().trim())+"/");
           }else{
               area.setText(area.getText().trim()+"/");
               cdiv++;
           }
       }
       if(ae.getSource()==bmais){
           if(cmas>0){
                area.setText(op.soma(area.getText().trim())+"+");
           }else{
               area.setText(area.getText()+"+");
               cmas++;
           }
        }
       if(ae.getSource()==bmenos){
           if(cmen>1){
                area.setText(op.soma(area.getText())+"-");
           }else{
               area.setText(area.getText().trim()+"-");
               cmen++;
           }
       }
       if(ae.getSource()==bzero){
           area.setText(area.getText().trim()+"");
       }
       if(ae.getSource()==big){
           area.setText(""+op.soma(area.getText().trim()));
           cvez=0;
           cmas=0;
           cmen=0;
           cdiv=0;
       }
       
    }
    
    

}
