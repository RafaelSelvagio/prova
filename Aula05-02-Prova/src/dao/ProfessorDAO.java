package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;
import model.Professor;

public class ProfessorDAO {
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

    // Método para inserir um professor no banco de dados
    public void insereProfessor(Professor professor) throws SQLException {
        abreConexao();
        
        String sql = "INSERT INTO professor (codigo, nome, codigo_disciplina, especialidade, data_admissao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, professor.getCodigo());
            preparedStatement.setString(2, professor.getNome());
            preparedStatement.setString(3, professor.getDisciplina().getSigla());
            preparedStatement.setString(4, professor.getEspecialidade());
            
            java.sql.Date sqlDate = new java.sql.Date(professor.getDataAdmissao().getTime());
            preparedStatement.setDate(5, sqlDate);
            
            preparedStatement.executeUpdate();
        }
        
        fechaConexao();
    }

    // Método para buscar um professor pelo código no banco de dados
    public Professor buscaProfessorPorCodigo(int codigo) throws SQLException {
        abreConexao();
        
        String sql = "SELECT * FROM professor WHERE codigo = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String codigoDisciplina = resultSet.getString("codigo_disciplina");
                String especialidade = resultSet.getString("especialidade");
                java.sql.Date dataAdmissao = resultSet.getDate("data_admissao");

                // Agora, você precisa buscar a disciplina com base no código
                DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                Disciplina disciplina = disciplinaDAO.buscaDisciplinaPorCodigo(codigoDisciplina);

                return new Professor(codigo, nome, disciplina, especialidade, dataAdmissao);
            }
        }
        
        fechaConexao();
        
        return null; // Retorna null se o professor não for encontrado
    }


    // Método para atualizar os dados de um professor no banco de dados
    public boolean atualizaProfessor(Professor professor) throws SQLException {
        abreConexao();
        
        String sql = "UPDATE professor SET nome = ?, codigo_disciplina = ?, especialidade = ?, data_admissao = ? WHERE codigo = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setString(1, professor.getNome());
            preparedStatement.setString(2, professor.getDisciplina().getSigla());
            preparedStatement.setString(3, professor.getEspecialidade());
            preparedStatement.setDate(4, new java.sql.Date(professor.getDataAdmissao().getTime()));
            preparedStatement.setInt(5, professor.getCodigo());
            
            int linhasAfetadas = preparedStatement.executeUpdate();
            
            fechaConexao();
            
            return linhasAfetadas > 0; // Retorna true se pelo menos uma linha foi afetada (atualização bem-sucedida)
        }
    }

    // Método para excluir um professor pelo código no banco de dados
    public int excluiProfessor(int codigo) throws SQLException {
        abreConexao();
        int linhasAfetadas = 0;
        
        String sql = "DELETE FROM professor WHERE codigo = ?";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            preparedStatement.setInt(1, codigo);
            linhasAfetadas = preparedStatement.executeUpdate();
        }
        
        fechaConexao();
        
        return linhasAfetadas;
    }

    // Método para listar todos os professores no banco de dados
    public List<Professor> listaProfessores() throws SQLException {
        abreConexao();
        
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor";
        try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo");
                String nome = resultSet.getString("nome");
                String codigoDisciplina = resultSet.getString("codigo_disciplina");
                String especialidade = resultSet.getString("especialidade");
                java.sql.Date dataAdmissao = resultSet.getDate("data_admissao");
                
                DisciplinaDAO disciplinaDAO = new DisciplinaDAO(); 
                
                Disciplina disciplina = disciplinaDAO.buscaDisciplinaPorCodigo(codigoDisciplina);

                professores.add(new Professor(codigo, nome, disciplina, especialidade, dataAdmissao));
            }
        }
        
        fechaConexao();
        
        return professores;
    }

}
