/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Ficheiro.LeiturasEscritas;
import Objectos.*;
import java.io.*;
import java.util.Vector;
import Validacoes.Validacoes;

/**
 *
 * @author Charque-Suede
 */
public class Gestao implements Interface {

    private String nome, apelido, endereco, email, codigo, celular;
    private String categoria, id, quantidade, precoUnitario;
    private String user, password;
    private int cont;
    private double precoU;
    public Vector<Pessoa> pessoa = new Vector<>();
    ;
    public Vector<Venda> venda = new Vector<>();
    ;
    public Vector<Produto> produto = new Vector<>();
    ;
    Validacoes v = new Validacoes();
    ;
    LeiturasEscritas l = new LeiturasEscritas();
    ;
    
    public Pessoa p;
    public Venda ven;
    public Produto prod;

    //Metodos do Cliente;
    @Override
    public void criarCliente() {

        apelido = v.validarString("INSIRA O APELIDO: ");
        nome = v.validarString("INSIRA O NOME: ");
        endereco = v.validarString("INSIRA O ENDERCO: ");
        email = v.validarString("INSIRA O email: ");
        celular = v.validarString("INSIRA O CELULAR: ");
        codigo = celular;
        p = new Cliente(nome, apelido, endereco, email, codigo, celular);
        pessoa.add(p);

        if (l.gravarDados(pessoa, "pessoas.arq")) //Se conseguir gravar no ficheiro
        {
            System.out.println("CADASTRADO COM SUCESSO;");
        }
    }

    @Override
    public void actualizarCliente() {
        boolean bol = false;
        id = v.validarString("INSIRA O CODIGO DO CLIENTE");
        for (int i = 0; i < pessoa.size(); i++) {
            if (id.equals(pessoa.get(i).getCodigo())) {
                menuActualizarCliente(i);

                if (l.gravarDados(pessoa, "pessoas.arq")) {
                    System.out.println("ACTUALIZADO COM SUCESSO");
                }
                bol=true;
                break;
            }
        }
        if(!bol)
            System.out.println("O CODIGO INSERIDO NAO ENCONTRA-SE NO BANCO DE DADOS");
    }

    @Override
    public boolean removerCliente() {
        boolean bol = false;
        id = v.validarString("INSIRA O CODIGO DO CLIENTE");
        for (int i = 0; i < pessoa.size(); i++) {
            if (pessoa.get(i).getCodigo().equalsIgnoreCase(id)) {
                Cliente clienteAntigo = (Cliente) pessoa.get(i);
                pessoa.remove(i);

                if (l.gravarDados(pessoa, "pessoas.arq")) {
                    System.out.println(clienteAntigo.getNome()
                            + "" + clienteAntigo.getApelido() + " REMOVIDO COM SUCESSO!!");
                }

                bol = true;
                return bol;
            }

        }
        return bol;

    }

    public void subMenuCliente() {
        int op = 0;
        do {
            op = v.validarInt(1, 7, "____________CLIENTES____________"
                    + "\n 1- Cadastrar "
                    + "\n 2- Actualizar"
                    + "\n 3- Remover"
                    + "\n 4- Listar todos Clientes"
                    + "\n 5- Verificar Existencia"
                    + "\n 6- Voltar"
                    + "\n 7- Sair"
                    + "\n________________________________");
            switch (op) {
                case 1:
                    criarCliente();
                    break;
                case 2:
                    actualizarCliente();
                    break;
                case 3:
                    ;
                    if (!removerCliente()) {
                        System.out.println("ERRO, CODIGO NAO ENCONTRADO NA BASE DE DADOS");
                    }
                    break;
                case 4:
                    ListarClientes();
                    break;
                case 5:
                    VerificarExistenciaCliente();
                    break;
                case 6:
                    Menu();
                    break;
                case 7:
                    if (v.Confirmar()) {
                        System.exit(0);
                    } else {
                        Menu();
                    }
                    break;
            }

        } while (op < 1 || op > 7);
    }

