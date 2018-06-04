package br.com.rodolfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TesteConexao
 */
public class TesteConexao {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		//Instanciamos o pool de conexão
		ConnectionPool dataBase = new ConnectionPool();
		
		//Simulção de várias requisições ao servidor com utilização do POOL
		for (int i = 0; i < 100; i++) {
			
			//Refatoração da classe
			Connection connection = dataBase.getConnection();

			//Necessário criar um Statement para realizar a execução SQL
			Statement stmt = connection.createStatement();
			//Caso a SQL executada traga um conjunto de resultados (SELECT) então será TRUE caso contrário é FALSE (INSERT, DELETE)
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
			
			
			//Todda conexão deve ser fechada. Usando o POOL de conexão, quando uma conexão é fechada, a conexão é devolvida para o pool de conexões o próximo getConnection() pede uma conexão do pool e recebe a conexão anterior
			connection.close();
		}
    }
}