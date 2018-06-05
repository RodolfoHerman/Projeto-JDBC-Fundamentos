package br.com.rodolfo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.rodolfo.modelo.Produto;
import br.com.rodolfo.repository.ProdutoDAO;

/**
 * TesteDAOProduto
 */
public class TesteDAOProduto {

    public static void main(String[] args) throws SQLException {
        
        Produto produto = new Produto("Mesa", "Mesa redonda");

        //Colocar no TRY evita a preocupação do programador em fechar a Connection, fazendo com que o JAVA realize o close
        try(Connection connection = new ConnectionPool().getConnection()) {

            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            
            //utilizar o DAO para salvar o produto
            System.out.println(produtoDAO.salvar(produto));

            //Utilizar o DAO para listar os produtos
            for (Produto prod : produtoDAO.listar()) {
                
                System.out.println(prod);
            }
        }
    }
    
}