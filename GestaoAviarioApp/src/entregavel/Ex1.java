/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entregavel;

import Controller.ClienteController;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author MANJATE-OSVALDO
 */
public class Ex1 extends JFrame implements ActionListener{
    
    private JTextArea txt;
    private JScrollPane jscrol;
    private JButton enviar, limpar;
    private ArrayList<Cliente> lista;

    public Ex1() {

        txt = new JTextArea();
        jscrol = new JScrollPane(txt);
        jscrol.setBounds(20, 20, 410, 200);

        limpar = new JButton("Limpar");
        limpar.setBounds(200, 260, 80, 30);
        limpar.addActionListener(this);

        enviar = new JButton("Enviar");
        enviar.setBounds(300, 260, 80, 30);
        enviar.addActionListener(this);

        setLayout(null);
        add(jscrol);
        add(limpar);
        add(enviar);
        setTitle("Numero1");
        setSize(500, 350);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Ex1();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == limpar) {
            txt.setText("");
        }

        if (e.getSource() == enviar) {
            verifacarQuery();
        }
    }

    public void verifacarQuery() {
        String query = txt.getText();

        System.out.println(query.substring(0, 6));

        switch (query.substring(0, 6).toLowerCase()) {
            case "select":
                select();
                break;

            case "delete":
                ClienteController.excluir(query);
                break;

            case "insert":
                ClienteController.gravarCliente(query);
                break;

            case "update":
                System.out.println(query);
                ClienteController.actualizar(query);
                break;
        }
    }

    public void select() {
        String query = txt.getText();

        lista = ClienteController.getCliente(query);

        for (int i = 0; i < lista.size(); i++) {
            txt.setText(txt.getText() + "\n" + lista.get(i).toString());
        }
    }


}
