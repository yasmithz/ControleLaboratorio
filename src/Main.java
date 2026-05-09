import br.edu.ifpa.laboratorio.dao.EmprestimoDAO;
import br.edu.ifpa.laboratorio.dao.EquipamentoDAO;
import br.edu.ifpa.laboratorio.model.Equipamento;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        System.out.println("--- INICIANDO BATERIA DE TESTES ---\n");

        try {
            // TESTE 1: Listar Disponíveis
            System.out.println("1️⃣ Listando equipamentos disponíveis:");
            List<Equipamento> lista = equipamentoDAO.listarDisponiveis();
            for (Equipamento eq : lista) {
                System.out.println("ID: " + eq.getId() + " - " + eq.getNome());
            }

            // TESTE 2: Emprestar um item válido (Ex: Aluno 2 pega o Equipamento 1)
            System.out.println("\n2️⃣ Testando empréstimo válido (Aluno 2 pega Equipamento 1):");
            emprestimoDAO.realizarEmprestimo(2, 1);

            // TESTE 3: Tentar emprestar o que já está emprestado
            System.out.println("\n3️⃣ Testando bloqueio (Tentar emprestar o Equipamento 1 de novo):");
            emprestimoDAO.realizarEmprestimo(3, 1);

            // TESTE 4: Devolver o item (Finalizando o empréstimo do item 1)
            System.out.println("\n4️⃣ Registrando devolução (Liberando o Equipamento 1):");
            // Nota: O primeiro parâmetro é o ID do Empréstimo (chutei 3 baseado nos inserts que fizemos), e o segundo é o ID do Equipamento (1)
            emprestimoDAO.registrarDevolucao(3, 1);

        } catch (Exception e) {
            System.err.println("Erro no teste: " + e.getMessage());
        }
    }
}