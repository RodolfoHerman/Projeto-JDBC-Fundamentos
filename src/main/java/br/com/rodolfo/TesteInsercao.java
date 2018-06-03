package br.com.rodolfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TesteInsercao
 */
public class TesteInsercao {

    public static void main(String[] args) throws SQLException {
        
        Connection connection = DataBase.getConnection();

        Statement statement =  connection.createStatement();
        //Para recuperar o ID da inserção realizada passamos um segundo argumento 'RETURN_GENERATED_KEYS' no 'execute'
        boolean result = statement.execute("INSERT INTO produtos (nome, descricao) VALUES ('Monitor', 'Monitor 21p'), ('Lampada', 'Lampada de LED')", Statement.RETURN_GENERATED_KEYS);
         
        //Recuperar os 'ids' inseridos no comando 'execute' do statement
        ResultSet resultSet = statement.getGeneratedKeys();

        while (resultSet.next()) {
            
            int id = resultSet.getInt(1);
            System.out.println(id + " gerado");
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}