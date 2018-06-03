package br.com.rodolfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DataBase
 */
public class DataBase {

    public static Connection getConnection() throws SQLException {
        
        //Necessário para 'mysql-connector' maior que a versão 6.0'
		//Class.forName("com.mysql.cj.jdbc.Driver");
		final String BD_URL = "jdbc:mysql://127.0.0.1/lista?useTimezone=true&serverTimezone=UTC&useSSL=false";
		
		//gerenciador de conexão escolhe qual estratégia adequada para a comunicação com o banco de dados
		Connection connection = DriverManager.getConnection(BD_URL, "root", "root");

        return connection;
    }

}