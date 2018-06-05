package br.com.rodolfo.modelo;

/**
 * Produto
 */
public class Produto {

    private Integer id;
    private String nome;
    private String descricao;

	public Produto(Integer id, String nome, String descricao) {

		this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Produto(String nome, String descricao) {

        this.nome = nome;
        this.descricao = descricao;
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

	public String getDescricao()
	{
		return this.descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
    }
    
    @Override
    public String toString() {
        return String.format("[produto: %d %s %s]", this.id, this.nome, this.descricao);
    }
    
}