package view;

import java.util.List;
import java.util.Scanner;

import model.Disciplina;

public class DisciplinaView {
	Scanner scanner = new Scanner(System.in);
	
	public Disciplina adicionaDisciplina() {
		System.out.print("Informe a sigla da disciplina: ");
        String sigla = scanner.nextLine();

        System.out.print("Informe o nome da disciplina: ");
        String nome = scanner.nextLine();

        System.out.print("Informe a ementa da disciplina: ");
        String ementa = scanner.nextLine();

        return new Disciplina(sigla, nome, ementa);
	}
	
	public String buscaDisciplinaPorSigla() {
		System.out.print("Informe a sigla da disciplina: ");
        String sigla = scanner.nextLine();

        return sigla;
	}
	
	public void exibeDisciplina(Disciplina disciplina) {
		if (disciplina != null) {
            System.out.print("Sigla: " + disciplina.getSigla() + " - ");
            System.out.print("Nome: " + disciplina.getNome() + " - ");
            System.out.println("Ementa: " + disciplina.getEmenta());
        } else {
            System.out.println("Disciplina não encontrada.");
        }
	}
	
	public void listaDisciplinas(List<Disciplina> disciplinas) {
		for (Disciplina disciplina : disciplinas) {
			exibeDisciplina(disciplina);
		}
	}
	
	public String[] atualizaDisciplina() {
        String[] dadosAtualizados = new String[2];

        System.out.print("Digite o novo nome da disciplina: ");
        dadosAtualizados[0] = scanner.nextLine();

        System.out.print("Digite a nova ementa da disciplina: ");
        dadosAtualizados[1] = scanner.nextLine();

        return dadosAtualizados;
    }
	
	public void exibeAtualizaDisciplina(boolean atualiza) {
		if (atualiza) {
			System.out.println("Disciplina atualizada.");
		} else {
			System.out.println("Disciplina não atualizada.");
		}
	}
	
	public void exibeExcluiDisciplina(int exclui) {
		if (exclui > 0) {
			System.out.println("Disciplina excluída.");
		} else {
			System.out.println("Disciplina não excluída.");
		}
	}
}
