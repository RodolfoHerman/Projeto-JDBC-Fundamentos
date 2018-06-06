package br.com.rodolfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.rodolfo.modelo.Categoria;
import br.com.rodolfo.modelo.Produto;
import br.com.rodolfo.repository.CategoriaDAO;

/**
 * TestaCategorias
 */
public class TestaCategorias {

    public static void main(String[] args) throws SQLException {
        
        try(Connection connection = new ConnectionPool().getConnection()) {

            //List<Categoria> categorias = new CategoriaDAO(connection).lista();
            List<Categoria> categorias = new CategoriaDAO(connection).listaComProdutos();

            for (Categoria categoria : categorias) {
                
                System.out.println(categoria);

                //Para cada categoria são buscados os produtos pertencentes à ela. O problema aqui é conhecido como 1 + N, ou seja, será executada 1 query para buscar a 'categoria' e serão executadas mais N queries para os 'produtos'. Esse 1 + N indica a relação da tabela, para contornar esse problema podemos criar um array de produtos dentro de 'categora' indicando essa relação
                // for (Produto produto : new ProdutoDAO(connection).busca(categoria)) {
                    
                //     System.out.println(categoria.getNome() + " - " + produto.getNome());
                // }


                //Resolvido o problema do N + 1
                for (Produto produto : categoria.getProdutos()) {
                    
                    System.out.println(produto.getNome() + " - " + produto.getDescricao());
                }
            }

        }

    }
}