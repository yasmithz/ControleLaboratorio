import br.edu.ifpa.laboratorio.dao.EmprestimoDAO;
import br.edu.ifpa.laboratorio.dao.EquipamentoDAO;
import br.edu.ifpa.laboratorio.model.Equipamento;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();

        System.out.println("========================================================");
        System.out.println("  SISTEMA DE LABORATÓRIO - APRESENTAÇÃO OFICIAL  ");
        System.out.println("========================================================\n");

        try {
            // TESTE 1: Verificar Estoque Disponível
            System.out.println("TESTE 1: Verificando equipamentos DISPONÍVEIS no estoque...");
            List<Equipamento> disponiveis = equipamentoDAO.listarDisponiveis();
            for (Equipamento eq : disponiveis) {
                System.out.println("    ID: " + eq.getId() + " | " + eq.getNome());
            }
            System.out.println("---------------------------------------------------------");

            // TESTE 2: Empréstimo com Sucesso 1
            System.out.println(" TESTE 2: Realizando empréstimo (Aluno ID 1 pega Equipamento ID 1)...");
            emprestimoDAO.realizarEmprestimo(1, 1);
            System.out.println("---------------------------------------------------------");

            // TESTE 3: Empréstimo com Sucesso 2
            System.out.println(" TESTE 3: Realizando outro empréstimo (Aluno ID 2 pega Equipamento ID 2)...");
            emprestimoDAO.realizarEmprestimo(2, 2);
            System.out.println("---------------------------------------------------------");

            // TESTE 4:Regras 7.1 e 7.4
            System.out.println(" TESTE 4: FORÇANDO ERRO - Aluno 3 tenta pegar o Equipamento 1 (que já está em uso)...");
            emprestimoDAO.realizarEmprestimo(3, 1);
            System.out.println("---------------------------------------------------------");

            // TESTE 5: Consultar Empréstimos Ativos (Mostra quem está com o quê / Itens Indisponíveis)
            System.out.println(" TESTE 5: Consultando Empréstimos Ativos (Itens fora do laboratório)...");
            emprestimoDAO.listarEmprestimosAtivos();
            System.out.println("---------------------------------------------------------");

            // TESTE 6: Devolução do Equipamento 1
            System.out.println(" TESTE 6: Registrando devolução (Liberando o Equipamento 1)...");
            // ATENÇÃO: Estou colocando o ID do empréstimo como 1. Veja a dica abaixo sobre isso!
            emprestimoDAO.registrarDevolucao(1, 1);
            System.out.println("---------------------------------------------------------");

            // TESTE 7: Mostrar o Histórico Geral
            System.out.println(" TESTE 7: Exibindo o Histórico Completo do Banco de Dados...");
            emprestimoDAO.listarHistorico();
            System.out.println("---------------------------------------------------------");

            System.out.println("\n===================================================");
            System.out.println("  APRESENTAÇÃO FINALIZADA COM SUCESSO!  ");
            System.out.println("===================================================");

        } catch (Exception e) {
            System.err.println(" Ocorreu um erro durante a apresentação: " + e.getMessage());
        }
    }
}
