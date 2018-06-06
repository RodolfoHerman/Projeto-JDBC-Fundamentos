package br.com.rodolfo.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Categoria
 */
public class Categoria {

    private Integer id;
	private String nome;
	//Associação de 1 para N
	private List<Produto> produtos = new ArrayList<>();

	public Categoria(Integer id, String nome) {

		this.id = id;
		this.nome = nome;
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return this.nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public List<Produto> getProdutos()
	{
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos)
	{
		this.produtos = produtos;
	}

	public void adiciona(Produto produto) {
		this.produtos.add(produto);
	}

	@Override
	public String toString() {
		return String.format("[categoria: %d %s]", this.id, this.nome);
	}

}