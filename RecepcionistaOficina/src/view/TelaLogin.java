package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import componentes.RoundedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;

import javax.swing.JPasswordField;
import model.negocio.Mecanico;
import service.LoginService;

public class TelaLogin extends JFrame {

    JTextField txt_usuario;
    JPasswordField txt_senha;
    JLabel lbl_usuario, lbl_senha, lbl_logo;
    JButton btn_login;
    private LoginService log_serv;
    

    public TelaLogin() {
        inicializarObjs();
        JPanel painelLogin = new JPanel();
        painelLogin.setLayout(null);
        painelLogin.setBounds(250, 25, 350, 200);
        Border thatBorder1 = new LineBorder(Color.BLUE);
        TitledBorder thatBorder2 = new TitledBorder(thatBorder1, "AUTO MECANICA");
        Font font = new Font("Serif", Font.ITALIC, 12);
        TitledBorder thatBorder = new TitledBorder(thatBorder2, "", TitledBorder.LEFT,
                TitledBorder.ABOVE_BOTTOM, font, Color.BLUE);
        painelLogin.setBorder(thatBorder);

        JPanel pane_logo = new JPanel();
        pane_logo.setLayout(null);
        pane_logo.setBackground(new Color(255, 255, 255));
        pane_logo.setBounds(25, 25, 280, 200);

        ImageIcon icon = new ImageIcon(getClass().getResource("Capture.PNG"));
        lbl_logo = new JLabel(icon);
        lbl_logo.setBounds(0, 0, 200, 200);
        pane_logo.add(lbl_logo);

        lbl_usuario = new JLabel("Usuario");
        lbl_usuario.setBounds(25, 25, 300, 25);

        txt_usuario = new JTextField();
        txt_usuario.setBounds(25, 50, 280, 25);

        lbl_senha = new JLabel("Palavra Chave");
        lbl_senha.setBounds(25, 75, 300, 25);

        txt_senha = new JPasswordField();
        txt_senha.setBounds(25, 100, 280, 25);

        btn_login = new JButton("Login");
        btn_login.setBounds(25, 140, 280, 25);
        btn_login.setBorder(new RoundedBorder(10));
        btn_login.setBackground(new Color(63, 145, 254));
        btn_login.setForeground(new Color(255, 255, 254));

        painelLogin.add(lbl_usuario);
        painelLogin.add(txt_usuario);
        painelLogin.add(lbl_senha);
        painelLogin.add(txt_senha);
        painelLogin.add(btn_login);
        painelLogin.setBackground(new Color(255, 255, 255));

        add(painelLogin);
        add(pane_logo);
        getContentPane().setBackground(new Color(255, 255, 255));
        setLayout(null);
        setResizable(false);
        setTitle("Login");
        setSize(630, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        eventosButoes();
    }

    public static void main(String[] args) {
        TelaLogin telaLogin = new TelaLogin();
    }

    public void eventosButoes() {
        TelaLogin tela = this;
        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
             
                if (txt_usuario.getText().equals("Admin") && txt_senha.getText().equals("admin")) {
                    new TelaAdmin();
                    System.out.println("Recepcionista");
                    tela.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void inicializarObjs() {
        try {
            Registry regist = LocateRegistry.getRegistry(Grupo.ip, 6001);
            log_serv = (LoginService) regist.lookup("Login");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
