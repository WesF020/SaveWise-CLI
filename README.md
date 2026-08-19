# SaveWise — Personal Finance Manager

Aplicação de gerenciamento financeiro pessoal desenvolvida em Java 21, com foco em consolidar conceitos de Programação Orientada a Objetos, Banco de Dados Relacionais, padrão arquitetural DAO e interface gráfica com JavaFX.

O projeto está em evolução contínua: partiu de uma interface CLI funcional e está sendo migrado para uma interface gráfica moderna com JavaFX.

---

## Tecnologias e Conceitos

- Java 21
- JavaFX 21 *(em implementação)*
- MariaDB com JDBC
- Arquitetura em Camadas (UI → Service → DAO → Model)
- Padrão DAO com interface e implementação separadas
- Maven
- Conventional Commits

---

## Arquitetura

```
Main
 └── UI Layer (MenuCLI → JavaFX em breve)
       └── TransactionService
             └── TransactionDAOImpl
                   └── ConnectionFactory
                         └── MariaDB (savewise_db)
```

---

## Estrutura de Pacotes

```
com.savewise
├── connection
│   └── ConnectionFactory.java
├── dao
│   ├── ITransactionDAO.java
│   └── TransactionDAOImpl.java
├── model
│   ├── Transaction.java
│   └── TransactionType.java
├── service
│   └── TransactionService.java
└── ui
    └── MenuCLI.java
```

---

## Funcionalidades

### Implementadas
- [x] Cadastro de transações (receitas e despesas)
- [x] Listagem de todas as transações
- [x] Remoção de transações por ID
- [x] Cálculo e exibição do saldo atual
- [x] Persistência real em banco de dados relacional

### Em desenvolvimento
- [ ] Filtro de transações por tipo (INCOME/EXPENSE)
- [ ] Filtro de transações por categoria
- [ ] Data personalizada no cadastro de transações
- [ ] Interface gráfica com JavaFX
- [ ] Dashboard com saldo em destaque e tabela de transações
- [ ] Gráficos de despesas por categoria

---

## Como executar localmente

### Pré-requisitos
- Java 21
- Maven
- MariaDB

### Configuração do banco de dados

```sql
CREATE DATABASE savewise_db;

USE savewise_db;

CREATE TABLE transactions (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(100)   NOT NULL,
    amount      DECIMAL(10, 2) NOT NULL,
    type        ENUM('INCOME', 'EXPENSE') NOT NULL,
    category    VARCHAR(50),
    date        DATE           NOT NULL
);
```

### Configuração das credenciais

Copie o arquivo de exemplo e preencha com suas credenciais:

```bash
cp src/main/resources/config.properties.example src/main/resources/config.properties
```

Edite o `config.properties`:

```properties
db.url=jdbc:mariadb://localhost:3306/savewise_db
db.user=seu_usuario
db.password=sua_senha
```

### Executando

```bash
mvn compile exec:java -Dexec.mainClass="com.savewise.Main"
```

---

## Roadmap

Consulte o arquivo [ROADMAP.md](ROADMAP.md) para o planejamento detalhado das próximas fases do projeto, incluindo a migração completa para JavaFX.

---

## Autor

[WesF020](https://github.com/WesF020)
