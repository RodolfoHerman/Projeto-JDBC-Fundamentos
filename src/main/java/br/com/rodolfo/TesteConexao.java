package br.com.rodolfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TesteConexao
 */
public class TesteConexao {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //Necessário para 'mysql-connector' maior que a versão 6.0'
		//Class.forName("com.mysql.cj.jdbc.Driver");
		final String BD_URL = "jdbc:mysql://127.0.0.1/lista?useTimezone=true&serverTimezone=UTC&useSSL=false";
		
		//gerenciador de conexão escolhe qual estratégia adequada para a comunicação com o banco de dados
		Connection connection = DriverManager.getConnection(BD_URL, "root", "root");
		
		//Necessário criar um Statement para realizar a execução SQL
		Statement stmt = connection.createStatement();
		//Caso a SQL executada traga um conjunto de resultados então será TRUE
		boolean result = stmt.execute("select * from produtos");
		System.out.println("O resultado foi : " + result);
		
		//Para acessar o conjunto de dados resultante da query
		ResultSet resultSet = stmt.getResultSet();
		
		//Para percorrer os dados que vieram do banco de dados
		while(resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String nome = resultSet.getString("nome");
			String descricao = resultSet.getString("descricao");
			
			System.out.println(id);
			System.out.println(nome);
			System.out.println(descricao);
		}
		
		//Tudo que foi aberto deve ser fechado
		resultSet.close();
		stmt.close();
		
		
		//Todda conexão deve ser fechada
		connection.close();



    }
}