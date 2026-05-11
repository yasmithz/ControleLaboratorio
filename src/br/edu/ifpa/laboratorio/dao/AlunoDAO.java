package br.edu.ifpa.laboratorio.dao;

import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import br.edu.ifpa.laboratorio.model.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AlunoDAO {
    public void cadastrar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, matricula) VALUES (?, ?)";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.executeUpdate();

            System.out.println(" Aluno salvo com sucesso no banco!");

        } catch (SQLException e) {
            System.err.println(" Erro ao cadastrar aluno: " + e.getMessage());
        }
    }
}
