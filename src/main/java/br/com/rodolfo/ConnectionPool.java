package br.com.rodolfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;


/**
 * ConnectionPool
 */
public class ConnectionPool {

    //Se abrirmos e fecharmos uma nova conexão para cada novo usuário/requisição? O custo de abrir e fechar uma conexão é alto: é necessário enviar e receber dados de autenticação via TCP para o banco que é remoto. Além disso, se o número de usuários cresce muito, não teremos conexões suficientes no banco para responder pelo desejo dos usuários. Para solucionar esse problema, temos um pool de conexões, de onde virão os objetos para atender os requerimentos de cada cliente. A medida que os clientes terminam sua requisição, os objetos são devolvidos para esse pool. O pool tem um limite máximo de conexões abertas que atendem as requisições dos clientes.


    //Utilizamos a interface DataSource pois ela só disponibiliza os getters, não os setters.
    private DataSource dataSource;

    public ConnectionPool() {
        //Deixar de criar uma Connection nova a cada requisição e passar a usar um pool
        MysqlConnectionPoolDataSource pool = new MysqlConnectionPoolDataSource();
        
        final String BD_URL = "jdbc:mysql://127.0.0.1/lista?useSSL=false";
        
        pool.setUrl(BD_URL);
        pool.setUser("root");
        pool.setPassword("");

        this.dataSource = pool;
    }
    
    public Connection getConnection() throws SQLException {
        
        //Necessário para 'mysql-connector' maior que a versão 6.0'
		//Class.forName("com.mysql.cj.jdbc.Driver");
        //final String BD_URL = "jdbc:mysql://127.0.0.1/lista?useTimezone=true&serverTimezone=UTC&useSSL=false";
        final String BD_URL = "jdbc:mysql://127.0.0.1/lista?useSSL=false";
		
		//gerenciador de conexão escolhe qual estratégia adequada para a comunicação com o banco de dados
		//Connection connection = DriverManager.getConnection(BD_URL, "root", "root");
        Connection connection = DriverManager.getConnection(BD_URL, "root", "");

        //return connection;

        //Utilizando o pool de conexão do MySql
        return this.dataSource.getConnection();
    }

    
}