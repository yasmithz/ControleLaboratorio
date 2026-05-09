package br.edu.ifpa.laboratorio.dao;
import br.edu.ifpa.laboratorio.database.ConexaoMySQL;
import br.edu.ifpa.laboratorio.model.Equipamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    public void cadastrar(Equipamento e) throws SQLException {
        String sql = "INSERT INTO equipamento (nome, disponivel) VALUES (?, ?)";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getNome());
            stmt.setBoolean(2, e.isDisponivel());
            stmt.executeUpdate();
        }
    }

    public List<Equipamento> listarDisponiveis() throws SQLException {
        List<Equipamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipamento WHERE disponivel = TRUE";
        try (Connection conn = ConexaoMySQL.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Equipamento e = new Equipamento();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setDisponivel(rs.getBoolean("disponivel"));
                lista.add(e);
            }
        }
        return lista;
    }
}