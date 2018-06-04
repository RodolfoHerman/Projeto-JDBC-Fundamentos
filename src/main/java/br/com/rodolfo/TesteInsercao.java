package br.com.rodolfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TesteInsercao
 */
public class TesteInsercao {

    public static void main(String[] args) throws SQLException {
        
        String nome1 = "Lampada";
        String desc1 = "Lampada LED";
        String nome2 = "Monitor";
        String desc2 = "Monitor 22'";

        Connection connection = DataBase.getConnection();

        //Criado um statement de preparação logo abaixo
        //Statement statement = connection.createStatement();

        //Cria-se a query e os parâmetros do value serão preparadaos
        String sql = "INSERT INTO produtos (nome, descricao) VALUES (?, ?),(?, ?)";

        //Cria-se uma sql preparada para receber parâmetros a serem 'escapados' scpae
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //Setar os parâmetros
        statement.setString(1, nome1);
        statement.setString(2, desc1);
        statement.setString(3, nome2);
        statement.setString(4, desc2);


        //Para recuperar o ID da inserção realizada passamos um segundo argumento 'RETURN_GENERATED_KEYS' no 'execute'
        //boolean result = statement.execute("INSERT INTO produtos (nome, descricao) VALUES ('Monitor', 'Monitor 21p'), ('Lampada', 'Lampada de LED')", Statement.RETURN_GENERATED_KEYS);
         
        //A sql já foi preparada, agora só é necessário executa-la
        boolean result = statement.execute();

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