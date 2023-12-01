package mainProgram;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.entities.Estudante;
import model.entities.Pessoa;
import model.entities.Professor;
import model.entities.components.Disciplina;
import model.entities.components.Turma;
import model.entities.exception.SchoolException;
import model.entities.exception.SchoolSystemException;
import model.entities.school.School;

public class MainProgram {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		School escola = new School();
		List<Pessoa> pessoasNasDisciplinas = new ArrayList();
		Integer opc = -1;
		String opc2 = " ";
		do {
			try {
				System.out.println("O que deseja cadastrar?\n1 - Disciplina\n2 - Estudante\n3 - Professor\n0 - Sair");
				opc = input.nextInt();
				if(opc > 3 || opc < 0) {
					System.out.println("Opção inválida\n");
					input.nextLine();
					continue;
				}
				if(opc == 0) {
					break;
				}
				String nick = " ";
				if (escola.getDisciplinas().size() == 0 && opc != 1) {
					System.out.println("É necessário cadastrar no mínimo uma disciplina");
				}
				if (opc == 1 || escola.getDisciplinas().size() == 0) {
					System.out.print("Quantas matérias deseja criar? ");
					int nMaterias = input.nextInt();
					for (int i = 0; i < nMaterias; i++) {
						System.out.print("Nome da matéria: ");
						input.nextLine();
						String nome = input.nextLine();
						System.out.print("Código da matéria: ");
						int codigo = input.nextInt();
						if (escola.verificaDisciplina(nome, codigo) == false) {
							do {
								System.out.print("Disciplina já existe.Cadestre uma nova disciplina");
								System.out.print("\nNome da matéria: ");
								input.nextLine();
								nome = input.nextLine();
								System.out.print("Código da matéria: ");
								codigo = input.nextInt();
							} while (escola.verificaDisciplina(nome, codigo) == false);
						}
						Disciplina disciplina = new Disciplina(nome, codigo);
						try {
							escola.adicionarDisciplina(disciplina);
						} catch (SchoolSystemException esc) {
							System.out.println("Erro: " + esc.getMessage());
							continue;
						}
					}
				}
				if (opc == 1) {
					continue;
				}

				if (opc == 2) {
					System.out.print("Quantos estudantes deseja matricular? ");
					nick = "estudante";
				}
				if (opc == 3) {
					System.out.print("Quantos professores deseja cadastrar?");
					nick = "professor";
				}

				int n = input.nextInt();
				input.nextLine();
				for (int i = 0; i < n; i++) {
					System.out.println("Digite os dados do " + nick + ": ");
					System.out.print("Nome do " + nick + ": ");
					String nome = input.nextLine();
					System.out.print("Idade do " + nick + ": ");
					Integer idade = input.nextInt();
					System.out.print("Sexo do " + nick + ": ");
					String sexo = input.next();
					if (!sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F")) {
						do {
							System.out.print("Sexo inválido\nInforme o sexo (M/F)");
							sexo = input.next();
						} while (!sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F"));
					}
					char sx = sexo.charAt(0);
					if (opc == 2) {
						System.out.print("Matricula do estudante: ");
						int matricula = input.nextInt();
						input.nextLine();
						if (escola.verificaMatricula(matricula) == false) {
							do {
								System.out.print("Número de matricula já existe!\nDigite outro:");
								matricula = input.nextInt();
								input.nextLine();
							} while (escola.verificaMatricula(matricula) == false);
						}
						Estudante estudante = new Estudante(nome, idade, sx, matricula);
						try {
							escola.adicionarEstudante(estudante);
						} catch (SchoolSystemException esc) {
							System.out.println("Erro: " + esc.getMessage());
							continue;
						}
					} else if (opc == 3) {
						System.out.print(
								"Qual a disciplina que o professor irá lecionar(Digite o nome de uma das opções listadas):");
						for (Disciplina d : escola.getDisciplinas()) {
							System.out.println("\n" + d.getNome());
						}
						input.nextLine();
						String disciplina = input.nextLine();
						Disciplina lecionar = escola.disciplinaExiste(disciplina);
						if (lecionar.getNome() == null) {
							do {
								System.out.println("Disciplina " + disciplina
										+ " não existe\nEscolha e digite uma das opções listadas:");
								for (Disciplina d : escola.getDisciplinas()) {
									System.out.println(d.getNome() + "\n");
								}
								disciplina = input.nextLine();
								lecionar = escola.disciplinaExiste(disciplina);
							} while (lecionar.getNome() == null);
						}
						Professor professor = new Professor(nome, idade, sx, lecionar);
						try {
							escola.adicionarProfessor(professor);
						} catch (SchoolSystemException e) {
							System.out.println("Erro: " + e.getMessage());
						}catch(SchoolException e) {
							System.out.println("Erro: " + e.getMessage());				
							continue;
						}
						
					}
				}
				
			} catch (InputMismatchException e) {
				System.out.print("Erro: Você digitou um caractere, digite um número!\n");
				input.nextLine();
				continue;
			}
		} while (opc != 0);
		input.nextLine();
		System.out.println();
		if (escola.getDisciplinas().size() > 0 && escola.getEstudantes().size() > 0) {
			do {
				System.out.println(
						"Matricula de estudantes nas disciplinas:\nQual estudante deseja matricular nas disciplinas?");
				for (Estudante e : escola.getEstudantes()) {
					System.out.println(e.getNome());
					System.out.println("--------------");
				}
	
				String nomeEstudante = input.nextLine();
				Estudante estudante = escola.estudanteGetByName(nomeEstudante);

				if (estudante.getNome() == null) {
					do {
						System.out.print("Estudante não matriculado.Tente novamente por favor:");
						nomeEstudante = input.nextLine();
						estudante = escola.estudanteGetByName(nomeEstudante);
					} while (estudante.getNome() == null);
				}

				System.out.println();
				System.out.println("Em qual matéria gostaria de matricular os estudantes " + estudante.getNome());
				for (Disciplina d : escola.getDisciplinas()) {
					System.out.println(d.getNome() + "\n");
				}
				String disciplinaEstudante = input.nextLine();
				if (escola.disciplinaGetByName(disciplinaEstudante).getNome() == null) {
					System.out.println(
							"Disciplina não cadastrada. Por favor tente novamente e digite uma das disciplinas listadas:");
					do {
						for (Disciplina d : escola.getDisciplinas()) {
							System.out.println(d.getNome() + "\n");
						}
						disciplinaEstudante = input.nextLine();
						escola.disciplinaGetByName(disciplinaEstudante);
					} while (escola.disciplinaGetByName(disciplinaEstudante).getNome() == null);
					try {
						escola.disciplinaGetByName(disciplinaEstudante).matricularEstudante(estudante);
					} catch (SchoolSystemException e) {
						System.out.println("Erro: " + e.getMessage());
						continue;
					}
				}
				try {
					escola.disciplinaGetByName(disciplinaEstudante).matricularEstudante(estudante);
				
				System.out.println(
						"Cadastro realizado com sucesso!\nDeseja realizar mais um cadastro de alunos em alguma disciplina?\nSim(s) Não(n)");
				opc2 = input.next();
				} catch (SchoolSystemException e) {
					System.out.println("Erro: " + e.getMessage());				
					System.out.println(
							"\nDeseja realizar mais um cadastro de alunos em alguma disciplina?\nSim(s) Não(n)");
					opc2 = input.next();
				}
				if (!opc2.equalsIgnoreCase("n") && !opc2.equalsIgnoreCase("s")) {
					System.out.print(
							"Opção inválida.\nDeseja realizar mais um cadastro? Digite: S para sim ou N para não");
					opc2 = input.next();
				}

			} while (!opc2.equalsIgnoreCase("n"));
		}
		Professor professor;
		for (Professor p : escola.getProfessores()) {
			professor = p;
			Turma turma = new Turma(professor, professor.getDisciplina());
			escola.adicionarTurma(turma);
		}
		System.out.println();
		int soma = 0;
		System.out.println("Pessoas cadastradas na escola: ");
		for (Pessoa p : escola.getPessoas()) {
			for (int i = 0; i < escola.getPessoas().size(); i++) {

				if (escola.getEstudantes().size() - 1 >= i) {
					if (p == escola.getEstudantes().get(i)) {
						System.out.println("\nEstudante:");
						System.out.println(escola.getEstudantes().get(i).showInfo());
						soma += 1;
					}
				}

				if (escola.getProfessores().size() - 1 >= i ) {
					if (p == escola.getProfessores().get(i)) {
						System.out.println("\nProfessor:");
						System.out.println(escola.getProfessores().get(i).showInfo());
						soma += 1;
					}
				}
			}
		}
		System.out.println("\nSão " + soma + " pessoas cadastradas na escola.");

		input.close();

	}

}
