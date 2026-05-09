package br.edu.ifpa.laboratorio.dao;
import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import java.sql.*;
import java.time.LocalDate;

public class EmprestimoDAO {

    public void realizarEmprestimo(int idAluno, int idEquipamento) throws SQLException {
        // Regra 7.1 e 7.4: Verificar se equipamento existe e está disponível
        String sqlCheck = "SELECT disponivel FROM equipamento WHERE id = ?";

        try (Connection conn = ConexaoMySQL.getConnection()) {
            try (PreparedStatement stmtCheck = conn.prepareStatement(sqlCheck)) {
                stmtCheck.setInt(1, idEquipamento);
                ResultSet rs = stmtCheck.executeQuery();

                if (!rs.next()) {
                    System.out.println("ERRO: Equipamento inexistente!");
                    return;
                }
                if (!rs.getBoolean("disponivel")) {
                    System.out.println("ERRO: Equipamento indisponível para empréstimo!");
                    return;
                }
            }

            // Inicia Transação para garantir Regra 7.2
            conn.setAutoCommit(false);
            try {
                // 1. Inserir empréstimo
                String sqlIns = "INSERT INTO emprestimo (id_aluno, id_equipamento, data_emprestimo, status) VALUES (?, ?, ?, 'ATIVO')";
                PreparedStatement stmtIns = conn.prepareStatement(sqlIns);
                stmtIns.setInt(1, idAluno);
                stmtIns.setInt(2, idEquipamento);
                stmtIns.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
                stmtIns.executeUpdate();

                // 2. Atualizar disponibilidade do equipamento
                String sqlUpd = "UPDATE equipamento SET disponivel = FALSE WHERE id = ?";
                PreparedStatement stmtUpd = conn.prepareStatement(sqlUpd);
                stmtUpd.setInt(1, idEquipamento);
                stmtUpd.executeUpdate();

                conn.commit();
                System.out.println("SUCESSO: Empréstimo registrado!");
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }

    public void registrarDevolucao(int idEmprestimo, int idEquipamento) throws SQLException {
        // Regra 7.3
        try (Connection conn = ConexaoMySQL.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // Atualizar Emprestimo
                String sqlEmp = "UPDATE emprestimo SET data_devolucao = ?, status = 'FINALIZADO' WHERE id = ?";
                PreparedStatement stmtEmp = conn.prepareStatement(sqlEmp);
                stmtEmp.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
                stmtEmp.setInt(2, idEmprestimo);
                stmtEmp.executeUpdate();

                // Atualizar Equipamento
                String sqlEq = "UPDATE equipamento SET disponivel = TRUE WHERE id = ?";
                PreparedStatement stmtEq = conn.prepareStatement(sqlEq);
                stmtEq.setInt(1, idEquipamento);
                stmtEq.executeUpdate();

                conn.commit();
                System.out.println("SUCESSO: Devolução registrada e equipamento liberado!");
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }
}
