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

Regras de Negócio (Lógica do Sistema)
O coração do software foi projetado (blindado) para seguir rigorosamente as diretrizes abaixo:

7.1)Disponibilidade: Um equipamento só pode ser emprestado se seu status no banco de dados for disponivel = TRUE.

7.2)Fluxo de Saída: Ao realizar um empréstimo, o sistema cria automaticamente um registro com status ATIVO e altera o status do equipamento para disponivel = FALSE.

7.3)Fluxo de Retorno: Na devolução, o registro de empréstimo é marcado como FINALIZADO, a data de devolução é salva e o equipamento é liberado (disponivel = TRUE) para o próximo aluno.

7.4)Segurança e Integridade: O sistema impede o registro de empréstimos para alunos ou equipamentos que não existam no cadastro, ou caso o item já esteja ocupado.

Implementação Técnica

Estrutura de Pastas (Padrão DAO)
A arquitetura foi dividida em camadas para facilitar a manutenção:
```
src/
└── br.edu.ifpa.laboratorio/
    ├── model/      # Classes de Entidade (Representação dos dados)
    ├── dao/        # Persistência (SQL e validação de regras de negócio)
    ├── database/   # Gerenciamento de conexão com o MySQL
    └── Main.java   # Ponto de entrada e rotina de testes'.
 ```

Etapa 1: Modelagem UML
Antes de escrever a primeira linha de código, estruturamos o sistema visualmente para garantir que todos os requisitos fossem atendidos.

1.1 Diagrama de Casos de Uso
Mostra o que o usuário (Administrador) pode fazer no sistema.

<img width="799" height="598" alt="image" src="https://github.com/user-attachments/assets/690981ad-c457-46af-b17a-bec1befd0b1c" />

1.2 Diagrama de Classe
A planta baixa do sistema, mostrando como as classes se relacionam.

<img width="1024" height="729" alt="image" src="https://github.com/user-attachments/assets/08e64330-f964-4e4b-add0-26f4fe344305" />


1.3 Diagrama de Sequência
O fluxo de mensagens entre o Java e o Banco de Dados durante um empréstimo.

<img width="1043" height="631" alt="image" src="https://github.com/user-attachments/assets/bafbec0c-6963-4636-b456-f83a9a5a94bf" />


Etapa 2: Banco de Dados (MySQL)
A base de dados foi projetada para ser robusta, utilizando chaves primárias e estrangeiras para garantir que nenhum empréstimo fique "orfão".

Script de Criação e Dados Iniciais
Tabela de alunos populada com 25 registros e uma lista completa de hardwares básicos e peças de manutenção.

