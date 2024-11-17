package view;

import componentes.*;
import java.awt.Color;
import java.awt.Cursor;
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
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;
import model.negocio.Carro;
import model.negocio.Cliente;
import model.negocio.Ficha;
import model.negocio.Mecanico;
import modeloTabelas.ModeloFicha;
import service.FichaService;
import service.MecanicoService;

public class DialogoFicha extends JDialog {

    public DialogoFicha() throws RemoteException {
        inicializarObjs();
        this.setSize(660, 750);
        pDadosPessoais();
        preencherTabelaRegisto();
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        eventosT();
    }

    public void preencherTabelaRegisto() throws RemoteException {
//        FichaDAO dao = new FichaDAO();
        fichas = fich_serv.findAll();
        model = new ModeloFicha(fichas);
        tb_Registo.setModel(model);

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < model.getColumnCount(); i++) {
            tb_Registo.getColumnModel().getColumn(i).setCellRenderer(render);
        }
        tb_Registo.getColumnModel().getColumn(0).setPreferredWidth(100);
        tb_Registo.getColumnModel().getColumn(1).setPreferredWidth(300);
    }

    private boolean valido() {
        return !txt_nome.getText().equals("") & !txt_marca.getText().equals("") & !txt_endereco.getText().equals("") & !txt_cor.getText().equals("")
                & !txt_modelo.getText().equals("") & !txt_quuilom.getText().equals("") & !txt_motor.getText().equals("")
                & !txt_matricula.getText().equals("") & !String.valueOf(servicos.getSelectedItem()).equals("Escolha o servico")
                & !String.valueOf(mec.getSelectedItem()).equals("Selecione o mecânico") & (btn_disel.isSelected() || btn_gasolina.isSelected());
    }

    public void cadastrar() {
        try {
            if (valido()) {

//                FichaDAO dao = new FichaDAO();
                String id = UUID.randomUUID().toString();
                String idCliente = UUID.randomUUID().toString();
                Ficha f = new Ficha();
                Cliente cliente = new Cliente(idCliente, txt_nome.getText(), txt_endereco.getText(), txt_cell.getText());
                String tipoCombustivel = btn_grupoCombustivel.getSelection().getActionCommand();
                Carro carro = new Carro(txt_matricula.getText(), txt_marca.getText(), txt_modelo.getText(), txt_cor.getText(), tipoCombustivel, idCliente,
                        Integer.parseInt(txt_motor.getText()), Float.parseFloat(txt_quuilom.getText()));
                Date data = new Date();
                String idMecanico = "";
                for (int i = 0; i < mecanicos.size(); i++) {
                    if (String.valueOf(mec.getSelectedItem()).equals(mecanicos.get(i).getNome())) {
                        idMecanico = mecanicos.get(i).getId();
                    }
                }

                if (fich_serv.insert(id, new java.sql.Timestamp(data.getTime()), null, "Aberto", String.valueOf(servicos.getSelectedItem()), cliente, carro, idMecanico)) {
                    JOptionPane.showMessageDialog(this, "Ficha cadastrada com sucesso");
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao cadastrar ficha", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha correctamente os campos obrigatorios", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException x) {
            JOptionPane.showMessageDialog(this, "Preencha correctamente o campo idade", "Erro", JOptionPane.ERROR_MESSAGE);

        } catch (ClassCastException x) {
            JOptionPane.showMessageDialog(this, x);
        } catch (NullPointerException x) {
            x.printStackTrace();
            JOptionPane.showMessageDialog(this, x);
        } catch (Exception x) {
            JOptionPane.showMessageDialog(this, x);

        }
    }

    public void update() {
          try {
            if (valido()) {
//                FichaDAO dao = new FichaDAO();
                Cliente cliente = fich_serv.getCliente(ficha.getIdCliente());
                Carro carro = fich_serv.getCarro(ficha.getIdCliente());
                
                cliente.setNome(txt_nome.getText());
                cliente.setEndereco(txt_endereco.getText());
                carro.setMarca(txt_marca.getText());
                carro.setModelo(txt_modelo.getText());
                carro.setMatricula(txt_matricula.getText());
                carro.setNrMotor(Integer.parseInt(txt_motor.getText()));
                carro.setCor(txt_cor.getText());
                carro.setQuilometragem(Float.parseFloat(txt_quuilom.getText()));
                ficha.setTipoServico((String.valueOf(servicos.getSelectedItem())));
                ficha.setIdMecanico((String.valueOf(mec.getSelectedItem())));
                    for (int i = 0; i < mecanicos.size(); i++) {
                    if (String.valueOf(mec.getSelectedItem()).equals(mecanicos.get(i).getNome())) {
                        ficha.setIdMecanico(mecanicos.get(i).getId());
                    }
                }

                if (fich_serv.actualizar(ficha.getId(), ficha.getTipoServico(), cliente, carro, ficha.getIdMecanico()) && fich_serv.actualizarCarro(carro) && fich_serv.actualizarCliente(cliente)) {
                    JOptionPane.showMessageDialog(this, "Ficha Editada com sucesso!!"+ cliente.getNome(), "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao actualizar Ficha", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha correctamente os campos obrigatorios", "Erro", JOptionPane.ERROR_MESSAGE);
            } 
        } catch (NumberFormatException x) {
            JOptionPane.showMessageDialog(this, "Preencha correctamente o campo idade", "Erro", JOptionPane.ERROR_MESSAGE);

        } catch (ClassCastException x) {
            JOptionPane.showMessageDialog(this, x);
        } catch (NullPointerException x) {
            JOptionPane.showMessageDialog(this, x);
        } catch (Exception x) {
            JOptionPane.showMessageDialog(this, x);

        } 
    }

    public void limparTela() {
        txt_nome.setText("");
        txt_endereco.setText("");
        txt_marca.setText("");
        txt_modelo.setText("");
        txt_quuilom.setText("");
        txt_motor.setText("");
        txt_cor.setText("");
        txt_cell.setText("");
        txt_matricula.setText("");
        btn_grupoCombustivel.clearSelection();
        mec.setSelectedItem(mecan[0]);
        servicos.setSelectedItem(serv[0]);
        tb_Registo.clearSelection();
        habilitarCampos();
        btn_editar.setEnabled(false);
        btn_remover.setEnabled(false);
        btn_gravar.setEnabled(true);
        modo = "registar";
    }

    public void desabilitarCampos() {
        txt_cell.setEditable(false);
        txt_nome.setEditable(false);
        txt_nome.setTextoFundo("");
        txt_marca.setEditable(false);
        txt_marca.setTextoFundo("");
        txt_modelo.setEditable(false);
        txt_modelo.setTextoFundo("");
        txt_matricula.setEditable(false);
        txt_matricula.setTextoFundo("");
        txt_cor.setEditable(false);
        txt_cor.setTextoFundo("");
        txt_endereco.setEditable(false);
        txt_endereco.setTextoFundo("");
        txt_quuilom.setEditable(false);
        txt_quuilom.setTextoFundo("");
        txt_motor.setEditable(false);
        txt_motor.setTextoFundo("");
        mec.setEditable(false);
        servicos.setEditable(false);
        btn_disel.setEnabled(false);
        btn_gasolina.setEnabled(false);

    }

    public void habilitarCampos() {
        txt_cell.setEditable(true);
        txt_nome.setEditable(true);
        txt_marca.setEditable(true);
        txt_modelo.setEditable(true);
        txt_matricula.setEditable(true);
        txt_cor.setEditable(true);
        txt_endereco.setEditable(true);
        txt_quuilom.setEditable(true);
        txt_motor.setEditable(true);
        mec.setEditable(true);
        servicos.setEditable(true);
        btn_disel.setEnabled(true);
        btn_gasolina.setEnabled(true);
    }

    public void preencher() throws RemoteException {
//        FichaDAO dao = new FichaDAO();
        Cliente cliente = fich_serv.getCliente(ficha.getIdCliente());
        Carro carro = fich_serv.getCarro(ficha.getIdCliente());
        Mecanico mecanico = fich_serv.getMecanico(ficha.getIdMecanico());
        try {
            txt_cell.setText(cliente.getCelular());
            txt_nome.setText(cliente.getNome());
            txt_marca.setText(carro.getMarca());
            txt_modelo.setText(carro.getModelo());
            txt_matricula.setText(carro.getMatricula());
            txt_cor.setText(carro.getCor());
            txt_endereco.setText(cliente.getEndereco());
            txt_quuilom.setText(carro.getQuilometragem() + "");
            txt_motor.setText(carro.getNrMotor() + "");
            String cobustivel = carro.getTipoCombustivel();
            if (cobustivel.equals("Diesel")) {
                btn_disel.setSelected(true);
            } else if (cobustivel.equals("Gasolina")) {
                btn_gasolina.setSelected(true);

            }

            mec.setSelectedItem(mecanico.getNome());
            servicos.setSelectedItem(ficha.getTipoServico());

            desabilitarCampos();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "selecione um item na tabela");
            e.printStackTrace();
        }
    }

    public void eventosT() {

        btn_editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                control = false;
                try {
                    preencher();
                } catch (RemoteException ex) {
                    Logger.getLogger(DialogoFicha.class.getName()).log(Level.SEVERE, null, ex);
                }
                habilitarCampos();
                modo = "editar";
                btn_editar.setEnabled(false);
                btn_remover.setEnabled(false);
                btn_gravar.setEnabled(true);

            }
        });

        btn_remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!false) {
                    try {
                        preencherTabelaRegisto();
                    } catch (RemoteException ex) {
                        Logger.getLogger(DialogoFicha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    limparTela();
                    //JOptionPane.showMessageDialog(null, "ELIMINADO COM SUCESSO");

                }
            }
        });

        btn_limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                control = true;
                limparTela();
            }
        });

        tb_Registo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                int indice = tb_Registo.getSelectedRow();

                if (indice >= 0) {
                    ficha = model.retornarRegisReg(indice);
                    try {
                        preencher();
                    } catch (RemoteException ex) {
                        Logger.getLogger(DialogoFicha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desabilitarCampos();
                    btn_editar.setEnabled(true);
                    btn_remover.setEnabled(true);
                    btn_gravar.setEnabled(false);

                }
            }
        });

        btn_gravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (modo.equals("registar")) {
                    cadastrar();
                    try {
                        preencherTabelaRegisto();
                    } catch (RemoteException ex) {
                        Logger.getLogger(DialogoFicha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (modo.equals("editar")) {
                    update();
                    try {
                        preencherTabelaRegisto();
                    } catch (RemoteException ex) {
                        Logger.getLogger(DialogoFicha.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    limparTela();
                }

            }
        });
    }

    private void pDadosPessoais() throws RemoteException {
        leftP_y = 40;
        leftP_x = 10;

        lb_Titulo = new JLabel("FICHA DE REGISTO");
        lb_Titulo.setOpaque(true);
        lb_Titulo.setBounds(5, 5, 635, 30);
        lb_Titulo.setBackground(new Color(41, 129, 179));
        lb_Titulo.setHorizontalAlignment(SwingConstants.CENTER);
        lb_Titulo.setForeground(new Color(255, 255, 255));
        lb_Titulo.setFont(font_lb_titulos);
        this.add(lb_Titulo);

        pane_dados = new JPanel(null);
        pane_dados.setBackground(new Color(255, 255, 255));
        pane_dados.setBounds(leftP_x, leftP_y, 625, 640);
        pane_dados.setBorder(new LineBorder(cor_font_lbs, 1));
        //pane_dados.add(pane_dados);

        lb_dados = new JLabel("DADOS DO CLIENTE");
        lb_dados.setOpaque(true);
        lb_dados.setBounds(5, 5, pane_dados.getWidth() - 330, 30);
        lb_dados.setBackground(new Color(41, 129, 179));
        lb_dados.setHorizontalAlignment(SwingConstants.CENTER);
        lb_dados.setForeground(new Color(255, 255, 255));
        lb_dados.setFont(font_lb_titulos);
        pane_dados.add(lb_dados);

        lb_nome = new JLabel("Nome do Cliene");
        lb_nome.setFont(font_lbs);
        lb_nome.setForeground(cor_font_lbs);
        lb_nome.setBounds(20, (h = h + 45), 250, 25);
        pane_dados.add(lb_nome);

        txt_nome = new CampoTexto();
        txt_nome.setTextoFundo("Digite o nome completo");
        txt_nome.setFont(font_lbs);
        txt_nome.setForeground(cor_font_txt);
        txt_nome.setBounds(20, (h = h + 30), 270, 25);
        pane_dados.add(txt_nome);

        lb_endereco = new JLabel("Endereco");
        lb_endereco.setFont(font_lbs);
        lb_endereco.setForeground(cor_font_lbs);
        lb_endereco.setBounds(20, (h = h + 25), 270, 25);
        pane_dados.add(lb_endereco);

        txt_endereco = new CampoTexto();
        txt_endereco.setTextoFundo("Digite aqui o Endereco");
        txt_endereco.setFont(font_lbs);
        txt_endereco.setForeground(cor_font_txt);
        txt_endereco.setBounds(20, (h = h + 25), 270, 25);
        pane_dados.add(txt_endereco);

        lb_cell = new JLabel("Celular");
        lb_cell.setFont(font_lbs);
        lb_cell.setForeground(cor_font_lbs);
        lb_cell.setBounds(20, h = h + 25, 220, 25);
        pane_dados.add(lb_cell);

        MaskFormatter cell = null;

        try {
            //Criamos o formato do campo
            cell = new MaskFormatter("+258 ## ### ####");

        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
        txt_cell = new JFormattedTextField(cell);
        txt_cell.setFont(font_lbs);
        txt_cell.setForeground(cor_font_txt);
        txt_cell.setBounds(20, (h = h + 25), 270, 30);
        pane_dados.add(txt_cell);

        lb_mec = new JLabel("Mecanico Responsavel");
        lb_mec.setFont(font_lbs);
        lb_mec.setForeground(cor_font_lbs);
        lb_mec.setBounds(20, h + 27, 270, 25);
        pane_dados.add(lb_mec);

//        MecanicoDAO mecani=new MecanicoDAO();
        mecanicos = mec_serv.findAll();
        mecan = new String[mecanicos.size() + 1];
        mecan[0] = "Selecione o mecânico";
        for (int i = 0; i < mecanicos.size(); i++) {
            mecan[i + 1] = mecanicos.get(i).getNome();
        }

        mec = new JComboBox(mecan);
        mec.setBounds(20, (h = h + 50), 270, 25);
        pane_dados.add(mec);

        leftP_y = 10;
        leftP_x = 10;
        int x = 330;

        lb_dados_carro = new JLabel("DADOS DO CARRO");
        lb_dados_carro.setOpaque(true);
        lb_dados_carro.setBounds(x - 5, 5, pane_dados.getWidth() - 330, 30);
        lb_dados_carro.setBackground(new Color(41, 129, 179));
        lb_dados_carro.setHorizontalAlignment(SwingConstants.CENTER);
        lb_dados_carro.setForeground(new Color(255, 255, 255));
        lb_dados_carro.setFont(font_lb_titulos);
        pane_dados.add(lb_dados_carro);

        h = 45;
        lb_marca = new JLabel("Marca do Carro");
        lb_marca.setFont(font_lbs);
        lb_marca.setForeground(cor_font_lbs);
        lb_marca.setBounds(x + 5, h, 250, 25);
        pane_dados.add(lb_marca);

        txt_marca = new CampoTexto();
        txt_marca.setTextoFundo("Digite a marca do carro");
        txt_marca.setFont(font_lbs);
        txt_marca.setForeground(cor_font_txt);
        txt_marca.setBounds(x + 5, (h = h + 30), 270, 25);
        pane_dados.add(txt_marca);

        lb_modelo = new JLabel("Modelo");
        lb_modelo.setFont(font_lbs);
        lb_modelo.setForeground(cor_font_lbs);
        lb_modelo.setBounds(x + 5, (h = h + 25), 270, 25);
        pane_dados.add(lb_modelo);

        txt_modelo = new CampoTexto();
        txt_modelo.setTextoFundo("Digite aqui o Modelo da Viatura");
        txt_modelo.setFont(font_lbs);
        txt_modelo.setForeground(cor_font_txt);
        txt_modelo.setBounds(x + 5, (h = h + 25), 270, 25);
        pane_dados.add(txt_modelo);

        lb_cor = new JLabel("Cor da viatura");
        lb_cor.setFont(font_lbs);
        lb_cor.setForeground(cor_font_lbs);
        lb_cor.setBounds(x + 5, (h = h + 25), 100, 25);
        pane_dados.add(lb_cor);

        txt_cor = new CampoTexto();
        txt_cor.setTextoFundo("COR");
        txt_cor.setFont(font_lbs);
        txt_cor.setForeground(cor_font_txt);
        txt_cor.setBounds(x + 5, (h = h + 25), 100, 25);
        pane_dados.add(txt_cor);

        lb_matricula = new JLabel("Matricula da viatura");
        lb_matricula.setFont(font_lbs);
        lb_matricula.setForeground(cor_font_lbs);
        lb_matricula.setBounds(x + 145, (h = h - 25), 150, 25);
        pane_dados.add(lb_matricula);

        txt_matricula = new CampoTexto();
        txt_matricula.setTextoFundo("matricula");
        txt_matricula.setFont(font_lbs);
        txt_matricula.setForeground(cor_font_txt);
        txt_matricula.setBounds(x + 145, (h = h + 25), 130, 25);
        pane_dados.add(txt_matricula);

        lb_quilom = new JLabel("Quilometragem");
        lb_quilom.setFont(font_lbs);
        lb_quilom.setForeground(cor_font_lbs);
        lb_quilom.setBounds(x + 5, (h = h + 25), 100, 25);
        pane_dados.add(lb_quilom);

        txt_quuilom = new CampoTexto();
        txt_quuilom.setTextoFundo("##Km");
        txt_quuilom.setFont(font_lbs);
        txt_quuilom.setForeground(cor_font_txt);
        txt_quuilom.setBounds(x + 5, (h = h + 25), 100, 25);
        pane_dados.add(txt_quuilom);

        lb_nr_Motor = new JLabel("Numero do Motor");
        lb_nr_Motor.setFont(font_lbs);
        lb_nr_Motor.setForeground(cor_font_lbs);
        lb_nr_Motor.setBounds(x + 145, (h = h - 25), 150, 25);
        pane_dados.add(lb_nr_Motor);

        txt_motor = new CampoTexto();
        txt_motor.setTextoFundo("MotorNr");
        txt_motor.setFont(font_lbs);
        txt_motor.setForeground(cor_font_txt);
        txt_motor.setBounds(x + 145, (h = h + 25), 130, 25);
        pane_dados.add(txt_motor);

        lb_serv = new JLabel("Tipo de Servico");
        lb_serv.setFont(font_lbs);
        lb_serv.setForeground(cor_font_lbs);
        lb_serv.setBounds(20, (h = h + 25), 100, 25);
        pane_dados.add(lb_serv);

        servicos = new JComboBox(serv);
        servicos.setBounds(20, (h = h + 25), 270, 25);
        pane_dados.add(servicos);

        lb_combustivel = new JLabel("Tipo de Combustivel");
        lb_combustivel.setFont(font_lbs);
        lb_combustivel.setForeground(cor_font_lbs);
        lb_combustivel.setBounds(x + 5, (h = h - 25), 150, 25);
        pane_dados.add(lb_combustivel);

        btn_gasolina = new JRadioButton("Gasolina");
        btn_gasolina.setBackground(new Color(255, 255, 255));
        btn_gasolina.setActionCommand("Gasolina");
        btn_gasolina.setForeground(cor_font_lbs);
        btn_gasolina.setFont(font_lbs);
        btn_gasolina.setBounds(x + 5, (h = h + 25), 100, 25);
        btn_gasolina.setFocusPainted(false);
        btn_gasolina.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pane_dados.add(btn_gasolina);

        btn_disel = new JRadioButton("Diesel");
        btn_disel.setBackground(new Color(255, 255, 255));
        btn_disel.setActionCommand("Diesel");
        btn_disel.setForeground(cor_font_lbs);
        btn_disel.setFont(font_lbs);
        btn_disel.setBounds(x + 145, (h), 100, 25);
        btn_disel.setFocusPainted(false);
        btn_disel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pane_dados.add(btn_disel);

        btn_grupoCombustivel = new ButtonGroup();
        btn_grupoCombustivel.add(btn_gasolina);
        btn_grupoCombustivel.add(btn_disel);

        pane_Butoes = new JPanel(null);
        pane_Butoes.setBackground(new Color(255, 255, 255));
        pane_Butoes.setBounds(40, h = h + 35, 540, 40);
        pane_Butoes.setBorder(new LineBorder(cor_font_lbs, 1));
        pane_dados.add(pane_Butoes);

        btn_limpar = new JButton("Limpar");
        btn_limpar.setBounds(5, 5, (585 / 4) - 25, 30);
        btn_limpar.setBackground(Color.WHITE);
        pane_Butoes.add(btn_limpar);

        btn_editar = new JButton("EDITAR");
        btn_editar.setBounds((585 / 4) - 15, 5, (585 / 4) - 15, 30);
        btn_editar.setBackground(Color.GREEN);
        btn_editar.setEnabled(false);
        pane_Butoes.add(btn_editar);

        btn_remover = new JButton("REMOVER");
        btn_remover.setBounds(2 * ((585 / 4) - 25) + 25, 5, (585 / 4) - 15, 30);
        btn_remover.setBackground(Color.PINK);
        btn_remover.setEnabled(false);
        pane_Butoes.add(btn_remover);

        btn_gravar = new JButton("GRAVAR");
        btn_gravar.setBounds(3 * ((585 / 4) - 25) + 40, 5, (585 / 4) - 15, 30);
        btn_gravar.setBackground(Color.CYAN);
        pane_Butoes.add(btn_gravar);

        tb_Registo = new JTable();
        tb_Registo.setBackground(new Color(255, 255, 255));
        tb_Registo.setFont(new Font("Centuary Gothic", Font.BOLD, 13));
        tb_Registo.setGridColor(new Color(75, 130, 181));
        tb_Registo.setBounds(0, 0, 585, 250);
        tb_Registo.getTableHeader().setFont(new Font("Centuary Gothic", Font.BOLD, 15));
        tb_Registo.getTableHeader().setBackground(new Color(160, 196, 229));
        tb_Registo.getTableHeader().setForeground(new Color(29, 36, 98));
        tb_Registo.setRowHeight(25);

        pane_Table = new JScrollPane();
        pane_Table.setBounds(20, h + 60, 585, 250);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws RemoteException {
        new DialogoFicha();
    }

    private JPanel pane_dados, pane_Butoes, pane_P;

    private JLabel lb_nome, lb_endereco, lb_data, lb_marca,
            lb_dados, lb_cell, lb_dados_carro, lb_modelo, lb_cor,
            lb_matricula, lb_nr_Motor, lb_quilom, lb_serv, lb_mec, lb_Titulo, lb_combustivel;
    private CampoTexto txt_nome, txt_data, txt_cor,
            txt_endereco, txt_marca, txt_modelo, txt_matricula, txt_motor, txt_quuilom;
    private Font font_lbs, font_lb_titulos;
    private Color cor_font_lbs, cor_font_txt;
    private JComboBox servicos, mec;
    private JRadioButton btn_disel, btn_gasolina;
    private List<Ficha> fichas;
    private List<Mecanico> mecanicos;
    private Ficha ficha;
    private JFormattedTextField txt_cell;
    private JScrollPane pane_Table;
    private ModeloFicha model;
    private JTable tb_Registo;
    private boolean control = false;
    private JButton btn_limpar, btn_remover, btn_editar, btn_info, btn_gravar, btn_sair;
    int h, leftP_x, leftP_y;
    private ButtonGroup btn_grupoCombustivel;
    private String[] mecan;
    private String[] serv = {"Escolha o servico", "Lavagem", "Lubrificacao", "Revisao do Motor", "Outro"};
    private String modo = "registar";
    
    static MecanicoService mec_serv;
    static FichaService fich_serv;

}
