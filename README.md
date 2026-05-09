🖥️ Sistema de Controle de Empréstimo de Laboratório
Visão Geral do Projeto
O Sistema de Controle de Empréstimo de Laboratório nasceu da necessidade de organizar o fluxo de materiais em laboratórios de hardware e redes. Em um ambiente acadêmico ou técnico, o controle manual de componentes como gabinetes, periféricos, processadores e cabos é propenso a falhas, perdas e conflitos de agendamento.

Este sistema atua como um "gerente digital", garantindo que:

O administrador saiba exatamente quem está com qual equipamento.

Um item não seja emprestado para duas pessoas ao mesmo tempo.

O histórico de uso seja preservado para fins de manutenção e inventário.

Desenvolvido para a disciplina de Engenharia de Software II, o foco principal foi aplicar os conceitos de modelagem UML, persistência de dados e as melhores práticas de Programação Orientada a Objetos.

Tecnologias Utilizadas
Linguagem: Java 21 (JDK 21)

Banco de Dados: MySQL 8.0

Modelagem: Astah UML

IDE: IntelliJ IDEA

Persistência: JDBC com Driver MySQL Connector/J 9.x

📋 Regras de Negócio (Lógica do Sistema)
O coração do software foi projetado (blindado) para seguir rigorosamente as diretrizes abaixo:

Disponibilidade: Um equipamento só pode ser emprestado se seu status no banco de dados for disponivel = TRUE.

Fluxo de Saída: Ao realizar um empréstimo, o sistema cria automaticamente um registro com status ATIVO e altera o status do equipamento para disponivel = FALSE.

Fluxo de Retorno: Na devolução, o registro de empréstimo é marcado como FINALIZADO, a data de devolução é salva e o equipamento é liberado (disponivel = TRUE) para o próximo aluno.

Segurança e Integridade: O sistema impede o registro de empréstimos para alunos ou equipamentos que não existam no cadastro, ou caso o item já esteja ocupado.

Implementação Técnica

Estrutura de Pastas (Padrão DAO)
A arquitetura foi dividida em camadas para facilitar a manutenção:
'''src/
└── br.edu.ifpa.laboratorio/
    ├── model/      # Classes de Entidade (Representação dos dados)
    ├── dao/        # Persistência (SQL e validação de regras de negócio)
    ├── database/   # Gerenciamento de conexão com o MySQL
    └── Main.java   # Ponto de entrada e rotina de testes'''

Etapa 1: Modelagem UML
Antes de escrever a primeira linha de código, estruturamos o sistema visualmente para garantir que todos os requisitos fossem atendidos.

1.1 Diagrama de Casos de Uso
Mostra o que o usuário (Administrador) pode fazer no sistema.

[INSERIR AQUI O PRINT DO DIAGRAMA DE CASOS DE USO]

1.2 Diagrama de Classe
A planta baixa do sistema, mostrando como as classes se relacionam.

[INSERIR AQUI O PRINT DO DIAGRAMA DE CLASSE]

1.3 Diagrama de Sequência
O fluxo de mensagens entre o Java e o Banco de Dados durante um empréstimo.

[INSERIR AQUI O PRINT DO DIAGRAMA DE SEQUÊNCIA]

Etapa 2: Banco de Dados (MySQL)
A base de dados foi projetada para ser robusta, utilizando chaves primárias e estrangeiras para garantir que nenhum empréstimo fique "orfão".

Script de Criação e Dados Iniciais
Tabela de alunos populada com 25 registros e uma lista completa de hardwares básicos e peças de manutenção.

[INSERIR AQUI O PRINT DO SCRIPT SQL NO WORKBENCH]

[INSERIR AQUI O PRINT DO RESULTADO DA TABELA (SELECT * FROM EQUIPAMENTO)]

Etapa 3: Testes e Validação de Regras
Para provar que a implementação está correta, a classe Main executa uma bateria de testes que valida desde o cadastro até o bloqueio de itens indisponíveis.

Cenários Testados:

Cadastro: Inserção de novos alunos e equipamentos.

Consulta: Listagem em tempo real de itens livres.

Empréstimo: Alteração automática de status no banco.

Bloqueio: Tentativa falha de pegar um item que já está com outro aluno.

Devolução: Retorno do item ao estoque virtual.

[INSERIR AQUI O PRINT DO CONSOLE DO INTELLIJ COM OS RESULTADOS]

Como Rodar o Projeto
Crie o banco de dados controle_laboratorio usando o script SQL incluso.

Importe o projeto no IntelliJ IDEA.

Certifique-se de que o mysql-connector-j está adicionado às dependências (Project Structure > Libraries).

Ajuste a senha do seu MySQL no arquivo ConexaoMySQL.java.

Execute o arquivo Main.java.

Desenvolvido por:
Yasmin Smith Nogueira
