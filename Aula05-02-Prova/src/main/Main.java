package main;

import java.util.Scanner;

import controller.DisciplinaController;
import controller.ProfessorController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DisciplinaController disciplinaController = new DisciplinaController();
		ProfessorController professorController = new ProfessorController();
		
		Scanner scanner = new Scanner(System.in);
		int opcao = -1;

		while (opcao != 0) {

			System.out.println("==== Sistema Escolar ====");
			System.out.println("1. Disciplina");
			System.out.println("2. Professor");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			
			try {

				opcao = scanner.nextInt();
	
				switch (opcao) {
	
				case 1:
					System.out.println("==== Sistema Escolar ====");
					System.out.println("-      Disciplina       ");
					System.out.println("1. Inserir");
					System.out.println("2. Buscar por Sigla");
					System.out.println("3. Listar");
					System.out.println("4. Atualizar");
					System.out.println("5. Excluir");
					System.out.println("9. Voltar");
					System.out.print("Escolha uma opção: ");
					
					try {

						opcao = scanner.nextInt();
						
						switch (opcao) {
						case 1:
							disciplinaController.insereDisciplina();
							break;
							
						case 2:
							disciplinaController.buscaDisciplinaPorCodigo();
							break;
							
						case 3:
							disciplinaController.listaDisciplinas();
							break;
							
						case 4:
							disciplinaController.atualizaDisciplina();
							break;
							
						case 5:
							disciplinaController.excluiDisciplina();
							break;
							
						case 9: 
							
							break;
						}
						
					} catch (java.util.InputMismatchException e) {
						System.out.println("Opção inválida. Por favor, digite um número inteiro válido.");
		                scanner.nextLine(); // Limpar o buffer do scanner
					}
					
					break;
					
				case 2:
					System.out.println("==== Sistema Escolar ====");
					System.out.println("-      Professor       ");
					System.out.println("1. Inserir");
					System.out.println("2. Buscar por Código");
					System.out.println("3. Listar");
					System.out.println("4. Atualizar");
					System.out.println("5. Excluir");
					System.out.println("9. Voltar");
					System.out.print("Escolha uma opção: ");
					
					try {

						opcao = scanner.nextInt();
						
						switch (opcao) {
						case 1:
							professorController.insereProfessor();
							break;
							
						case 2:
							professorController.buscaProfessorPorCodigo();
							break;
							
						case 3:
							professorController.listaProfessores();
							break;
							
						case 4:
							professorController.atualizaProfessor();
							break;
							
						case 5:
							professorController.excluiProfessor();
							break;
							
						case 9: 
							
							break;
						}
						
					} catch (java.util.InputMismatchException e) {
						System.out.println("Opção inválida. Por favor, digite um número inteiro válido.");
		                scanner.nextLine(); // Limpar o buffer do scanner
					}
					
					break;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("Opção inválida. Por favor, digite um número inteiro válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
			}
		}

	}

}