```
DROP DATABASE IF EXISTS controle_laboratorio; 
CREATE DATABASE controle_laboratorio;
USE controle_laboratorio;

CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE equipamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    disponivel BOOLEAN DEFAULT TRUE
);

CREATE TABLE emprestimo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_aluno INT NOT NULL,
    id_equipamento INT NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE,
    status VARCHAR(20) DEFAULT 'ATIVO',
    
    FOREIGN KEY (id_aluno) REFERENCES aluno(id),
    FOREIGN KEY (id_equipamento) REFERENCES equipamento(id)
);

INSERT INTO aluno (nome, matricula) VALUES 
('Aydêe Lauanda', '2026001'),
('Alessandra Navegantes', '2026002'),
('Carlos Eduardo Lima', '2026003'),
('Davi Silva', '2026004'),
('Eduardo Castro', '2026005'),
('Fernanda Carvalho', '2026006'),
('Gabriel Martins', '2026007'),
('Hudson Henrique', '2026008'),
('Igor Ribeiro', '2026009'),
('Juliana Alves', '2026010'),
('Lucas Carvalho', '2026011'),
('Mariana Santos', '2026012'),
('Nicolas Ferreira', '2026013'),
('Olívia Castro', '2026014'),
('Pedro Henrique', '2026015'),
('Quintino Barros', '2026016'),
('Raiza Nunes', '2026017'),
('Renan Pinheiro', '2026018'),
('Thiago Batista', '2026019'),
('Vilcler Estumano', '2026020'),
('Vinícius Silva', '2026021'),
('Wagner Souza', '2026022'),
('Xavier Nogueira', '2026023'),
('Yan Monteiro', '2026024'),
('Zeca Camargo', '2026025');

INSERT INTO equipamento (nome, disponivel) VALUES 
-- Gabinetes
('Gabinete ATX Padrão Preto', TRUE),
('Gabinete Micro-ATX Básico', TRUE),
('Gabinete ATX com Fonte 200W', TRUE),

('Mouse USB Genérico', TRUE),
('Teclado ABNT2 USB', TRUE),
('Kit Teclado e Mouse Sem Fio', TRUE),
('Monitor AOC 18.5 Polegadas', FALSE), -- Indisponível para teste
('Monitor Dell 21 Polegadas', TRUE),
('Cabo de Força Padrão Novo', TRUE),
('Filtro de Linha 5 Tomadas', TRUE),

('Placa Mãe Gigabyte H310M', TRUE),
('Processador Intel Core i3 8ª Ger', TRUE),
('Processador AMD Ryzen 3 3200G', FALSE), -- Indisponível para teste
('Memória RAM DDR4 8GB 2666MHz', TRUE),
('Memória RAM DDR3 4GB 1333MHz', TRUE),
('SSD Kingston 240GB SATA', TRUE),
('HD Seagate 1TB 7200RPM', TRUE),
('Fonte ATX 500W Real', TRUE),
('Placa de Vídeo GT 710 2GB', TRUE),

('Alicate de Crimpagem RJ45', TRUE),
('Testador de Cabo de Rede', TRUE),
('Cabo de Rede Azul 2m', TRUE),
('Switch TP-Link 8 Portas', TRUE),

('Kit de Chaves Philips/Fenda', TRUE),
('Pulseira Antiestática', TRUE),
('Limpador de Contato Spray', TRUE);


-- 5. Inserindo Empréstimos Iniciais (Para casar com os itens FALSE acima)
-- Emprestando o Monitor AOC (ID 7) para a Ale (ID 1)
INSERT INTO emprestimo (id_aluno, id_equipamento, data_emprestimo, status) VALUES 
(1, 7, CURDATE(), 'ATIVO');

-- Emprestando o Processador Ryzen (ID 14) para o Lucas (ID 11)
INSERT INTO emprestimo (id_aluno, id_equipamento, data_emprestimo, status) VALUES 
(11, 14, CURDATE(), 'ATIVO');

select * from aluno;
select * from equipamento; 
```


Etapa 3: Testes e Validação de Regras
Para provar que a implementação está correta, a classe Main executa uma bateria de testes que valida desde o cadastro até o bloqueio de itens indisponíveis.

Cenários Testados:

Cadastro: Inserção de novos alunos e equipamentos.

Consulta: Listagem em tempo real de itens livres.

Empréstimo: Alteração automática de status no banco.

Bloqueio: Tentativa falha de pegar um item que já está com outro aluno.

Devolução: Retorno do item ao estoque virtual.

Primeiro teste com erro, pois repetia a Chave Primária

<img width="1824" height="882" alt="image" src="https://github.com/user-attachments/assets/72570e69-2763-4bd8-adfc-c35529c35acd" />

Segundo teste corrigido

<img width="1804" height="828" alt="image" src="https://github.com/user-attachments/assets/da397ea1-25c9-4eb2-b8b0-9b932ccb2e8b" />


<img width="1023" height="592" alt="image" src="https://github.com/user-attachments/assets/e6876e0a-f3b0-4fd0-b17f-934df9db97c2" />


<img width="917" height="694" alt="image" src="https://github.com/user-attachments/assets/cc80b6a6-112d-448d-bc80-5a6fdc3a31f9" />


Como Rodar o Projeto
Crie o banco de dados controle_laboratorio usando o script SQL incluso.

Importe o projeto no IntelliJ IDEA.

Certifique-se de que o mysql-connector-j está adicionado às dependências (Project Structure > Libraries).

Ajuste a senha do seu MySQL no arquivo ConexaoMySQL.java.

Execute o arquivo Main.java.

Desenvolvido por:
Yasmin Smith Nogueira
