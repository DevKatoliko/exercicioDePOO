package model.entities;

public abstract class Pessoa {
	private String nome;
	private Integer idade;
	private char sexo;
	
	public Pessoa(String nome, Integer idade, char sexo) {
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
	}

	public Pessoa() {
		
	}

	public String getNome() {
		return nome;
	}


	public Integer getIdade() {
		return idade;
	}



	public char getSexo() {
		return sexo;
	}


	public abstract String showInfo();
	
}
