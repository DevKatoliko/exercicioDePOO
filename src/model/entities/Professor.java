package model.entities;

import model.entities.components.Disciplina;

public class Professor extends Pessoa{
	private Disciplina disciplina;

	public Professor(String nome, Integer idade, char sexo, Disciplina disciplina) {
		super(nome, idade, sexo);
		this.disciplina = disciplina;
	}


	
	public Disciplina getDisciplina() {
		return disciplina;
	}



	@Override
	public String showInfo() {
		return "Nome: " + getNome()
		+"\nIdade: " + getIdade()
		+"\nSexo: " + getSexo()
		+"\nLeciona: " + getDisciplina().getNome();
	}

}
