package emailapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Charque Suede Junior
 */
public class EmailApp {

    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        String continuar = null;
        ArrayList<Email> emails = new ArrayList<>();
        String nome, apelido;
        int tamanho = 0;

        while (continuar != "") {
            System.out.println("Registrando novo funcionario ");
            System.out.println("Informe o nome: ");
            nome = bfr.readLine();

            System.out.println("Informe o apelido: ");
            apelido = bfr.readLine();

            emails.add(new Email(nome, apelido));

//            System.out.println("Informe o email alternativo: ");
//            email1.setEmailAlternativo(bfr.readLine());
            emails.get(tamanho).mostrarInformacao();
            tamanho++;
            System.out.println("\nPressione Enter para continuar a gerar emails ou \nClique (1) para terminar");
            continuar = bfr.readLine();
        }

    }
}
