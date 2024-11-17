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
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MANJATE-OSVALDO
 */
public class Ex3 extends JFrame implements ActionListener{

    
    private JTextArea txt;
    private JScrollPane jscrol, jscrol_Tabela;
    private JButton enviar, limpar;
    private ArrayList<Cliente> lista;
    private JTable tabela;
    private DefaultTableModel modelo;
    
    public Ex3(){
        txt = new JTextArea();
        jscrol = new JScrollPane(txt);
        jscrol.setBounds(20, 20, 440, 100);

        limpar = new JButton("Limpar");
        limpar.setBounds(200, 160, 80, 30);
        limpar.addActionListener(this);

        enviar = new JButton("Enviar");
        enviar.setBounds(300, 160, 80, 30);
        enviar.addActionListener(this);
        
        Object colunas[] = {"Matricula", "Nome", "Sexo", "Idade"};
        tabela = new JTable();
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(colunas);
        tabela.setModel(modelo);
        jscrol_Tabela = new JScrollPane(tabela);
        jscrol_Tabela.setBounds(10, 250, 460, 150);

        setLayout(null);
        add(jscrol);
        add(limpar);
        add(enviar);
        add(jscrol_Tabela);
        setTitle("Numero3");
        setSize(500, 450);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
  
    public static void main(String[] args) {
        new Ex3();
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
                loadTable();
                break;

            case "insert":
                ClienteController.gravarCliente(query);
                loadTable();
                break;

            case "update":
                System.out.println(query);
                ClienteController.actualizar(query);
                loadTable();
                break;
        }
    }
    
    public void select(){
         String query = txt.getText();
         lista = ClienteController.getCliente(query);
         
          DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Matricula", "Nome", "Idade", "Sexo"}, 0);

        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(new Object[]{lista.get(i).getMatricula(), lista.get(i).getNome(),
                lista.get(i).getIdade(), lista.get(i).getSexo()});
        }
        tabela.setModel(modelo);
         
         
    }

        public void loadTable() {
        lista = ClienteController.getCliente("Select * from cliente;");

        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Matricula", "Nome", "Idade", "Sexo"}, 0);

        for (int i = 0; i < lista.size(); i++) {
            modelo.addRow(new Object[]{lista.get(i).getMatricula(), lista.get(i).getNome(),
                lista.get(i).getIdade(), lista.get(i).getSexo()});
        }
        tabela.setModel(modelo);
    }

    
}
