package br.com.rodolfo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rodolfo.modelo.Categoria;
import br.com.rodolfo.modelo.Produto;

/**
 * CategoriaDAO
 */
public class CategoriaDAO {

    private final Connection con;

    public CategoriaDAO(Connection connection) {

        this.con = connection;
    }

    public List<Categoria> lista() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try(PreparedStatement statement = this.con.prepareStatement(sql)) {

            statement.execute();

            try(ResultSet result = statement.getResultSet()) {

                while(result.next()) {

                    Integer id = result.getInt("id");
                    String nome = result.getString("nome");

                    categorias.add(new Categoria(id, nome));
                }
            }
        }

        return categorias;
    }

    public List<Categoria> listaComProdutos() throws SQLException {
        
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT c.id AS categoriaId, c.nome AS categoriaNome, p.id AS produtoId, p.nome AS produtoNome, p.descricao AS produtoDesc FROM categoria c INNER JOIN produtos p ON (p.id_categoria = c.id) ORDER BY categoriaNome";

        Categoria ultima = null;

        try(PreparedStatement statement = this.con.prepareStatement(sql)) {

            statement.execute();

            try(ResultSet result = statement.getResultSet()) {

                while(result.next()) {
                
                    int categoriaId = result.getInt("categoriaId");
                    String categoriaNome = result.getString("categoriaNome");

                    if(ultima == null || !ultima.getNome().equals(categoriaNome)) {
                        
                        Categoria categoria = new Categoria(categoriaId, categoriaNome);
                        categorias.add(categoria);

                        ultima = categoria;
                    }

                    int produtoId = result.getInt("produtoId");
                    String produtoNome = result.getString("produtoNome");
                    String pdodutoDesc = result.getString("produtoDesc");

                    ultima.adiciona(new Produto(produtoId, produtoNome, pdodutoDesc));
                }
            }

        }

        return categorias;
    }

}