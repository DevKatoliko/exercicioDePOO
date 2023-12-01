package model.entities.components;

import model.entities.Professor;
import model.entities.exception.SchoolException;

public class Turma {
	private Professor professor;
	private Disciplina disciplina;

	public Turma(Professor professor, Disciplina disciplina) {
		if (professor.getDisciplina() != disciplina) {
			throw new SchoolException(professor.getNome()+" não leciona " + disciplina.getNome());
		}
			this.professor = professor;
			this.disciplina = disciplina;
		
	}

	public Professor getProfessor() {
		return professor;
	}


	public Disciplina getDisciplina() {
		return disciplina;
	}


}