    public void menuActualizarCliente(int indice) {
        int opcao = 0;
        String aux;
        do {
            opcao = v.validarInt(1, 6, "ACTUALIZAR DADOS DO CLIENTE"
                    + "\n 1.ACTUALIZAR NOME"
                    + "\n 2.ACTUALIZAR APELIDO"
                    + "\n 3.ACTUALIZAR EMAIL"
                    + "\n 4.ACTUALIZAR CELULAR"
                    + "\n 5.ACTUALIZAR ENDERCO"
                    + "\n 6.SAIR");

            switch (opcao) {
                case 1:
                    aux = v.validarString("DIGITE O NOVO NOME: ");
                    pessoa.get(indice).setNome(aux);
                    System.out.println("NOME ACTUALIZADO COM SUCESSO!!");
                    break;

                case 2:
                    aux = v.validarString("DIGITE O NOVO APELIDO: ");
                    pessoa.get(indice).setApelido(aux);
                    System.out.println("APELIDO ACTUALIZADO COM SUCESSO!!");
                    break;

                case 3:
                    aux = v.validarString("DIGITE O NOVO EMAIL: ");
                    pessoa.get(indice).setEmail(aux);
                    System.out.println("EMAIL ACTUALIZADO COM SUCESSO!!");
                    break;

                case 4:
                    aux = v.validarString("DIGITE O NOVO CELULAR: ");
                    pessoa.get(indice).setCelular(aux);
                    System.out.println("CELULAR ACTUALIZADO COM SUCESSO!!");
                    break;

                case 5:
                    aux = v.validarString("DIGITE O NOVO ENDERECO: ");
                    pessoa.get(indice).setEndereco(aux);
                    System.out.println("ENDERECO ACTUALIZADO COM SUCESSO!!");
                    break;

                case 6:
                    if (v.Confirmar()) {
                        System.exit(0);
                    } else {
                        Menu();
                    }

            }
        } while (opcao < 0 || opcao > 6);
    }

    public void ListarClientes() {
        String l = "---------------CLIENTES CADASTRADOS---------------";
        for (Pessoa p : pessoa) {
            if (p instanceof Cliente) {
                l += p.toString();
            }
        }
        System.out.println(l);
    }

    public void VerificarExistenciaCliente() {
        boolean bol=false;
        id = v.validarString("INSIRA O ID A PROCURAR");
        for (Pessoa p : pessoa) {
            if (p instanceof Cliente) {
                p.getCodigo().equalsIgnoreCase(id);
                System.out.println("O ID ESTA NO SISTEMA!!\n"
                        + "PERTENCE A SR(A): " + p.getNome().toUpperCase()
                        + " " + p.getApelido().toUpperCase());
                        bol=true;
                        break;
            }
        }
        if(!bol)
            System.out.println("ID NAO EXISTENTE NO BANCO DE DADOS;");
    }

    //Metodos do Funcionario;
    @Override
    public void criarFuncionario() {
        apelido = v.validarString("INSIRA O APELIDO: ");
        nome = v.validarString("INSIRA O NOME: ");
        endereco = v.validarString("INSIRA O ENDERCO: ");
        email = v.validarString("INSIRA O email: ");
        celular = v.validarString("INSIRA O CELULAR: ");
        user = email + "123";
        password = celular;
        codigo = celular;
        p = new Funcionario(user, password, nome, apelido, endereco,
                email, codigo, celular);
        pessoa.add(p);

        if (l.gravarDados(pessoa, "pessoas.arq")) {
            System.out.println("CADASTRADO COM SUCESSO;");
        }
    }

    @Override
    public void actualizarFuncionario() {
        boolean bol = false;
        id = v.validarString("INSIRA O CODIGO DO FUNCIONARIO");
        cont = 0;
        for (Pessoa p : pessoa) {
            cont++;
            if (p instanceof Funcionario) {
                if (p.getCodigo().equalsIgnoreCase(id)) {
                    menuActualizarFuncionario(cont);

                    if (l.gravarDados(pessoa, "pessoas.arq")) {
                        System.out.println("ACTUALIZADO COM SUCESSO");
                    }
                    bol=true;
                    break;
                }
            }
        }
        if(!bol)
            System.out.println("O CODIGO INSERIDO NAO ENCONTRA-SE NO BANCO DE DADOS");
    }

