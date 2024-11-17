package view;

import componentes.Botao;
import componentes.CampoTexto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;
import model.negocio.Mecanico;
import modeloTabelas.TabelaMecanico;
import service.FichaService;
import service.MecanicoService;


public class DialogoCadastroMecanico extends JDialog {

    public DialogoCadastroMecanico() throws RemoteException {
        inicializarObjs();
        inicializacao();
        desabilitarCampos();
        preencherTabelaMecanico();
        eventosT();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private boolean valido() {

        return !txt_nome.getText().equals("") & !txt_idade.getText().equals("") & (btn_cas.isSelected() || btn_div.isSelected() || btn_sol.isSelected())
                & !txt_num_bi.getText().equals("") & !txt_nacionalidade.getText().equals("") & (btn_fem.isSelected() || btn_masc.isSelected())
                & !txt_cell.getText().trim().equals("+258");
    }

    public void limparTela() {
        txt_nome.setText("");
        txt_nacionalidade.setText("");
        txt_idade.setText("");
        txt_num_bi.setText("");
        txt_descricao.setText("");
        btn_grupoGenero.clearSelection();
        btn_grupoEstadoCivil.clearSelection();
        txt_cell.setText("");
    }

    public void desabilitarCampos() {
        txt_cell.setEnabled(false);
        txt_idade.setEnabled(false);
        txt_idade.setTextoFundo("");
        txt_nacionalidade.setEnabled(false);
        txt_nacionalidade.setTextoFundo("");
        txt_nome.setEnabled(false);
        txt_nome.setTextoFundo("");
        txt_num_bi.setEnabled(false);
        txt_num_bi.setTextoFundo("");
        txt_descricao.setEnabled(false);
        txt_descricao.setTextoFundo("");
        btn_cas.setEnabled(false);
        btn_div.setEnabled(false);
        btn_sol.setEnabled(false);
        btn_masc.setEnabled(false);
        btn_fem.setEnabled(false);
    }

    public void habilitarCampos() {
        txt_cell.setEnabled(true);
        txt_idade.setEnabled(true);
        txt_idade.setTextoFundo("");
        txt_nacionalidade.setEnabled(true);
        txt_nacionalidade.setTextoFundo("");
        txt_nome.setEnabled(true);
        txt_nome.setTextoFundo("");
        txt_num_bi.setEnabled(true);
        txt_num_bi.setTextoFundo("");
        txt_descricao.setEnabled(true);
        txt_descricao.setTextoFundo("");
        btn_cas.setEnabled(true);
        btn_div.setEnabled(true);
        btn_sol.setEnabled(true);
        btn_masc.setEnabled(true);
        btn_fem.setEnabled(true);
    }

    public void preencher(Mecanico mecnc) {
        try {
            txt_cell.setText(mecnc.getCelular());
            txt_idade.setText(mecnc.getIdade() + "");
            txt_nacionalidade.setText(mecnc.getNacionalidade());
            txt_nome.setText(mecnc.getNome());
            txt_num_bi.setText(mecnc.getNrBI());
            txt_descricao.setText(mecnc.getNacionalidade());

            if (mecnc.getSexo().equalsIgnoreCase("Masculino")) {
                btn_masc.setSelected(true);
            } else {
                btn_fem.setSelected(true);
            }

            if (mecnc.getEstadoCivil().equalsIgnoreCase("Solteiro")) {
                btn_sol.setSelected(true);
            } else if (mecnc.getEstadoCivil().equalsIgnoreCase("Casado")) {
                btn_cas.setSelected(true);
            } else {
                btn_div.setSelected(true);
            }

            desabilitarCampos();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "selecione um item na tabela");
        }
    }

