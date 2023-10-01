package controller;

import dao.DisciplinaDAO;
import model.Disciplina;
import view.DisciplinaView;

import java.sql.SQLException;

public class DisciplinaController {
    private DisciplinaDAO disciplinaDAO;
    private DisciplinaView disciplinaView;

    public DisciplinaController() {
        disciplinaDAO = new DisciplinaDAO();
        disciplinaView = new DisciplinaView();
    }

    public void insereDisciplina() {
    	Disciplina disciplina = disciplinaView.adicionaDisciplina();
    	
        try {
            disciplinaDAO.insereDisciplina(disciplina);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscaDisciplinaPorCodigo() {
    	String sigla = disciplinaView.buscaDisciplinaPorSigla();
    	
        try {
        	disciplinaView.exibeDisciplina(disciplinaDAO.buscaDisciplinaPorCodigo(sigla));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizaDisciplina() {
    	String sigla = disciplinaView.buscaDisciplinaPorSigla();
    	String[] dados = disciplinaView.atualizaDisciplina();
    	
    	Disciplina disciplinaAtualiza = new Disciplina(sigla, dados[0], dados[1]);
    	
        try {
        	disciplinaView.exibeAtualizaDisciplina(disciplinaDAO.atualizaDisciplina(disciplinaAtualiza));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluiDisciplina() {
    	String sigla = disciplinaView.buscaDisciplinaPorSigla();
    	
        try {
        	disciplinaView.exibeExcluiDisciplina(disciplinaDAO.excluiDisciplina(sigla));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listaDisciplinas() {
        try {
            disciplinaView.listaDisciplinas(disciplinaDAO.listaDisciplinas());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
