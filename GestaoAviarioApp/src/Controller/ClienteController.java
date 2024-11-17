package Controller;

import entregavel.Cliente;
import entregavel.ClienteDAO;
import java.util.ArrayList;



public class ClienteController {
    
    public static void gravarCliente(String query){
        ClienteDAO dao = new ClienteDAO();
        dao.gravar(query);
    }
    
    public static void actualizar(String query){
         ClienteDAO dao = new ClienteDAO();
         System.out.println(query);
        dao.actualizar(query);
    }
    
     public static void excluir(String query){
         
         ClienteDAO dao = new ClienteDAO();
         dao.apagar(query);
    }
     
    public static ArrayList<Cliente> getCliente(String query){
        ArrayList<Cliente> lista = new ArrayList<>();
        ClienteDAO dao = new ClienteDAO();
        lista = dao.listar(query);   
        return lista;
    }
    
}