    public void eventosT() {

        DialogoCadastroMecanico dialogoCadastroFuncionario = this;
        btn_sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialogoCadastroFuncionario.dispose();
            }
        });

        txt_pesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                try {
                    preencherTabelaAdotantePesq();
                } catch (RemoteException ex) {
                    Logger.getLogger(DialogoCadastroMecanico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        btn_editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                control = false;
                preencher(mecanico);
                habilitarCampos();

            }
        });

        btn_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                preencher(mecanico);
            }
        });

        btn_remover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (mec_serv.apagar(mecanico)) {
                        try {
                            preencherTabelaMecanico();
                        } catch (RemoteException ex) {
                            Logger.getLogger(DialogoCadastroMecanico.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        limparTela();
                        JOptionPane.showMessageDialog(null, "ELIMINADO COM SUCESSO");
                        //TelaAdmin.decrementarFuncionarios();
                        //TelaFuncionario.decrementarFuncionarios();
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(DialogoCadastroMecanico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        btn_novo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                control = true;
                limparTela();
                habilitarCampos();
            }
        });

        tb_mecanico.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                int indice = tb_mecanico.getSelectedRow();

                if (indice >= 0) {
                    mecanico = model.retornarAdotante(indice);
                    btn_info.setEnabled(true);
                } else {
                    btn_info.setEnabled(false);
                }
            }
        });

        btn_cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (control) {
                    cadastrar();
                    try {
                        preencherTabelaMecanico();
                    } catch (RemoteException ex) {
                        Logger.getLogger(DialogoCadastroMecanico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (!control) {
                    update();
                    try {
                        preencherTabelaMecanico();
                    } catch (RemoteException ex) {
                        Logger.getLogger(DialogoCadastroMecanico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desabilitarCampos();
                }

            }
        });
    }

    public void update() {
        try {
            if (valido()) {
                String nome = txt_nome.getText();
                int idade = Integer.parseInt(txt_idade.getText());
                String nacionalidade = txt_descricao.getText();
                String nrBI = txt_num_bi.getText();
                String sexo = btn_grupoGenero.getSelection().getActionCommand();
                String estadoCivil = btn_grupoEstadoCivil.getSelection().getActionCommand();
                String celular = txt_cell.getText().trim();
                Mecanico mecUpdate=new Mecanico(mecanico.getId(), nome, idade, nacionalidade, nrBI, sexo, estadoCivil, celular, mecanico.getUsuario(), mecanico.getSenha());
                if (mec_serv.actualizar(mecUpdate)) {
                    JOptionPane.showMessageDialog(this, "Dados actualizados com sucesso", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao actualizar Mecanico", "Erro", JOptionPane.ERROR_MESSAGE);
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

    public void cadastrar() {
        try {
            if (valido()) {
                String id = UUID.randomUUID().toString();
                String nome = txt_nome.getText();
                int idade = Integer.parseInt(txt_idade.getText());
                String nacionalidade = txt_descricao.getText();
                String nrBI = txt_num_bi.getText();
                String sexo = btn_grupoGenero.getSelection().getActionCommand();
                String estadoCivil = btn_grupoEstadoCivil.getSelection().getActionCommand();
                String celular = txt_cell.getText().trim();
                String senha = "12345";
                String usuario = txt_nome.getText().replace(' ', '.').toLowerCase() + "@oficina.co.mz";
                Mecanico mec=new Mecanico(id, nome, idade, nacionalidade, nrBI, sexo, estadoCivil, celular, usuario, senha);
                if (mec_serv.insert(mec)) {
                    JOptionPane.showMessageDialog(this, "Mecanico registado com sucesso\n\nUsuario: " + usuario + "\nSenha: " + senha, "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    limparTela();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao registar mecanico", "Erro", JOptionPane.ERROR_MESSAGE);
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

    public void componentesDao() {
        paneP = new JPanel(null);
        paneP.setBounds(600, 45, 685, 370);
        paneP.setBorder(new LineBorder(new Color(41, 129, 179), 1));
        Font font = new Font("Times new Roman ", Font.ITALIC, 9);

        paneAbigaBu = new JPanel(null);
        paneAbigaBu.setBounds(5, 5, 675, 50);
        paneAbigaBu.setBackground(Color.LIGHT_GRAY);

        paneButoes = new JPanel(null);
        paneButoes.setBounds(5, 5, 665, 40);
        paneButoes.setBackground(new Color(247, 218, 185));
        paneAbigaBu.add(paneButoes);

        btn_novo = new JButton("NOVO");
        btn_novo.setBounds(5, 5, 100, 30);
        btn_novo.setFont(font);
        btn_novo.setBackground(Color.WHITE);
        paneButoes.add(btn_novo);

        btn_editar = new JButton("EDITAR");
        btn_editar.setBounds(110, 5, 100, 30);
        btn_editar.setBackground(Color.GREEN);
        btn_editar.setFont(font);
        paneButoes.add(btn_editar);

        btn_info = new JButton("INFORMACAO");
        btn_info.setBounds(215, 5, 100, 30);
        btn_info.setBackground(Color.CYAN);
        btn_info.setFont(font);
        paneButoes.add(btn_info);

        btn_remover = new JButton("REMOVER");
        btn_remover.setBounds(320, 5, 100, 30);
        btn_remover.setBackground(Color.RED);
        //btn_remover.setFont(font);
        paneButoes.add(btn_remover);

        txt_pesquisa = new CampoTexto();
        txt_pesquisa.setTextoFundo("CLIQUE AQUI PARA PESQUISAR");
        txt_pesquisa.setBounds(425, 5, 235, 30);
        paneButoes.add(txt_pesquisa);

        paneP.add(paneAbigaBu);

        pane_Table = new JScrollPane();
        pane_Table.setBounds(5, 60, 675, 300);
        pane_Table.setBackground(Color.DARK_GRAY);
        paneP.add(pane_Table);

        tb_mecanico = new JTable();
        tb_mecanico.setBackground(new Color(255, 255, 255));
        tb_mecanico.setFont(new Font("Centuary Gothic", Font.BOLD, 13));
        tb_mecanico.setGridColor(new Color(75, 130, 181));
        tb_mecanico.setBounds(0, 0, 675, 300);
        tb_mecanico.getTableHeader().setFont(new Font("Centuary Gothic", Font.BOLD, 15));
        tb_mecanico.getTableHeader().setBackground(new Color(160, 196, 229));
        tb_mecanico.getTableHeader().setForeground(new Color(29, 36, 98));
        tb_mecanico.setRowHeight(25);
        pane_Table.setViewportView(tb_mecanico);
        this.add(paneP);

    }

    public void preencherTabelaMecanico() throws RemoteException {
        listaMecanicos = mec_serv.findAll();
        model = new TabelaMecanico(listaMecanicos);
        tb_mecanico.setModel(model);

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < model.getColumnCount(); i++) {
            tb_mecanico.getColumnModel().getColumn(i).setCellRenderer(render);
        }
        tb_mecanico.getColumnModel().getColumn(0).setPreferredWidth(100);
        tb_mecanico.getColumnModel().getColumn(1).setPreferredWidth(300);
    }

    public void preencherTabelaAdotantePesq() throws RemoteException {
        listaMecanicos = mec_serv.findAll(txt_pesquisa.getText());
        model = new TabelaMecanico(listaMecanicos);
        tb_mecanico.setModel(model);

        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < model.getColumnCount(); i++) {
            tb_mecanico.getColumnModel().getColumn(i).setCellRenderer(render);
        }
        tb_mecanico.getColumnModel().getColumn(0).setPreferredWidth(100);
        tb_mecanico.getColumnModel().getColumn(1).setPreferredWidth(300);
    }

    public void inicializacao() {
        this.setLayout(null);
        this.setSize(1300, 475);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        componentesDao();

        font_lbs = new Font("Century Gothic", Font.BOLD, 13);
        font_lb_titulos = new Font("Century Gothic", Font.BOLD, 18);
        cor_font_lbs = new Color(41, 129, 179);
        cor_font_txt = new Color(61, 61, 61);

        pane_titulo = new JPanel(null);
        pane_titulo.setBounds(5, 5, 1280, 35);
        pane_titulo.setBackground(new Color(41, 129, 179));

        lb_titulo = new JLabel("CRUD DO MECANICO");
        lb_titulo.setForeground(new Color(255, 255, 255));
        lb_titulo.setFont(new Font("Century Schoolbook", Font.BOLD, 25));
        lb_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        lb_titulo.setBounds(5, 5, 1285, 25);
        pane_titulo.add(lb_titulo);
        this.add(pane_titulo);

        pane_dados = new JPanel(null);
        pane_dados.setBounds(10, 45, 580, 370);
        pane_dados.setBackground(new Color(255, 255, 255));
        pane_dados.setBorder(new LineBorder(new Color(41, 129, 179), 1));
        this.add(pane_dados);

        // P dados pessoais
        pDadosPessoais();

        //Pane sexo
        pSexo();

        // P contactos 
        pContactos();

        //PDadosAcademicos
        pEstadoCivil();
        btn_sair = new Botao("Sair");
        btn_sair.setBackground(new Color(255, 0, 0));
        btn_sair.setBackgroundMouseEntered(new Color(255, 24, 24));
        btn_sair.setForeground(new Color(255, 255, 255));
        btn_sair.setBounds(450, 330, 120, 30);
        btn_sair.setBorder(new Color(255, 255, 255));
        pane_dados.add(btn_sair);

        btn_cadastrar = new Botao("Cadastrar");
        btn_cadastrar.setBackground(new Color(0, 128, 0));
        btn_cadastrar.setBackgroundMouseEntered(new Color(85, 170, 85));
        btn_cadastrar.setForeground(new Color(255, 255, 255));
        btn_cadastrar.setBounds(320, 330, 120, 30);
        btn_cadastrar.setBorder(new Color(255, 255, 255));
        pane_dados.add(btn_cadastrar);

    }

    private void pDadosPessoais() {
        leftP_y = 10;
        leftP_x = 10;

        pane_dados_pessoais = new JPanel(null);
        pane_dados_pessoais.setBackground(new Color(255, 255, 255));
        pane_dados_pessoais.setBounds(leftP_x, leftP_y, 300, 350);
        pane_dados_pessoais.setBorder(new LineBorder(cor_font_lbs, 1));
        pane_dados.add(pane_dados_pessoais);

        lb_dados_pessoasis = new JLabel("Dados Pessoais");
        lb_dados_pessoasis.setOpaque(true);
        lb_dados_pessoasis.setBounds(5, 5, pane_dados_pessoais.getWidth() - 10, 30);
        lb_dados_pessoasis.setBackground(new Color(41, 129, 179));
        lb_dados_pessoasis.setHorizontalAlignment(SwingConstants.CENTER);
        lb_dados_pessoasis.setForeground(new Color(255, 255, 255));
        lb_dados_pessoasis.setFont(font_lb_titulos);
        pane_dados_pessoais.add(lb_dados_pessoasis);

        lb_nome = new JLabel("Nome Completo");
        lb_nome.setFont(font_lbs);
        lb_nome.setForeground(cor_font_lbs);
        lb_nome.setBounds(20, (h = h + 45), 250, 25);
        pane_dados_pessoais.add(lb_nome);

        txt_nome = new CampoTexto();
        txt_nome.setTextoFundo("Digite o nome completo");
        txt_nome.setFont(font_lbs);
        txt_nome.setForeground(cor_font_txt);
        txt_nome.setBounds(20, (h = h + 30), 270, 25);
        pane_dados_pessoais.add(txt_nome);

        lb_idade = new JLabel("Idade");
        lb_idade.setFont(font_lbs);
        lb_idade.setForeground(cor_font_lbs);
        lb_idade.setBounds(20, (h = h + 25), 270, 25);
        pane_dados_pessoais.add(lb_idade);

        txt_idade = new CampoTexto();
        txt_idade.setTextoFundo("Digite aqui a sua Idade");
        txt_idade.setFont(font_lbs);
        txt_idade.setForeground(cor_font_txt);
        txt_idade.setBounds(20, (h = h + 25), 270, 25);
        pane_dados_pessoais.add(txt_idade);

        lb_nacionalidade = new JLabel("Endereco");
        lb_nacionalidade.setFont(font_lbs);
        lb_nacionalidade.setForeground(cor_font_lbs);
        lb_nacionalidade.setBounds(20, (h = h + 25), 300, 25);
        pane_dados_pessoais.add(lb_nacionalidade);

        txt_nacionalidade = new CampoTexto();
        txt_nacionalidade.setTextoFundo("Digite aqui a sua Nacionalidade");
        txt_nacionalidade.setFont(font_lbs);
        txt_nacionalidade.setForeground(cor_font_txt);
        txt_nacionalidade.setBounds(20, (h = h + 25), 270, 25);
        pane_dados_pessoais.add(txt_nacionalidade);

        lb_bi = new JLabel("Numero do BI");
        lb_bi.setFont(font_lbs);
        lb_bi.setForeground(cor_font_lbs);
        lb_bi.setBounds(20, (h = h + 25), 270, 25);
        pane_dados_pessoais.add(lb_bi);

        txt_num_bi = new CampoTexto();
        txt_num_bi.setTextoFundo("Digite o numero do BI");
        txt_num_bi.setFont(font_lbs);
        txt_num_bi.setForeground(cor_font_txt);
        txt_num_bi.setBounds(20, (h = h + 25), 270, 25);
        pane_dados_pessoais.add(txt_num_bi);

        lb_profissao = new JLabel("DESCRICAO");
        lb_profissao.setFont(font_lbs);
        lb_profissao.setForeground(cor_font_lbs);
        lb_profissao.setBounds(20, (h = h + 25), 270, 25);
        pane_dados_pessoais.add(lb_profissao);

        txt_descricao = new CampoTexto();
        txt_descricao.setTextoFundo("Digite mais detalhes sobre o seu trabalho aqui");
        txt_descricao.setFont(font_lbs);
        txt_descricao.setForeground(cor_font_txt);
        txt_descricao.setBounds(20, (h = h + 25), 270, 70);
        pane_dados_pessoais.add(txt_descricao);
    }

    private void pSexo() {
        int h;
        rightP_x = 320;
        rightP_y = 10;
        pane_morada = new JPanel(null);
        pane_morada.setBackground(new Color(255, 255, 255));
        pane_morada.setBounds(rightP_x, rightP_y, 250, 100);
        pane_morada.setBorder(new LineBorder(cor_font_lbs, 1));
        pane_dados.add(pane_morada);

        lb_titulo_morada = new JLabel("Sexo");
        lb_titulo_morada.setOpaque(true);
        lb_titulo_morada.setBounds(5, 5, pane_morada.getWidth() - 10, 30);
        lb_titulo_morada.setBackground(new Color(41, 129, 179));
        lb_titulo_morada.setHorizontalAlignment(SwingConstants.CENTER);
        lb_titulo_morada.setForeground(new Color(255, 255, 255));
        lb_titulo_morada.setFont(font_lb_titulos);
        pane_morada.add(lb_titulo_morada);

        h = 40;

        pane_generos = new JPanel(null);
        pane_generos.setBackground(new Color(255, 255, 255));
        pane_generos.setBounds(20, (h = h + 15), 220, 30);
        pane_generos.setBorder(new LineBorder(cor_font_lbs, 1));
        pane_morada.add(pane_generos);

        btn_masc = new JRadioButton("Masculino");
        btn_masc.setBackground(new Color(255, 255, 255));
        btn_masc.setActionCommand("Masculino");
        btn_masc.setForeground(cor_font_lbs);
        btn_masc.setFont(font_lbs);
        btn_masc.setBounds(10, (pane_generos.getHeight() - 20) / 2, 95, 20);
        btn_masc.setFocusPainted(false);
        btn_masc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pane_generos.add(btn_masc);

        btn_fem = new JRadioButton("Femenino");
        btn_fem.setBackground(new Color(255, 255, 255));
        btn_fem.setActionCommand("Femenino");
        btn_fem.setForeground(cor_font_lbs);
        btn_fem.setFont(font_lbs);
        btn_fem.setBounds(110, (pane_generos.getHeight() - 20) / 2, 95, 20);
        btn_fem.setFocusPainted(false);
        btn_fem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pane_generos.add(btn_fem);

        btn_grupoGenero = new ButtonGroup();
        btn_grupoGenero.add(btn_masc);
        btn_grupoGenero.add(btn_fem);

    }

    private void pContactos() {
        pane_contactos = new JPanel(null);
        pane_contactos.setBackground(new Color(255, 255, 255));
        pane_contactos.setBounds(rightP_x, (rightP_y = rightP_y + 110), 250, 110);
        pane_contactos.setBorder(new LineBorder(cor_font_lbs, 1));
        pane_dados.add(pane_contactos);

        lb_contactos = new JLabel("Contactos");
        lb_contactos.setOpaque(true);
        lb_contactos.setBounds(5, 5, pane_morada.getWidth() - 10, 30);
        lb_contactos.setBackground(new Color(41, 129, 179));
        lb_contactos.setHorizontalAlignment(SwingConstants.CENTER);
        lb_contactos.setForeground(new Color(255, 255, 255));
        lb_contactos.setFont(font_lb_titulos);
        pane_contactos.add(lb_contactos);

        int h = 40;
        lb_cell = new JLabel("Celular");
        lb_cell.setFont(font_lbs);
        lb_cell.setForeground(cor_font_lbs);
        lb_cell.setBounds(20, h, 220, 25);
        pane_contactos.add(lb_cell);

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
        txt_cell.setBounds(20, (h = h + 25), 220, 30);
        pane_contactos.add(txt_cell);

    }

    private void pEstadoCivil() {
        pane_dados_acade = new JPanel(null);
        pane_dados_acade.setBackground(new Color(255, 255, 255));
        pane_dados_acade.setBounds(rightP_x, (leftP_y = leftP_y + 235), 250, 75);
        pane_dados_acade.setBorder(new LineBorder(cor_font_lbs, 1));
        pane_dados.add(pane_dados_acade);

        lb_dados_acad = new JLabel("Estado Civil");
        lb_dados_acad.setOpaque(true);
        lb_dados_acad.setBounds(5, 5, pane_morada.getWidth() - 10, 30);
        lb_dados_acad.setBackground(new Color(41, 129, 179));
        lb_dados_acad.setHorizontalAlignment(SwingConstants.CENTER);
        lb_dados_acad.setForeground(new Color(255, 255, 255));
        lb_dados_acad.setFont(font_lb_titulos);
        pane_dados_acade.add(lb_dados_acad);

        int h = 40;

        btn_sol = new JRadioButton("Sol");
        btn_sol.setBackground(new Color(255, 255, 255));
        btn_sol.setActionCommand("Solteiro");
        btn_sol.setForeground(cor_font_lbs);
        btn_sol.setFont(font_lbs);
        btn_sol.setBounds(10, pane_dados_acade.getHeight() / 2 + 5, pane_dados_acade.getWidth() / 3 - 10, 20);
        btn_sol.setFocusPainted(false);
        btn_sol.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pane_dados_acade.add(btn_sol);

        btn_cas = new JRadioButton("Cas");
        btn_cas.setBackground(new Color(255, 255, 255));
        btn_cas.setActionCommand("Casado");
        btn_cas.setForeground(cor_font_lbs);
        btn_cas.setFont(font_lbs);
        btn_cas.setBounds(pane_dados_acade.getWidth() / 3 + 10, pane_dados_acade.getHeight() / 2 + 5, pane_dados_acade.getWidth() / 3 - 30, 20);
        btn_cas.setFocusPainted(false);
        btn_cas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pane_dados_acade.add(btn_cas);

        btn_div = new JRadioButton("Div");
        btn_div.setBackground(new Color(255, 255, 255));
        btn_div.setActionCommand("Divorciado");
        btn_div.setForeground(cor_font_lbs);
        btn_div.setFont(font_lbs);
        btn_div.setBounds(pane_dados_acade.getWidth() / 3 * 2 + 20, pane_dados_acade.getHeight() / 2 + 5, pane_dados_acade.getWidth() / 3 - 30, 20);
        btn_div.setFocusPainted(false);
        btn_div.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pane_dados_acade.add(btn_div);

        btn_grupoEstadoCivil = new ButtonGroup();
        btn_grupoEstadoCivil.add(btn_sol);
        btn_grupoEstadoCivil.add(btn_cas);
        btn_grupoEstadoCivil.add(btn_div);
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

    private JPanel pane_titulo, pane_dados, pane_morada,
            pane_generos, pane_dados_pessoais, pane_dados_acade, pane_contactos;

    private JLabel lb_titulo, lb_nome, lb_idade,
            lb_titulo_morada, lb_bi, lb_nacionalidade, lb_profissao,
            lb_dados_pessoasis, lb_dados_acad, lb_contactos, lb_cell, lb_info;
    private CampoTexto txt_nome, txt_nacionalidade, txt_descricao,
            txt_idade, txt_num_bi;
    private Font font_lbs, font_lb_titulos;
    private Color cor_font_lbs, cor_font_txt;
    private JFormattedTextField txt_cell;
    private JRadioButton btn_masc, btn_fem, btn_sol, btn_cas, btn_div;
    private Botao btn_cadastrar, btn_sair;
    private ButtonGroup btn_grupoGenero, btn_grupoEstadoCivil;
    int h, leftP_x, leftP_y, rightP_x, rightP_y;
    private Mecanico mecanico;
    private boolean control = false;
    private List<Mecanico> listaMecanicos;
    private JPanel paneP, paneTitulo, paneButoes, paneCampos, paneAbigaBu, pane_info;
    private JScrollPane pane_Table;
    private TabelaMecanico model;
    private JButton btn_novo, btn_remover, btn_editar, btn_info, btn_back;
    private CampoTexto txt_pesquisa;
    private JTable tb_mecanico;

    static MecanicoService mec_serv;
    static FichaService fich_serv;

    public static void main(String[] args) throws RemoteException {
        new DialogoCadastroMecanico();
    }
}
