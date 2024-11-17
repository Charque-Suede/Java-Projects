package emailapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Charque Suede Junior
 */
public class Email {

    private String nome;
    private String apelido;
    private String senha;
    private String email;
    private String departamento;
    private int capacidadeMailBox= 512;
    private String emailAlternativo = "recuperaremail@empresaCSJ.co.mz";
    private String empresa = "empresaCSJ.co.mz";

    BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));

    /**
     * construtor que recebera o nome e apelido e faz inicializacao de outras
     * variaveis atraves de chamada de metedos respectivos
     */
    public Email(String nome, String apelido) throws IOException {
        this.nome = nome;
        this.apelido = apelido;
        this.departamento = setDepartamento();
        this.senha = setSenha(8);
        this.email = gerarEmail();
    }

    //Define o departamento 
    private String setDepartamento() throws IOException {
        System.out.println("Informe o departamento: \n1 - Vendas \n2 - Desenvolvimento \n3 - Contabilidade \n0 - Nenhum \n");
        int opcao = Integer.parseInt(bfr.readLine());

        switch (opcao) {
            case (0):
                return "";
            case (1):
                return "Vendas";
            case (2):
                return "Desenvolvimento";
            case (3):
                return "Contabilidade";
            default:
                return "";
        }
    }

    // Gera a senha aleatoriamente, atravez de um conjunto de caracteres     definidas
    private String setSenha(int tamanho) {
        String caracteresSenha = "ABCDEFGHIJKLMNOPQRSTUVXWYZ#$@!&^@()*&@^$%#@$!1234567890";
        char[] senha = new char[tamanho];
        for (int i = 0; i < tamanho; i++) {
            int aleatorio = (int) (Math.random() * caracteresSenha.length());
            senha[i] = caracteresSenha.charAt(aleatorio);
        }
        return new String(senha);
    }

    private String gerarEmail() {
        return this.nome + "." + this.apelido + "@" + this.departamento + "." + this.empresa.toLowerCase();
    }

    public void mostrarInformacao() {
        System.out.println("______________________________________ \nNome: " + this.nome + " " + this.apelido);
        System.out.println("Email: " + this.email + "\nEmail Alternativo: " + this.emailAlternativo + "\nDepartamento: " + this.departamento);
        System.out.println("capacidade do mailBox: " + this.capacidadeMailBox + "mb");
    }

    //Setters
    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public void setCapacidadeMailBox(int capacidadeMailBox) {
        this.capacidadeMailBox = capacidadeMailBox;
    }

    public void setEmailAlternativo(String emailAlternativo) {
        this.emailAlternativo = emailAlternativo;
    }

    //Getters 
    public String getSenha() {
        return senha;
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public String getEmailAlternativo() {
        return emailAlternativo;
    }

    public int getCapacidadeMailBox() {
        return capacidadeMailBox;
    }

}
