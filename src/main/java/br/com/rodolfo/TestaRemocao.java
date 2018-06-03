package br.com.rodolfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TestaRemocao
 */
public class TestaRemocao {

    public static void main(String[] args) throws SQLException {
        
        Connection connection = DataBase.getConnection();

        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM produtos WHERE id > 3");

        //Retorna o número de linhas que foram atualizadas (deletadas no caso)
        int count = statement.getUpdateCount();

        System.err.println(count + " Linhas atualizadas.");

        statement.close();
        connection.close();
    }
    
}