package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;

public class DisciplinaDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/prova";
    private static final String USUARIO = "root";
    private static final String SENHA = "aluno";
    private Connection conexao;
    
    // Método para abrir uma conexão com o banco de dados
    public void abreConexao() throws SQLException {
        try {
            // Carrega o driver JDBC específico (substitua pelo driver do seu banco de dados)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão com o banco de dados usando os atributos url, usuario e senha
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch (ClassNotFoundException | SQLException e) {
            // Lida com erros de carregamento do driver ou conexão
            throw new SQLException("Erro ao abrir a conexão com o banco de dados", e);
        }
    }
    
    // Método para fechar a conexão com o banco de dados
    public void fechaConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                // Lida com erros de fechamento da conexão
                e.printStackTrace();
            }
        }
    }

    // Método para inserir uma disciplina no banco de dados
    public void insereDisciplina(Disciplina disciplina) throws SQLException {
    	abreConexao();
    	
        String sql = "INSERT INTO disciplina (sigla, nome, ementa) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, disciplina.getSigla());
            preparedStatement.setString(2, disciplina.getNome());
            preparedStatement.setString(3, disciplina.getEmenta());
            preparedStatement.executeUpdate();
        }
        
        fechaConexao();
    }

    // Método para buscar uma disciplina pelo código (sigla) no banco de dados
    public Disciplina buscaDisciplinaPorCodigo(String sigla) throws SQLException {
    	abreConexao();
    	
        String sql = "SELECT * FROM disciplina WHERE sigla = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, sigla);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String ementa = resultSet.getString("ementa");
                return new Disciplina(sigla, nome, ementa);
            }
        }
        
        fechaConexao();
        
        return null; // Retorna null se a disciplina não for encontrada
    }

    // Método para atualizar os dados de uma disciplina no banco de dados
    public boolean atualizaDisciplina(Disciplina disciplina) throws SQLException {
    	abreConexao();
    	
        String sql = "UPDATE disciplina SET nome = ?, ementa = ? WHERE sigla = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, disciplina.getNome());
            preparedStatement.setString(2, disciplina.getEmenta());
            preparedStatement.setString(3, disciplina.getSigla());
            int linhasAfetadas = preparedStatement.executeUpdate();
            
            fechaConexao();
            
            return linhasAfetadas > 0; // Retorna true se pelo menos uma linha foi afetada (atualização bem-sucedida)
        }
    }

    // Método para excluir uma disciplina do banco de dados pelo código (sigla)
    public int excluiDisciplina(String sigla) throws SQLException {
    	abreConexao();
    	
    	int linhasAfetadas = 0;
    	
        String sql = "DELETE FROM disciplina WHERE sigla = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, sigla);
            linhasAfetadas = preparedStatement.executeUpdate();
        }
        
        fechaConexao();
        
        return linhasAfetadas;
    }

    // Método para listar todas as disciplinas no banco de dados
    public List<Disciplina> listaDisciplinas() throws SQLException {
    	abreConexao();
    	
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT * FROM disciplina";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String sigla = resultSet.getString("sigla");
                String nome = resultSet.getString("nome");
                String ementa = resultSet.getString("ementa");
                disciplinas.add(new Disciplina(sigla, nome, ementa));
            }
        }
        
        fechaConexao();
        
        return disciplinas;
    }
}

