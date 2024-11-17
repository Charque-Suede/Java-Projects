package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import model.negocio.Carro;
import model.negocio.Cliente;
import model.negocio.Ficha;
import model.negocio.Mecanico;
import model.negocio.Peca;
import modeloTabelas.ModeloFecharFicha;
import recibos.*;
import service.FichaService;
import service.MecanicoService;
import service.PecaService;

public class DialogoFecharFicha extends JDialog {

    public DialogoFecharFicha() throws RemoteException {
        inicializarObjs();
        this.setSize(660, 510);
        pDadosPessoais();
        eventosT();
        preencherTabelaRegisto();
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void preencherTabelaRegisto() throws RemoteException {
//        FichaDAO dao = new FichaDAO();
        fichas = fich_serv.findAll();
        model = new ModeloFecharFicha(fichas);
        tb_Registo.setModel(model);

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < model.getColumnCount(); i++) {
            tb_Registo.getColumnModel().getColumn(i).setCellRenderer(render);
        }
        tb_Registo.getColumnModel().getColumn(0).setPreferredWidth(100);
        tb_Registo.getColumnModel().getColumn(1).setPreferredWidth(300);
    }

    public void imprimirRecibo() throws RemoteException {
//        FichaDAO dao = new FichaDAO();
        Cliente cliente = fich_serv.getCliente(ficha.getIdCliente());
        
        Carro carro = fich_serv.getCarro(ficha.getIdCliente());
        
        Mecanico mecanico = fich_serv.getMecanico(ficha.getIdMecanico());
        
        List<Peca> pecas = fich_serv.pecasByFichID(ficha);
        List<CamposRecibo> campos = new ArrayList<>();

        System.out.println("Nome Cliente: " + cliente.getNome());
        System.out.println("Telefone: " + cliente.getCelular());
        System.out.println("Marca do Carro: " + carro.getMarca());
        System.out.println("Modelo do Carro: " + carro.getModelo());
        System.out.println("Mecânico responsável: " + mecanico.getNome());
        System.out.println("Serviço: " + ficha.getTipoServico());
        System.out.println("Data de entrada da viatura: " + ficha.getDataEmissao());
        System.out.println("Data de entrega da viatura: " + ficha.getDataEntrega());

        System.out.println("\nPEÇAS USADAS\n");
        System.out.println("Nome \t\tQuantidade \t\tPreco \t\tValor");
        float valorPagar = 0;
        for (int i = 0; i < pecas.size(); i++) {
            System.out.println(pecas.get(i).getNome() + " \t\t" + pecas.get(i).getQuantidade() + "\t\t" + pecas.get(i).getPreco() + "\t\t" + (pecas.get(i).getQuantidade() * pecas.get(i).getPreco()) + "MT");
            valorPagar += pecas.get(i).getQuantidade() * pecas.get(i).getPreco();
            //String nome, int quant, float preco, float valor
            campos.add(new CamposRecibo(pecas.get(i).getNome(), pecas.get(i).getQuantidade(), pecas.get(i).getPreco(), (pecas.get(i).getQuantidade() * pecas.get(i).getPreco())));
        }

        System.out.println("\nTotal: " + valorPagar + "MT");

        ParametrosRecibo dados = new ParametrosRecibo(cliente.getNome(), cliente.getCelular(), carro.getMarca(), carro.getModelo(), mecanico.getNome(), ficha.getTipoServico(), ficha.getDataEmissao(), ficha.getDataEntrega(), valorPagar, campos);

        try {
            ImprimirRecibo.getInstance().imprimirRecibo(dados);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eventosT() {

        DialogoFecharFicha dialogoview = this;
        btn_imprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    imprimirRecibo();
                } catch (RemoteException ex) {
                    Logger.getLogger(DialogoFecharFicha.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int result = JOptionPane.showConfirmDialog(null, "Deseja finalizar essa ficha??", "Devolução do carro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    try {
//                        FichaDAO dao = new FichaDAO();
                        ficha.setEstado("Finalizado");
                        if (fich_serv.actualizarEstado(ficha)) {
                            preencherTabelaRegisto();
                            btn_finalizar.setEnabled(false);
                            btn_imprimir.setEnabled(false);
                            tb_Registo.clearSelection();
                            JOptionPane.showMessageDialog(null, "Ficha finalizada co sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro ao finalizar a ficha", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (ClassCastException x) {
                        JOptionPane.showMessageDialog(null, x);
                    } catch (NullPointerException x) {
                        JOptionPane.showMessageDialog(null, x);
                    } catch (Exception x) {
                        JOptionPane.showMessageDialog(null, x);

                    }

                }
            }
        });

        tb_Registo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                int indice = tb_Registo.getSelectedRow();
                ficha = model.retornarRegisReg(indice);
                if (ficha.getEstado().equals("Finalizado")) {
                    btn_finalizar.setEnabled(false);
                    btn_imprimir.setEnabled(true);
                } else if (ficha.getEstado().equals("Em espera")) {
                    btn_finalizar.setEnabled(true);
                    btn_imprimir.setEnabled(false);
                } else {
                    btn_finalizar.setEnabled(false);
                    btn_imprimir.setEnabled(false);
                }

            }
        });

    }

    private void pDadosPessoais() {
        leftP_y = 40;
        leftP_x = 10;

        lb_Titulo = new JLabel("FICHAS DE REGISTO");
        lb_Titulo.setOpaque(true);
        lb_Titulo.setBounds(5, 5, 635, 30);
        lb_Titulo.setBackground(new Color(41, 129, 179));
        lb_Titulo.setHorizontalAlignment(SwingConstants.CENTER);
        lb_Titulo.setForeground(new Color(255, 255, 255));
        lb_Titulo.setFont(font_lb_titulos);
        this.add(lb_Titulo);

        pane_dados = new JPanel(null);
        pane_dados.setBackground(new Color(255, 255, 255));
        pane_dados.setBounds(leftP_x, leftP_y, 625, 420);
        pane_dados.setBorder(new LineBorder(cor_font_lbs, 1));

        pane_Butoes = new JPanel(null);
        pane_Butoes.setBackground(new Color(255, 255, 255));
        pane_Butoes.setBounds(20, h = h + 35, 585, 40);
        pane_Butoes.setBorder(new LineBorder(cor_font_lbs, 1));
        pane_dados.add(pane_Butoes);

        btn_imprimir = new JButton("IMPRIMIR RECIBO");
        btn_imprimir.setBounds(5, 5, 280, 30);
        btn_imprimir.setBackground(Color.WHITE);
        btn_imprimir.setEnabled(false);
        pane_Butoes.add(btn_imprimir);

        btn_finalizar = new JButton("FINALIZAR A ENTREGA DA VIATURA");
        btn_finalizar.setBounds(300, 5, 280, 30);
        btn_finalizar.setBackground(Color.PINK);
        btn_finalizar.setEnabled(false);
        pane_Butoes.add(btn_finalizar);

        tb_Registo = new JTable();
        tb_Registo.setBackground(new Color(255, 255, 255));
        tb_Registo.setFont(new Font("Centuary Gothic", Font.BOLD, 13));
        tb_Registo.setGridColor(new Color(75, 130, 181));
        tb_Registo.setBounds(0, 0, 585, 300);
        tb_Registo.getTableHeader().setFont(new Font("Centuary Gothic", Font.BOLD, 15));
        tb_Registo.getTableHeader().setBackground(new Color(160, 196, 229));
        tb_Registo.getTableHeader().setForeground(new Color(29, 36, 98));
        tb_Registo.setRowHeight(25);

        pane_Table = new JScrollPane();
        pane_Table.setBounds(20, h + 60, 585, 300);
        pane_Table.setBackground(Color.DARK_GRAY);
        pane_Table.setViewportView(tb_Registo);
        pane_dados.add(pane_Table);

        pane_P = new JPanel(null);
        pane_P.setSize(700, 750);
        pane_P.add(pane_dados);
        this.add(pane_P);
    }

    public void inicializarObjs() {
        try {
            Registry regist = LocateRegistry.getRegistry(Grupo.ip, 6001);
            mec_serv = (MecanicoService) regist.lookup("Mecanico");
            fich_serv = (FichaService) regist.lookup("Ficha");
            pec_serv=(PecaService) regist.lookup("Pecas");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {
        new DialogoFecharFicha();
    }

    private JPanel pane_dados, pane_Butoes, pane_P;

    private JLabel lb_Titulo;

    private Font font_lb_titulos;
    private Color cor_font_lbs;
    private List<Ficha> fichas;
    private Ficha ficha;
    ;
    private JScrollPane pane_Table;
    private ModeloFecharFicha model;
    private JTable tb_Registo;
    private JButton btn_imprimir, btn_finalizar;
    int h, leftP_x, leftP_y;

    static MecanicoService mec_serv;
    static FichaService fich_serv;
    static PecaService pec_serv;
}