    @Override
    public void removerFuncionario() {
        boolean bol = false;
        id = v.validarString("INSIRA O CODIGO DO FUNCIONARIO");
        for (Pessoa p : pessoa) {
            if (p instanceof Funcionario) {
                Funcionario funcionarioAntigo = (Funcionario) p;
                if (p.getCodigo().equalsIgnoreCase(id)) {
                    pessoa.remove(p);

                    if (l.gravarDados(pessoa, "pessoas.arq")) {
                        System.out.println(p.getNome().toUpperCase() + ""
                                + p.getApelido().toUpperCase() + " REMOVIDO COM SUCESSO!!");
                    }
                        bol=true;
                    break;
                }
            }
        }
        if(!bol)
            System.out.println("CODIGO NAO ENCONTRADO NA BASE DE DADOS! \n TENTE NOVAMENTE");
    }

    public void subMenuFuncionario() {
        int op = 0;
        do {
            op = v.validarInt(1, 7,
                    "____________FUNCIONARIOS____________"
                    + "\n 1- Cadastrar "
                    + "\n 2- Actualizar"
                    + "\n 3- Remover"
                    + "\n 4- Listar todos Funcionarios"
                    + "\n 5- Verificar Existencia"
                    + "\n 6- Voltar"
                    + "\n 7- Sair"
                    + "\n____________________________________");
            switch (op) {
                case 1:
                    criarFuncionario();
                    break;
                case 2:
                    actualizarFuncionario();
                    break;
                case 3:
                    removerCliente();
                    break;
                case 4:
                    ListarFuncionarios();
                    break;
                case 5:
                    VerificarExistenciaFuncionario();
                    break;
                case 6:
                    Menu();
                    break;
                case 7:
                    if (v.Confirmar()) {
                        System.exit(0);
                    } else {
                        Menu();
                    }
                    break;
            }

        } while (op < 1 || op > 7);
    }

    public void menuActualizarFuncionario(int indice) {
        int opcao = 0;
        String aux;
        do {
            opcao = v.validarInt(1, 7,
                    "------ACTUALIZAR DADOS DO FUNCIONARIO-----"
                    + "\n 1- ACTUALIZAR NOME"
                    + "\n 2- ACTUALIZAR APELIDO"
                    + "\n 3- ACTUALIZAR EMAIL"
                    + "\n 4- ACTUALIZAR CELULAR"
                    + "\n 5- ACTUALIZAR ENDERCO"
                    + "\n 6- ACTUALIZAR PASSWORD"
                    + "\n 7- SAIR"
                    + "-----------------------------------------");

            switch (opcao) {
                case 1:
                    aux = v.validarString("DIGITE O NOVO NOME: ");
                    pessoa.get(indice).setNome(aux);
                    System.out.println("NOME ACTUALIZADO COM SUCESSO!!");
                    break;

                case 2:
                    aux = v.validarString("DIGITE O NOVO APELIDO: ");
                    pessoa.get(indice).setApelido(aux);
                    System.out.println("APELIDO ACTUALIZADO COM SUCESSO!!");
                    break;

                case 3:
                    aux = v.validarString("DIGITE O NOVO EMAIL: ");
                    pessoa.get(indice).setEmail(aux);
                    System.out.println("EMAIL ACTUALIZADO COM SUCESSO!!");
                    break;

                case 4:
                    aux = v.validarString("DIGITE O NOVO CELULAR: ");
                    pessoa.get(indice).setCelular(aux);
                    System.out.println("CELULAR ACTUALIZADO COM SUCESSO!!");
                    break;

                case 5:
                    aux = v.validarString("DIGITE O NOVO ENDERECO: ");
                    pessoa.get(indice).setEndereco(aux);
                    System.out.println("ENDERECO ACTUALIZADO COM SUCESSO!!");
                    break;

                case 6:
                    aux = v.validarString("DIGITE A NOVA SENHA: ");
                    pessoa.get(indice).setEndereco(aux);
                    System.out.println("SENHA ACTUALIZADA COM SUCESSO!!");
                    break;
                case 7:
                    if (v.Confirmar()) {
                        System.exit(0);
                    } else {
                        Menu();
                    }

            }
        } while (opcao < 0 || opcao > 7);

    }

