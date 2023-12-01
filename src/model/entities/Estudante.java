package model.entities;

public class Estudante extends Pessoa{
	private int matricula;
	
	
	public Estudante() {
		
	}
	public Estudante(String nome,Integer idade ,char sexo, int matricula) {
		super(nome,idade,sexo);
		this.matricula = matricula;
	}



	public Integer getMatricula() {
		return matricula;
	}


	@Override
	public String showInfo() {
		return  "Nome: " + getNome()
				+"\nIdade: " + getIdade()
				+"\nSexo: " + getSexo()
				+"\nMatrícula: " + getMatricula();
	}

	
}
