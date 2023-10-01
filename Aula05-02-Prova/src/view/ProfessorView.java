package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import model.Professor;

public class ProfessorView {
    Scanner scanner = new Scanner(System.in);

    public String[] insereProfessor() {
    	System.out.print("Informe o código do professor: ");
        String codigo = scanner.nextLine();
        
        System.out.print("Informe o nome do professor: ");
        String nome = scanner.nextLine();
        
        System.out.print("Informe a sigla da disciplina: ");
        String sigla_disciplina = scanner.nextLine();

        System.out.print("Informe a especialidade do professor: ");
        String especialidade = scanner.nextLine();
        
        System.out.print("Informe a data de admissão do professor (no formato yyyy-MM-dd): ");
        String dataAdmissaoStr = scanner.nextLine();
        
        String[] dados = {codigo, nome, sigla_disciplina, especialidade, dataAdmissaoStr};
        
        return dados;
    }

    public int buscaProfessorPorCodigo() {
        System.out.print("Informe o código do professor: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void exibeProfessor(Professor professor) {
        if (professor != null) {
            System.out.print("Código: " + professor.getCodigo() + " - ");
            System.out.print("Nome: " + professor.getNome() + " - ");
            System.out.print("Disciplina: " + professor.getDisciplina().getSigla() + " - " + professor.getDisciplina().getNome() + " - ");
            System.out.print("Especialidade: " + professor.getEspecialidade() + " - ");
            System.out.println("Data de Admissão: " + professor.getDataAdmissao());
        } else {
            System.out.println("Professor não encontrado.");
        }
    }

    public void listaProfessores(List<Professor> professores) {
        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            for (Professor professor : professores) {
            	exibeProfessor(professor);
            }
        }
    }

    public String[] atualizaProfessor() {
        System.out.print("Informe o nome do professor: ");
        String nome = scanner.nextLine();
        
        System.out.print("Informe a sigla da disciplina: ");
        String sigla_disciplina = scanner.nextLine();

        System.out.print("Informe a especialidade do professor: ");
        String especialidade = scanner.nextLine();
        
        System.out.print("Informe a data de admissão do professor (no formato yyyy-MM-dd): ");
        String dataAdmissaoStr = scanner.nextLine();
        
        String[] dados = {nome, sigla_disciplina, especialidade, dataAdmissaoStr};

        return dados;
    }

    public void exibeAtualizaProfessor(boolean atualiza) {
        if (atualiza) {
            System.out.println("Professor atualizado.");
        } else {
            System.out.println("Professor não atualizado.");
        }
    }

    public void exibeExcluiProfessor(int exclui) {
        if (exclui > 0) {
            System.out.println("Professor excluído.");
        } else {
            System.out.println("Professor não excluído.");
        }
    }
}