    public void ListarFuncionarios() {
        String l = "---------------FUNCIONARIOS CADASTRADOS---------------";
        for (Pessoa p : pessoa) {
            if (p instanceof Funcionario) {
                l += p.toString();
            }
        }
        System.out.println(l);
    }

    public void VerificarExistenciaFuncionario() {
        boolean bol=false;
        id = v.validarString("INSIRA O ID A PROCURAR");
        for (Pessoa p : pessoa) {
            if (p instanceof Funcionario) {
                if(p.getCodigo().equalsIgnoreCase(id)){
                System.out.println("O ID ESTA NO SISTEMA!!\n"
                        + "PERTENCE A SR(A): " + p.getNome().toUpperCase()
                        + " " + p.getApelido().toUpperCase());
                    bol=true;
                    break;
                }
            }
        }
        if(!bol)
            System.out.println("ID NAO EXISTENTE NO BANCO DE DADOS;");
    }

    //Metodos do produto
    @Override
    public void criarProduto() {
        nome = v.validarString("INSIRA O NOME: ");
        precoU = v.validarDouble(5, 1500, "INSIRA O PRECO UNITARIO: ");
        id = nome + "@1234";
        prod = new Produto(nome, id, 0, precoU);
        produto.add(prod);

        if (l.gravarDados(produto, "produtos.arq")) {
            System.out.println("CADASTRADO COM SUCESSO;");
        }
    }

    @Override
    public void actualizarProduto() {
        boolean bol=false;
        id = v.validarString("INSIRA O CODIGO DO PRODUTO A AUMENTAR O ESTOQUE COM O FORNECEDOR");
        for (int i = 0; i < produto.size(); i++) {
            if (id.equalsIgnoreCase(produto.get(i).getId())) {
                menuActualizarProduto(i);

                if (l.gravarDados(produto, "produtos.arq")) {
                    System.out.println(produto.get(i).getNome() + " ACTUALIZADO"
                            + "COM SUCESSO!!");
                }
                bol=true;
                break;
            }
        }
        if(!bol)
            System.out.println("NENHUM PRODUTO COM O CODIGO " + id);
    }

    @Override
    public void removerProduto() {
       boolean bol=false;
        id = v.validarString("INSIRA O ID A PROCURAR");
        for (int i = 0; i < produto.size(); i++) {
            if (id.equalsIgnoreCase(produto.get(i).getId())) {
                produto.remove(i);

                if (l.gravarDados(produto, "produtos.arq")) {
                    System.out.println(produto.get(i).getNome() + " REMOVIDO COM SUCESSO!!");
                }
                    bol=true;
                break;
            }
        }
        if(!bol)
            System.out.println("NENHUM PRODUTO COM O CODIGO " + id);
    }

    public void menuActualizarProduto(int indice) {
        int opcao = 0;
        String aux;
        do {
            opcao = v.validarInt(1, 4, "---------ACTUALIZAR DADOS DOS PRODUTOS---------"
                    + "\n1.ACTUALIZAR NOME"
                    + "\n2.ACTUALIZAR QUANTIDADE"
                    + "\n3.ACTUALIZAR PRECO"
                    + "\n4.Sair");

            switch (opcao) {
                case 1:
                    aux = v.validarString("DIGITE O NOVO NOME: ");
                    pessoa.get(indice).setNome(aux);
                    System.out.println("NOME ACTUALIZADO COM SUCESSO!!");
                    break;

                case 2:
                    cont = v.validarInt(0, 1000000, "QUE QUANTIDADE DESEJA AUMENTAR: ");
                    produto.get(indice).setQuantidade(produto.get(indice)
                            .getQuantidade() + cont);
                    System.out.println("QUANTIDADE ADICIONADA!!");
                    break;

                case 3:
                    precoU = v.validarDouble(1, 1000000, "DIGITE O NOVO PRECO: ");
                    produto.get(indice).setPrecoUnitario(precoU);
                    System.out.println("PRECO ACTUALIZADO!!");
                    break;

                case 4:
                    if (v.Confirmar()) {
                        System.exit(0);
                    } else {
                        Menu();
                    }
            }
        } while (opcao < 0 || opcao > 5);
    }

