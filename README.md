# 🖥️ Sistema de Controle de Empréstimo de Laboratório

Este projeto foi desenvolvido para a disciplina de **Engenharia de Software II**. O objetivo é gerenciar o empréstimo de equipamentos de hardware e redes (gabinetes, periféricos, peças de bancada) para alunos, garantindo a integridade dos dados e o cumprimento das regras de negócio.

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 21 (OO)
* **Banco de Dados:** MySQL 8.0
* **Modelagem:** Astah UML (Diagramas de Caso de Uso, Classe e Sequência)
* **IDE:** IntelliJ IDEA
* **Driver:** MySQL Connector/J 9.x

---

## 📋 Regras de Negócio Implementadas (7.1 - 7.4)

O sistema foi blindado para seguir as seguintes diretrizes:
* **7.1:** Equipamentos só podem ser emprestados se o status for `disponivel = TRUE`.
* **7.2:** Ao realizar empréstimo, o sistema gera um registro com status `ATIVO` e marca o equipamento como `disponivel = FALSE`.
* **7.3:** Na devolução, o registro é atualizado para `FINALIZADO`, a data de devolução é registrada e o equipamento volta a ser `disponivel = TRUE`.
* **7.4:** O sistema bloqueia empréstimos para alunos ou equipamentos inexistentes, ou itens já ocupados.

---

## 🛠️ Estrutura do Projeto

O projeto segue o padrão **DAO (Data Access Object)** para organização das camadas:

```text
src/
└── br.edu.ifpa.laboratorio/
    ├── model/      # Classes POJO (Aluno, Equipamento, Emprestimo)
    ├── dao/        # Persistência e Lógica de Banco (AlunoDAO, etc)
    ├── database/   # Configuração da Conexão JDBC
    └── Main.java   # Bateria de Testes e Validação

## 🛠️ Etapa 1: Modelagem UML

Nesta etapa, definimos o comportamento e a estrutura do sistema antes da codificação.

### 1.1 Diagrama de Casos de Uso
O diagrama de casos de uso ilustra as funcionalidades principais do sistema sob a perspectiva do administrador do laboratório.

> **[INSERIR AQUI O PRINT DO DIAGRAMA DE CASOS DE USO DO ASTAH]**

### 1.2 Diagrama de Classe
Define a estrutura estática do sistema, mostrando as classes `Aluno`, `Equipamento` e `Emprestimo`, além de seus atributos e métodos.

> **[INSERIR AQUI O PRINT DO DIAGRAMA DE CLASSE DO ASTAH]**

### 1.3 Diagrama de Sequência
Mapeia a interação entre os objetos no tempo, detalhando o processo de realizar um empréstimo desde a chamada no `Main` até a atualização no banco de dados via DAOs.

> **[INSERIR AQUI O PRINT DO DIAGRAMA DE SEQUÊNCIA DO ASTAH]**

---

## 💾 Etapa 2: Banco de Dados (MySQL)

A estrutura do banco de dados foi planejada para suportar integridade referencial através de chaves estrangeiras.

### Script SQL e Dados Iniciais
O script cria o banco `controle_laboratorio`, define as tabelas e insere dados iniciais (25 alunos e diversos equipamentos) para testes de carga e funcionalidade.

> **[INSERIR AQUI O PRINT DO SEU SCRIPT SQL NO MYSQL WORKBENCH]**
> *Dica: Tire um print que mostre os comandos CREATE TABLE e os comandos INSERT.*

---

## ☕ Etapa 3: Implementação Java (OO)

O projeto foi construído seguindo o padrão de arquitetura **DAO (Data Access Object)**, separando a lógica de negócio da persistência de dados.

* **Pacote `model`:** Classes de entidade (POJOs).
* **Pacote `database`:** Gerenciamento da conexão JDBC com MySQL.
* **Pacote `dao`:** Lógica de acesso ao banco e validação das regras de negócio 7.1 a 7.4.

---

## 🧪 Etapa 4: Testes e Validação

Para validar o sistema, foi criada uma bateria de testes na classe `Main`. Os testes cobrem:
1.  Cadastro de novo aluno e equipamento.
2.  Listagem de equipamentos que estão marcados como disponíveis.
3.  Realização de um empréstimo válido (marcando o item como indisponível).
4.  **Bloqueio de segurança:** Tentativa de emprestar um item que já está em uso (o sistema deve negar).
5.  Registro de devolução (liberando o item para novos empréstimos).

> **[INSERIR AQUI O PRINT DO CONSOLE DO INTELLIJ COM O RESULTADO DOS TESTES]**

---

## 🚀 Como Executar
1.  Importe o banco de dados usando o script SQL fornecido.
2.  Adicione o driver `mysql-connector-j` às bibliotecas do projeto.
3.  Configure a senha do seu banco em `ConexaoMySQL.java`.
4.  Execute a classe `Main.java`.

---
**Desenvolvido por:** [SEU NOME AQUI]
