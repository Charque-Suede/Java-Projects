package entregavel;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class ClienteDAO {

    private Connection conexao;

    public ClienteDAO() {
        try {
            conexao = BDConexao.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void gravar(String query) {
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Query executada com sucesso");
        } catch (SQLException e) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Erro ao executar a query");
        }
    }

    public void actualizar(String query) {
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            System.out.println(query);
            ps.executeUpdate();
            System.out.println(query);
            ps.close();
            System.out.println(query);
            JOptionPane.showMessageDialog(null, "Query executada com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a query");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void apagar(String query) {
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Query executada com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar a query");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public ArrayList<Cliente> listar(String query) {
        ArrayList<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement ps = conexao.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("matricula"), rs.getString("nome"), rs.getInt("idade"),
                        rs.getString("sexo").charAt(0)));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fazer select");
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;

    }

}
