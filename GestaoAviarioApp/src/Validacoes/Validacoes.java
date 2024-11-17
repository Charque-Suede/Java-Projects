package Validacoes;

import java.io.*;

public class Validacoes {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public  String validarString(String msg) {
        String x = "";
        System.out.println(msg);
        do {
            try {
                x = br.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (x.length() == 0) {
                System.out.println("ESTE CAMPO NAO PODE ESTAR VAZIO");
            }
        } while (x.length() == 0);

        return x;
    }

    public  int validarInt(int min, int max, String msg) {
        int x = 0;

        System.out.println(msg);
        do {
            try {
                x = Integer.parseInt(br.readLine());
                if (x < min || x > max) {
                    System.out.println("VALOR INVALIDO");
                }
            } catch (NumberFormatException e) {
                System.out.println("INSIRA UM INTEIRO");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } while (x < min || x > max);

        return x;
    }

    public  double validarDouble(double min, double max, String msg) {
        double x = 0;

        System.out.println(msg);
        do {
            try {
                x = Double.parseDouble(br.readLine());
                if (x < min || x > max) {
                    System.out.println("VALOR INVALIDO");
                }
            } catch (NumberFormatException e) {
                System.out.println("INSIRA UM DOUBLE");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        } while (x < min || x > max);

        return x;
    }

    public  boolean Confirmar() {
        if (validarInt(1, 2, "TEM A CERTEZA DE QUE QUER SAIR"
                + " DO SISTEMA ??\n 1-SIM\n 2-NAO") == 1)
            return true;
        else
            return false;

    }
    
    public boolean Efectivar(String msg){
        if(validarInt(1, 2, msg)==1)
            return true;
        else
            return false;
    }

    
    
}