    public boolean Vender(String idF, String idC) {
        double total = 0, precoY = 0;
        int resposta, quantidade;
        cont = 0;
        String recibo = "________________________________RECIBO______________________________"
                + "\nFUNCIONARIO Nr: " + idF + "...."
                + "\nCLIENTE NR: " + idC + "..."
                + "\n";
        do {

            System.out.println("__________PRODUTOS DISPONIVEIS__________");
            System.out.println("Nome:    Quant.Disponivel    Preco Unitario");
            for (int i = 0; i < produto.size(); i++) {
                Produto p = produto.get(i);
                System.out.println(i + 1 + "-" + p.getNome() + "\t "
                        + p.getQuantidade() + "\t" + p.getPrecoUnitario());
            }
            resposta = v.validarInt(0, produto.size()+1, "DESEJA COMPRAR QUAL???\n "
                    + (produto.size() + 1) + "-CANCELAR") - 1;
            if (resposta == produto.size()) {
                return false;
            }

            quantidade = v.validarInt(1, produto.get(resposta).getQuantidade(), " EM QUE QUANTIDADE ??");
            cont = v.validarInt(1, 2, "CONFIRMAR E TERMINAR A VENDA??\n 1-SIM\n 2-NAO");
            if (cont == 1) {
                Produto p = produto.get(resposta);
                p.setQuantidade(p.getQuantidade() - quantidade);
                precoY = quantidade * p.getPrecoUnitario();
                if (quantidade > 10) {
                    recibo += "Produto: " + p.getNome().toUpperCase() + "\tQuantidade: "
                            + quantidade + "\tPreco: " + precoY * 0.9 + "MZN\tDesconto: 10%\n";
                    total += precoY * 0.9;
                } else {
                    recibo += "Produto: " + p.getNome().toUpperCase() + "\tQuantidade: "
                            + quantidade + "\tPreco: " + precoY + "MZN\tDesconto: 0%\n";
                    total += precoY;
                }

            } else {
                Produto p = produto.get(resposta);
                p.setQuantidade(p.getQuantidade() - quantidade);
                precoY = quantidade * p.getPrecoUnitario();
                if (quantidade > 10) {
                    recibo += "Produto: " + p.getNome().toUpperCase() + "\tQuantidade: "
                            + quantidade + "\tPreco: " + precoY * 0.9 + "MZN\tDesconto: 10%\n";
                    total += precoY * 0.9;
                } else {
                    recibo += "Produto: " + p.getNome().toUpperCase() + "\tQuantidade: "
                            + quantidade + "\tPreco: " + precoY + "MZN\tDesconto: 0%\n";
                    total += precoY;

                }
            }
        } while (cont == 2);
        recibo += "\nTOTAL A PAGAR: " + total + "MZN"
                + "\n __________________________VOLTE SEMPRE_____________________________\n\n";
        System.out.println(recibo);
        criarVenda(recibo);
        return true;

    }

    public void listarProdutos() {
        String l = "_____________PRODUTOS______________\n";
        for (int i = 0; i < produto.size(); i++) {
            l += produto.get(i).toString() + "\n\n";
        }
        System.out.println(l);
    }

    public void Caixa() {
        boolean bol = false;
        String idF = v.validarString("INSIRA O SEU CODIGO COMO FUNCIONARIO");
        for (Pessoa p : pessoa) {
            if (p instanceof Funcionario) {
                if (p.getCodigo().equalsIgnoreCase(idF)) {
                    idF = p.getApelido() + ", " + p.getNome();
                    bol = true;
                    break;
                }
            }
        }
        if (!bol) {
            cont = v.validarInt(1, 2, "FUNCIONARIO NAO ENCONTRADO!\n "
                    + "DESEJA REGISTAR NOVO FUNCIONARIO ??\n 1-SIM\n 2-NAO");
            if (cont == 1) {
                criarFuncionario();
            } else {
                Menu();
            }
        }
        bol = false;
        String idC = v.validarString("INSIRA O CODIGO DO CLIENTE");
        for (Pessoa p : pessoa) {
            if (p instanceof Cliente) {
                if (p.getCodigo().equalsIgnoreCase(idC)) {
                    idC = p.getApelido() + ", " + p.getNome();
                    bol = true;
                    break;
                }
            }
        }

        if (!bol) {
            cont = v.validarInt(1, 2, "CLIENTE NAO ENCONTRADO!\n "
                    + "DESEJA REGISTAR NOVO CLIENTE ??\n 1-SIM\n 2-NAO");
            if (cont == 1) {
                criarCliente();
            } else {
                Menu();
            }
        }
        if (Vender(idF, idC)) {
            System.out.println("VENDA REALIZADA COM SUCESSO");
        } else {
            System.out.println("ERRO NA VENDA");
        }

    }

