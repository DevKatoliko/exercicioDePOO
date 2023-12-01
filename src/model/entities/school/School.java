package model.entities.school;

import java.util.ArrayList;
import java.util.List;

import model.entities.Estudante;
import model.entities.Pessoa;
import model.entities.Professor;
import model.entities.components.Disciplina;
import model.entities.components.Turma;
import model.entities.exception.SchoolException;
import model.entities.exception.SchoolSystemException;

public class School {
	private List<Turma> turmas = new ArrayList();
	private List<Disciplina> disciplinas = new ArrayList();
	private List<Estudante> estudantes = new ArrayList();
	private List<Professor> professores = new ArrayList();
	private List<Pessoa> pessoas = new ArrayList();
	public School () {
		
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	
	public List<Estudante> getEstudantes() {
		return estudantes;
	}

	public List<Professor> getProfessores() {
		return professores;
	}
	

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void adicionarTurma(Turma turma)  {
		if(turmas.contains(turma)) {
			throw new SchoolException("Turma de " + turma.getDisciplina().getNome()+ " já existente");
		}
		turmas.add(turma);
	}
	
	public void adicionarDisciplina(Disciplina disciplina) throws SchoolSystemException {
		if(disciplinas.contains(disciplina)) {
			throw new SchoolSystemException("Disciplina " + disciplina.getNome()+ " já existente");
		}
		disciplinas.add(disciplina);
	}
	
	public void adicionarEstudante(Estudante estudante) throws SchoolSystemException {
		if(!verificaPessoa(estudante)) {
			throw new SchoolSystemException("Estudante " + estudante.getNome()+ " já matriculado");
		}
		estudantes.add(estudante);
		pessoas.add(estudante);
	}
	
	public void adicionarProfessor(Professor professor) throws SchoolSystemException {
		if(!verificaPessoa(professor)) {
			throw new SchoolSystemException("Professor " + professor.getNome()+ " já cadastrado");
		}
		if(verificaLecionario(professor) == false) {
			throw new SchoolException ("Já existe um professor lecionando essa disciplina!\nCadastre uma nova disciplina");
		}
		professores.add(professor);
		pessoas.add(professor);
	}
	
	public Estudante estudanteGetByName(String nome) {
		Estudante estudante = new Estudante();
		for(Estudante e : estudantes) {
			if(nome.equalsIgnoreCase(e.getNome())) {
				estudante = e;
			}
		}
		return estudante;
	}
	
	public Disciplina disciplinaGetByName(String nome) {
		Disciplina disciplina = new Disciplina();
		for (Disciplina d : disciplinas) {
			if(nome.equalsIgnoreCase(d.getNome())){
				disciplina = d;
			}
		}
		return disciplina;
	}
	
	public boolean verificaPessoa (Pessoa pessoa) {
		boolean value = true;
		for(Pessoa p : pessoas) {
			if(estudantes.contains(pessoa)) {
				value = false;
				break;
			}
			else if(professores.contains(pessoa)) {
				value = false;
				break;
			}
		}
		return value;
	}
	
	public boolean verificaMatricula(int matricula) {
		boolean value = true;
		for(Estudante e : estudantes) {
			if(e.getMatricula() == matricula) {
				value = false;
				break;
			}
		}
		return value;
	}
	
	public boolean verificaDisciplina(String nome , int codigo) {
		boolean value = true;
		for(Disciplina d : disciplinas) {
			if(d.getCodigo() == codigo) {
				value = false;
				break;
			}
			else if(d.getNome().equalsIgnoreCase(nome)) {
				value = false;
				break;
			}
		}
		return value;
	}
	
	public boolean verificaLecionario(Professor professor) {
		boolean value = true;
		for(Professor p : professores) {
			if (p.getDisciplina() == professor.getDisciplina()) {
				value = false;
			}
		}
		return value;
	}
	public Disciplina disciplinaExiste(String nomeDisciplina) {
		Disciplina disciplina = new Disciplina();
		for(Disciplina d : disciplinas) {
			if(d.getNome().equalsIgnoreCase(nomeDisciplina)) {
				disciplina = d;
			}
		}
		return disciplina;
	} 
	

}
