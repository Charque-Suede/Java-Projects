/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ficheiro;

import java.io.*;
import java.util.Vector;

/**
 *
 * @author osval
 */
public class LeiturasEscritas {
    
	public boolean gravarDados(Vector lista, String nomeFich) {
		try {
			FileOutputStream fOut = new FileOutputStream(nomeFich);
			ObjectOutputStream obOut = new ObjectOutputStream(fOut);
			obOut.writeObject(lista);
			obOut.close();
			return true;
		} catch (IOException e) {
			System.out.println("Ocorreu um erro ao gravar dados no ficheiro de objectos");
			return false;
		}
		
		
	}

	@SuppressWarnings("rawtypes")
	public Vector lerDados(String nomeFich) {
	    Vector listaDados = new Vector();
		try {
			FileInputStream fIn = new FileInputStream(nomeFich);
			ObjectInputStream obIn = new ObjectInputStream(fIn);
			listaDados = (Vector) ( obIn.readObject());
			obIn.close();

		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro de objectos não encontrado");
		} catch (ClassNotFoundException f) {
			System.out.println("Classe não encontrada");
		} catch (IOException i) {
			System.out.println("Problema na leitura do ficheiro");
		}

		return listaDados;
	}

    
}
