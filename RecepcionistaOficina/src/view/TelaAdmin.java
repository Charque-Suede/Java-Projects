package view;

import componentes.Botao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.negocio.Ficha;
import service.FichaService;
import service.MecanicoService;

public class TelaAdmin extends JFrame {

    public TelaAdmin() {
        inicializarObjs();
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                janela.setVisible(true);
            }
        });
         
        
        inicializacao();
        layouts();
        
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Atualizar o texto do JTextField a cada 2 segundos
                    fichas = fich_serv.findAll();
                    lb_total_Fichas.setText(fichas.size()+"");
                    
                    int cont = 0;
                    for (int i = 0; i < fichas.size(); i++) {
                        if (fichas.get(i).getEstado().equals("Finalizado")) {
                            cont++;
                        }
                    }
                    lb_total_fichas_Fechadas.setText(cont+"");
                    lb_total_mecanicos.setText(mec_serv.findAll().size()+"");
                } catch (RemoteException ex) {
                    Logger.getLogger(TelaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        timer.start();
    }

    private void inicializacao() {

        font = new Font("Century Gothic", Font.BOLD, 30);
        font_menu = new Font("Century", Font.BOLD, 20);

        lb_titulo = new JLabel("AUTO MECANICA");
        lb_titulo.setForeground(new Color(255, 255, 255));
        lb_titulo.setOpaque(false);
        lb_titulo.setSize(400, 40);
        lb_titulo.setFont(font);
        lb_titulo.setHorizontalAlignment(SwingConstants.CENTER);

        pane_titulo = new JPanel();
        pane_titulo.setLayout(null);
        pane_titulo.setBackground(new Color(40, 126, 174));
        pane_titulo.add(lb_titulo);

        pane_logo = new JPanel();
        pane_logo.setLayout(null);
        pane_logo.setBackground(new Color(255, 255, 255));
        pane_logo.setBounds(10, 60, 280, 200);

        ImageIcon icon = new ImageIcon(getClass().getResource("Capture.PNG"));

        lbl_logo = new JLabel(icon);
        lbl_logo.setBounds(40, 0, 200, 200);
        pane_logo.add(lbl_logo);

        pane_menu_lateral = new JPanel();
        pane_menu_lateral.setLayout(null);
        pane_menu_lateral.setBackground(new Color(255, 255, 255));

        pane_conteudo = new JPanel();
        pane_conteudo.setLayout(null);
        pane_conteudo.setBackground(new Color(255, 255, 255));

        btn_cadastrarMecanico = new Botao("Mecânicos");
        btn_cadastrarMecanico.setBounds(10, 210, 280, 40);
        btn_cadastrarMecanico.setFont(font_menu);
        btn_cadastrarMecanico.setForeground(new Color(40, 126, 174));
        btn_cadastrarMecanico.setBackground(new Color(255, 255, 255));
        btn_cadastrarMecanico.setBackgroundMouseEntered(new Color(160, 196, 229));
        pane_menu_lateral.add(btn_cadastrarMecanico);

        btn_AbrirFicha = new Botao("Abrir Ficha");
        btn_AbrirFicha.setBounds(10, 260, 280, 40);
        btn_AbrirFicha.setFont(font_menu);
        btn_AbrirFicha.setForeground(new Color(40, 126, 174));
        btn_AbrirFicha.setBackground(new Color(255, 255, 255));
        btn_AbrirFicha.setBackgroundMouseEntered(new Color(160, 196, 229));
        pane_menu_lateral.add(btn_AbrirFicha);

        btn_Fechar_Ficha = new Botao("Fechar Ficha");
        btn_Fechar_Ficha.setBounds(10, 310, 280, 40);
        btn_Fechar_Ficha.setFont(font_menu);
        btn_Fechar_Ficha.setForeground(new Color(40, 126, 174));
        btn_Fechar_Ficha.setBackground(new Color(255, 255, 255));
        btn_Fechar_Ficha.setBackgroundMouseEntered(new Color(160, 196, 229));
        pane_menu_lateral.add(btn_Fechar_Ficha);

        pane_principal = new JPanel();
        pane_principal.setLayout(null);
        pane_principal.setBackground(new Color(255, 255, 255));
        pane_principal.add(pane_logo);
        pane_principal.add(pane_titulo);
        pane_principal.add(pane_menu_lateral);
        pane_principal.add(pane_conteudo);

        //Inicio da codificacao do dashboard
        font_lb_titulo = new Font("Centruy Schoolbock", Font.BOLD, 25);
        font_total = new Font("Ebrima", Font.BOLD, 30);
        cor_font = new Color(255, 255, 255);

        pane_mecanico = new JPanel(null);
        pane_mecanico.setBackground(new Color(220, 92, 0));
        pane_conteudo.add(pane_mecanico);

        lb_tit_mecanico = new JLabel("MECÂNICOS");
        lb_tit_mecanico.setBackground(new Color(240, 130, 26));
        lb_tit_mecanico.setFont(font_lb_titulo);
        lb_tit_mecanico.setForeground(cor_font);
        lb_tit_mecanico.setOpaque(true);
        lb_tit_mecanico.setHorizontalAlignment(SwingConstants.CENTER);
        pane_mecanico.add(lb_tit_mecanico);

        lb_total_mecanicos = new JLabel(TelaAdmin.quantMecanicos + "");
        lb_total_mecanicos.setForeground(cor_font);
        lb_total_mecanicos.setFont(font_total);
        lb_total_mecanicos.setHorizontalAlignment(SwingConstants.CENTER);
        pane_mecanico.add(lb_total_mecanicos);

        lb_desc_mecanico = new JLabel("TOTAL");
        lb_desc_mecanico.setFont(font_total);
        lb_desc_mecanico.setForeground(cor_font);
        lb_desc_mecanico.setHorizontalAlignment(SwingConstants.CENTER);
        pane_mecanico.add(lb_desc_mecanico);

        pane_F = new JPanel(null);
        pane_F.setBackground(new Color(63, 107, 181));
        pane_conteudo.add(pane_F);

        pane_Fichas = new JPanel(null);
        pane_Fichas.setBackground(new Color(0, 134, 116));
        pane_conteudo.add(pane_Fichas);

        lb_tit_Fichas = new JLabel("FICHAS");
        lb_tit_Fichas.setBackground(new Color(31, 169, 158));
        lb_tit_Fichas.setFont(font_lb_titulo);
        lb_tit_Fichas.setForeground(cor_font);
        lb_tit_Fichas.setOpaque(true);
        lb_tit_Fichas.setHorizontalAlignment(SwingConstants.CENTER);
        pane_Fichas.add(lb_tit_Fichas);

        lb_total_Fichas = new JLabel(quantFichas_total + "");
        lb_total_Fichas.setForeground(cor_font);
        lb_total_Fichas.setFont(font_total);
        lb_total_Fichas.setHorizontalAlignment(SwingConstants.CENTER);
        pane_Fichas.add(lb_total_Fichas);

        lb_desc_funcionario = new JLabel("TOTAL");
        lb_desc_funcionario.setFont(font_total);
        lb_desc_funcionario.setForeground(cor_font);
        lb_desc_funcionario.setHorizontalAlignment(SwingConstants.CENTER);
        pane_Fichas.add(lb_desc_funcionario);

        lb_tit_orfao = new JLabel("FICHAS FECHADAS");
        lb_tit_orfao.setBackground(new Color(57, 129, 222));
        lb_tit_orfao.setFont(font_lb_titulo);
        lb_tit_orfao.setForeground(cor_font);
        lb_tit_orfao.setOpaque(true);
        lb_tit_orfao.setHorizontalAlignment(SwingConstants.CENTER);
        pane_F.add(lb_tit_orfao);

        lb_total_fichas_Fechadas = new JLabel(TelaAdmin.quantFichas_fechadas + "");
        lb_total_fichas_Fechadas.setForeground(cor_font);
        lb_total_fichas_Fechadas.setFont(font_total);
        lb_total_fichas_Fechadas.setHorizontalAlignment(SwingConstants.CENTER);
        pane_F.add(lb_total_fichas_Fechadas);

        lb_desc_Fich = new JLabel("TOTAL");
        lb_desc_Fich.setFont(font_total);
        lb_desc_Fich.setForeground(cor_font);
        lb_desc_Fich.setHorizontalAlignment(SwingConstants.CENTER);
        pane_F.add(lb_desc_Fich);

        //Fim da codificacao do dashboard
        janela = new JFrame();
        janela.setSize(1200, 500);
        janela.getContentPane().add(pane_principal);
        janela.setMinimumSize(new Dimension(700, 500));
        this.janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.janela.setLocationRelativeTo(null);

        pack();
        eventosJanela();
        eventosButoes();
       // this.janela.setVisible(true);

    }


    private void layouts() {

        pane_titulo.setSize(this.pane_principal.getWidth() - 5, 50);
        pane_titulo.setLocation((janela.getWidth() - pane_titulo.getWidth()) / 2 - 8, 5);

        lb_titulo.setLocation((pane_titulo.getWidth() - lb_titulo.getWidth()) / 2, 5);

        pane_menu_lateral.setSize(300, pane_principal.getHeight() - 65);
        pane_menu_lateral.setLocation(2, pane_titulo.getHeight() + 10);

        int h = pane_menu_lateral.getHeight() - 5;
        int w = janela.getWidth() - pane_menu_lateral.getWidth() - 15;
        pane_conteudo.setBounds(310, pane_titulo.getHeight() + 10, w, h);

        int x = (pane_conteudo.getWidth() / 3) - 20;
        int y = (2 * pane_conteudo.getHeight() / 3);

        pane_F.setBounds(10, 20, x, y);
        lb_tit_orfao.setBounds(0, 0, pane_F.getWidth(), 40);

        pane_Fichas.setBounds(pane_F.getX() + x + 10, 20, x, y);
        lb_tit_Fichas.setBounds(0, 0, pane_Fichas.getWidth(), 40);

        pane_mecanico.setBounds(pane_Fichas.getX() + x + 10, 20, x, y);
        lb_tit_mecanico.setBounds(0, 0, pane_mecanico.getWidth(), 40);

        y = pane_Fichas.getHeight() - lb_tit_Fichas.getHeight() + lb_tit_Fichas.getY() - 160;

        lb_total_Fichas.setBounds(0, y, pane_Fichas.getWidth(), 50);
        lb_desc_funcionario.setBounds(0, lb_total_Fichas.getHeight() + lb_total_Fichas.getY() + 80, pane_Fichas.getWidth(), 30);

        lb_total_fichas_Fechadas.setBounds(0, y, pane_F.getWidth(), 50);
        lb_desc_Fich.setBounds(0, lb_total_fichas_Fechadas.getHeight() + lb_total_fichas_Fechadas.getY() + 80, pane_F.getWidth(), 30);

        lb_total_mecanicos.setBounds(0, y, pane_mecanico.getWidth(), 50);
        lb_desc_mecanico.setBounds(0, lb_total_mecanicos.getHeight() + lb_total_mecanicos.getY() + 80, pane_mecanico.getWidth(), 30);

    }

    private void eventosJanela() {

        janela.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                layouts();
            }

        });

        
    }

    private void eventosButoes() {
        btn_Fechar_Ficha.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    new DialogoFecharFicha();
                } catch (RemoteException ex) {
                    Logger.getLogger(TelaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_cadastrarMecanico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new DialogoCadastroMecanico();
                } catch (RemoteException ex) {
                    Logger.getLogger(TelaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        btn_AbrirFicha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new DialogoFicha();
                } catch (RemoteException ex) {
                    Logger.getLogger(TelaAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        JFrame frame = this;

    }

    public void inicializarObjs() {
        try {
            Registry regist = LocateRegistry.getRegistry(Grupo.ip, 6001);
            mec_serv = (MecanicoService) regist.lookup("Mecanico");
            fich_serv = (FichaService) regist.lookup("Ficha");
            quantMecanicos = mec_serv.findAll().size();
            fichas = fich_serv.findAll();

            int cont = 0;
            for (int i = 0; i < fichas.size(); i++) {
                if (fichas.get(i).getEstado().equals("Finalizado")) {
                    cont++;
                }
            }
            quantFichas_fechadas = cont;
            quantFichas_total = fichas.size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JLabel lb_titulo, lb_tit_mecanico, lb_tit_orfao, lb_tit_Fichas, lb_total_mecanicos, lb_desc_mecanico, lb_total_fichas_Fechadas,
            lb_desc_Fich, lb_total_Fichas, lb_desc_funcionario, lbl_logo;
    private JPanel pane_principal, pane_conteudo, pane_titulo, pane_menu_lateral, pane_mecanico, pane_F, pane_Fichas, pane_logo;
    private JFrame janela;
    private Font font, font_menu, font_lb_titulo, font_total;
    private Botao btn_AbrirFicha, btn_Fechar_Ficha, btn_cadastrarMecanico, btn_receberVisitas, btn_adotarOrfao, btn_relatorio;
    static int quantMecanicos = 0;
    static int quantFichas_total = 0;
    static int quantFichas_fechadas = 0;
    private static List<Ficha> fichas;
    private Color cor_font;

    private MecanicoService mec_serv;
    private FichaService fich_serv;
    
    
    public static void main(String[] args) {
        new TelaAdmin();
    }
}
