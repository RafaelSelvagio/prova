package controller;

import dao.DisciplinaDAO;
import dao.ProfessorDAO;
import model.Disciplina;
import model.Professor;
import view.ProfessorView;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class ProfessorController {
	private ProfessorDAO professorDAO;
	private ProfessorView professorView;
	private DisciplinaDAO disciplinaDAO;

	public ProfessorController() {
		professorDAO = new ProfessorDAO();
		professorView = new ProfessorView();
		disciplinaDAO = new DisciplinaDAO();
	}

	public void insereProfessor() {
		String[] dados = professorView.insereProfessor();
    	
    	Disciplina disciplina = null;
		try {
			disciplina = disciplinaDAO.buscaDisciplinaPorCodigo(dados[2]);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	
    	java.util.Date dataAdmissao = null;
    	
    	try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dataAdmissao = dateFormat.parse(dados[4]);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use yyyy-MM-dd.");
        }
    	
    	Professor professor = new Professor(Integer.parseInt(dados[0]), dados[1], disciplina, dados[3], dataAdmissao);
    	
        try {
            professorDAO.insereProfessor(professor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public void buscaProfessorPorCodigo() {
		int codigo = professorView.buscaProfessorPorCodigo();

		try {
			professorView.exibeProfessor(professorDAO.buscaProfessorPorCodigo(codigo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void atualizaProfessor() {
		int codigo = professorView.buscaProfessorPorCodigo();
		String[] dados = professorView.atualizaProfessor();

		Disciplina disciplina = null;
		try {
			disciplina = disciplinaDAO.buscaDisciplinaPorCodigo(dados[1]);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		java.util.Date dataAdmissao = null;
    	try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dataAdmissao = dateFormat.parse(dados[3]);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Use yyyy-MM-dd.");
        }

		Professor professorAtualiza = new Professor(codigo, dados[0], disciplina, dados[2], dataAdmissao);

		try {
			professorView.exibeAtualizaProfessor(professorDAO.atualizaProfessor(professorAtualiza));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluiProfessor() {
		int codigo = professorView.buscaProfessorPorCodigo();

		try {
			professorView.exibeExcluiProfessor(professorDAO.excluiProfessor(codigo));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void listaProfessores() {
		try {
			professorView.listaProfessores(professorDAO.listaProfessores());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
