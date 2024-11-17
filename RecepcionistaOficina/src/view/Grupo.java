package view;


import componentes.Botao;
import componentes.CampoTexto;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class Grupo extends JFrame{
    public static String ip="172.16.12.237";
    public Grupo() {
        
        Inicializacao();
         eventosButoes();
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(null);
        setResizable(false);
        setTitle("APRESENTACAO DO TRABALHO");
        setSize(400, 220);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }
    
    
    public void Inicializacao(){
        
        lb_Titulo = new JLabel("TRABALHO IV");
        lb_Titulo.setOpaque(true);
        lb_Titulo.setBounds(5, 5, 385, 30);
        lb_Titulo.setBackground(new Color(41, 129, 179));
        lb_Titulo.setHorizontalAlignment(SwingConstants.CENTER);
        lb_Titulo.setForeground(new Color(255, 255, 255));
        lb_Titulo.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(lb_Titulo);
        
        pane_dados = new JPanel(null);
        pane_dados.setBackground(new Color(255, 255, 255));
        pane_dados.setBounds(5, 35, 385, 140);
        pane_dados.setBorder(new LineBorder(new Color(46, 144, 232), 1));
        
        n1=new JCheckBox("CHARQUE SUEDE JR");
        n1.setSelected(true);
        n1.setEnabled(false);
        n1.setBounds(10, 10, 365, 70);
        pane_dados.add(n1);
              
        
        b1=new Botao("IR PARA TELA DE ADMIN");
        b1.setBounds(10, 85, 365, 50);
        pane_dados.add(b1);
        this.add(pane_dados);
      }
    
    public void eventosButoes() {
        Grupo tela = this;
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               dispose();
                new TelaAdmin();
               
            }
        });
    }
 
    
    public static void main(String[] args) {
        new Grupo();
    }
 
    private JLabel lb_Titulo;
    private JPanel pane_dados;
    private JCheckBox n1,n2,n3,n4;
    private Botao b1,b2;
    
    
}
