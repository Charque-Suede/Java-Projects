package entregavel;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexao implements Serializable{

    private String host, nomeBD, user, password, porta;

    public BDConexao() {
        host = "localhost";
        nomeBD = "registromatricula";
        user = "root";
        password = "";
        porta = "3306";
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        BDConexao conexao = new BDConexao(); 

        return DriverManager.getConnection("jdbc:mysql://" + conexao.host + ":" + conexao.porta + "/"
                + conexao.nomeBD + "?user=" + conexao.user + "&password=" + conexao.password);

    }
}