    @Override
    public void criarVenda(String recibo) {
        ven = new Venda(recibo);
        venda.add(ven);
        if (l.gravarDados(venda, "venda.arq")) {
            System.out.println("VENDIDO COM SUCESSO;");
        }
    }

    @Override
    public void todoRelatorioVendas() {
        String l = "_____________RELATORIO DE VENDAS______________\n\n";
        for (int i = 0; i < venda.size(); i++) {
            l += venda.get(i).toString() + "\n\n";
        }
        System.out.println(l);

    }

    public void Menu() {
        venda=l.lerDados("venda.arq");
        pessoa = l.lerDados("pessoas.arq");
        produto = l.lerDados("produtos.arq");
        int op = 0;
        do {
            op = v.validarInt(1, 6, "____________PAINEL DE GESTAO DA EMPRESA FRANGOS GULERA____________"
                    + "\n 1- Cliente"
                    + "\n 2- Funcionarios"
                    + "\n 3- Produtos"
                    + "\n 4- Venda"
                    + "\n 5- Relatorio de Vendas"
                    + "\n 6 -Sair"
                    + "\n__________________________________________________");
            switch (op) {
                case 1:
                    subMenuCliente();
                    Menu();
                    break;
                case 2:
                    subMenuFuncionario();
                    Menu();
                    break;

                case 3:
                    subMenuProduto();
                    Menu();
                    break;
                case 4:
                    Caixa();
                    Menu();
                    break;
                case 5:
                    relatorio();
                    Menu();
                    break;
                case 6:
                    if (v.Confirmar()) {
                        System.exit(0);
                    } else {
                        Menu();
                    }
                    break;
            }

        } while (op < 1 || op > 6);
    }

    public void relatorio(){
        String l="____________________RELATORIO DE VENDAS__________________\n";
        for (int i = 0; i <venda.size(); i++) {
            l+=venda.get(i).toString()+"\n\n";
        }
        System.out.println(l);
    }
    public void subMenuProduto() {
        int op = 0;
        do {
            op = v.validarInt(1, 6, "____________PRODUTOS____________"
                    + "\n 1- Cadastrar "
                    + "\n 2- Aumentar Stock No Fornecedor"
                    + "\n 3- Remover"
                    + "\n 4- Listar Todos"
                    + "\n 5- Voltar"
                    + "\n 6- Sair"
                    + "\n_______________________________");
            switch (op) {
                case 1:
                    criarProduto();
                    break;
                case 2:
                    actualizarProduto();
                    break;

                case 3:
                    removerProduto();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 6:
                    if (v.Confirmar()) {
                        System.exit(0);
                    } else {
                        Menu();
                    }
                    break;
                case 5:
                    Menu();
                    break;
            }

        } while (op < 1 || op > 5);
    }

    
    public void Login() {
        boolean bol=false;
        do{
        id = v.validarString("INSIRA O USER");
        password = v.validarString("INSIRA O PASSWORD");
        for (Pessoa p : pessoa) {
            if (p instanceof Funcionario) {
                if(((Funcionario) p).getUser().equalsIgnoreCase(id)
                        &&((Funcionario) p).getPassword().equalsIgnoreCase(password)){
                System.out.println("BEM VINDO"
                        + " SR(A): " + p.getNome().toUpperCase()
                        + " " + p.getApelido().toUpperCase());
                       Menu();
                }
            }
        }
        if(!bol)
            System.out.println("____________USER OU SENHA INVALIDOS_________\n"
                             + "TENTE NOVAMENTE OU CONTACTE O ADMINISTRADOR;");
        }while(!bol);
    }
    
    
}
