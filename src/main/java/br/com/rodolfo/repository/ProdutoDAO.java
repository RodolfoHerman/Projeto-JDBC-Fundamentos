package br.com.rodolfo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.rodolfo.modelo.Categoria;
import br.com.rodolfo.modelo.Produto;

/**
 * ProdutoDAO
 */
//O DAO é um padrão de design que utilizamos para isolar o código SQL. Ao adotá-lo, sabemos que existe um único grupo de classes que trabalha com um sistema externo de dados,
public class ProdutoDAO {

    private final Connection con;

    public ProdutoDAO(Connection connection) {

        this.con = connection;
    }

    public Integer salvar(Produto produto) throws SQLException {

        String sql      = "INSERT INTO produtos (nome, descricao) VALUES(? ,?)";
        Integer resp    = -1;

        //Colocar no TRY evita a preocupação do programador em fechar o PreparedStatement, fazendo com que o JAVA realize o close
        try(PreparedStatement statement = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.execute();

            ResultSet result = statement.getGeneratedKeys();

            if(result.next()) {

                resp = result.getInt(1);
            }
        }

        return resp;
    }

    public List<Produto> listar() throws SQLException {

        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<>();

        try(PreparedStatement statement = this.con.prepareStatement(sql)) {

            resultadoEmProdutos(statement, produtos);
        }

        return produtos;
    }


    public List<Produto> busca(Categoria categoria) throws SQLException {
        
        String sql = "SELECT * FROM produtos WHERE id_categoria = ? ";
        List<Produto> produtos = new ArrayList<>();

        try(PreparedStatement statement = this.con.prepareStatement(sql)) {

            statement.setInt(1, categoria.getId());
        
            resultadoEmProdutos(statement, produtos);        
        }

        return produtos;
    }


    private void resultadoEmProdutos(PreparedStatement statement, List<Produto> produtos) throws SQLException {

        statement.execute();

        try(ResultSet result = statement.getResultSet()) {

            while (result.next()) {
                
                Integer id = result.getInt("id");
                String nome = result.getString("nome");
                String desc = result.getString("descricao");

                produtos.add(new Produto(id, nome, desc));
            }
        }
    }


}