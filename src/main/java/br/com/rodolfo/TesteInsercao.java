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

        //Responsabiliza o JAVA para abrir a conexão e fecha-la quando bloco do TRY terminar, evitando a preocupação do programador em fechar o STATEMENT quando o bloco terminar com ou sem ERROR
        try(Connection connection = new ConnectionPool().getConnection()) {

            //Desativar o auto commit
            connection.setAutoCommit(false);

            //Criado um statement de preparação logo abaixo
            //Statement statement = connection.createStatement();

            //Cria-se a query e os parâmetros do value serão preparadaos
            String sql = "INSERT INTO produtos (nome, descricao) VALUES (?, ?)";

            //Cria-se uma sql preparada para receber parâmetros a serem 'escapados' scpae. Responsabiliza o JAVA para criar o STATEMENT e fecha-lo ao termino do bloco TRY evitando a preocupação do programador em fechar o STATEMENT quando o bloco terminar com ou sem ERROR
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

                //Para recuperar o ID da inserção realizada passamos um segundo argumento 'RETURN_GENERATED_KEYS' no 'execute'
                //boolean result = statement.execute("INSERT INTO produtos (nome, descricao) VALUES ('Monitor', 'Monitor 21p'), ('Lampada', 'Lampada de LED')", Statement.RETURN_GENERATED_KEYS);

                adiciona(nome1, desc1, statement);
                adiciona(nome2, desc2, statement);

                //realizar o commit do primeiro produto
                connection.commit();

            } catch (Exception e) {
                
                e.printStackTrace();
                connection.rollback();
                System.out.println("Rollback efetuado");
            }
        }

        //Podemos remover o close porque o TRY da conexão ao ser terminado o JAVA é inteligente para fechar a conexão, evitando a preocupação do programador em fechar a conexão.
        //connection.close();

    }

    public static void adiciona(String nome, String desc, PreparedStatement statement) throws SQLException {
        
        //Ocasionar um erro para não realizar commit no banco caso acontece erro de alguma inserção
        if(nome.equals("Monitor")) {

            throw new IllegalArgumentException("Problema ocorrido");
        }

        //Setar os parâmetros
        statement.setString(1, nome);
        statement.setString(2, desc);

        //A sql já foi preparada, agora só é necessário executa-la
        boolean result = statement.execute();

        //Recuperar os 'ids' inseridos no comando 'execute' do statement
        ResultSet resultSet = statement.getGeneratedKeys();

        while (resultSet.next()) {
            
            int id = resultSet.getInt(1);
            System.out.println(id + " gerado");
        }

        resultSet.close();
    }

}