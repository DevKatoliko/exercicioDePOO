package model.entities.components;

import java.util.ArrayList;
import java.util.List;

import model.entities.Estudante;
import model.entities.Professor;
import model.entities.exception.SchoolSystemException;

public class Disciplina {
	private String nome;
	private int codigo;

	private List<Estudante> estudantes = new ArrayList();;
	public Disciplina() {
		
	}
	public Disciplina(String nome, int codigo) {
		this.nome = nome;
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<Estudante> getEstudantes() {
		return estudantes;
	}
	
	private boolean matriculaValida(Estudante estudante) {
		boolean valida = true;
	if(estudantes.contains(estudante)) {
		valida = false;
	}
		return valida;
	}
	
	public void matricularEstudante(Estudante estudante) throws SchoolSystemException {
		if(estudantes.size() > 10) {
			throw new SchoolSystemException("Não há mais vagas disponíveis para essa disciplina");
		}
		if(matriculaValida(estudante) == false) {
			throw new SchoolSystemException( estudante.getNome() + " já está matriculado nessa disciplina");
		}
		estudantes.add(estudante);
	}
	
	public void alunosMatriculados() {
		for(Estudante e : estudantes) {
			System.out.println(e.showInfo()+"\n");
		}
	}
	
	@Override
	public String toString() {
		return "Disciplina: " + getNome()
				+"\nCodigo: " + getCodigo();
	}
	

}
