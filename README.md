Checklist

- [x] Analisar código (controllers, services, repositories, entidades, DTOs, configurações)
- [x] Extrair funcionalidades existentes e endpoints expostos
- [x] Documentar arquitetura, tecnologias e como executar localmente
- [x] Sinalizar pontos não determináveis e potenciais melhorias

README - Test Manager

1. Visão geral do projeto

O Test Manager é uma aplicação backend desenvolvida em Java com Spring Boot para gerenciar casos de teste, execuções de teste e bugs associados a execuções. O projeto foi criado como estudo de caso e portfólio para demonstrar conhecimentos em desenvolvimento Backend Java.

2. Objetivo da aplicação

Fornecer uma API REST simples que permita cadastrar e gerenciar:
- Casos de teste (Test Cases)
- Mudanças/épicos/itens de trabalho (Changes)
- Execuções de teste (Executions)
- Bugs gerados a partir das execuções (Bugs)

O objetivo é simular um fluxo de QA/Dev para validar conceitos de modelagem, validação, camadas e controle de versão do banco (Flyway).

3. Tecnologias utilizadas

- Java 17
- Spring Boot (versão definida em `pom.xml`)
- Spring Data JPA
- Hibernate
- PostgreSQL (driver presente em `pom.xml`)
- Flyway (configurado no `application.yaml`)
- Jakarta Validation (Bean Validation)
- Lombok (uso em entidades e classes auxiliares)
- Git Flow (fluxo de trabalho adotado - informação do autor)

4. Arquitetura do projeto

Arquitetura em camadas clássica:
- Controller (camada de entrada HTTP) — pacotes: `controller`
- Service (lógica de negócio) — pacotes: `service`
- Repository (persistência) — pacotes: `repository`
- Model/Entity (JPA) — pacotes: `model.entity` e `model.enums`
- DTOs (Java Records) — pacotes: `dto.*`
- Tratamento global de exceções — `handler.GlobalExceptionHandler`

O projeto usa DTOs implementados como Java Records para entrada e saída de dados e validação via anotações Jakarta (`@NotBlank`, `@NotNull`).

5. Funcionalidades implementadas (endpoints expostos)

Observação: todos os endpoints estão prefixados por `v1/` conforme anotação em cada controller.

- Test Cases (`TestCaseController` — `v1/tests`)
  - GET `v1/tests` — lista todos os casos de teste ativos (retorna lista de `ResponseTestCaseDto`)
  - GET `v1/tests/title?title={title}` — busca caso de teste por título (retorna `ResponseTestCaseDto`)
  - GET `v1/tests/{id}` — busca caso de teste por id (retorna `ResponseTestCaseDto`)
  - POST `v1/tests` — cria um novo caso de teste (body: `CreateTestCaseDto`)
    - `CreateTestCaseDto` fields: `title` (String, @NotBlank), `expectedResult` (String, @NotBlank), `steps` (String, @NotBlank)
  - DELETE `v1/tests/{id}` — marca o caso como deletado (soft delete, popula `deletedAt`)

- Changes (`ChangeController` — `v1/changes`)
  - GET `v1/changes` — lista changes ativos (retorna lista de `ResponseChangeDto`)
  - GET `v1/changes/name?name={name}` — busca por nome (retorna lista de `ResponseChangeDto`)
  - POST `v1/changes` — cria uma change (body: `CreateChangeDto`)
    - `CreateChangeDto` fields: `name` (String, @NotBlank), `description` (String, @NotBlank), `client` (String, @NotBlank), `priority` (enum `ChangePriority`, @NotNull)
  - DELETE `v1/changes/{id}` — soft delete (marca `deletedAt`)
  - PATCH `v1/changes/start/{id}` — inicia a change (altera status para IN_PROGRESS)
  - PATCH `v1/changes/pause/{id}` — pausa a change (altera status para PAUSED)
  - PATCH `v1/changes/done/{id}` — finaliza a change (altera status para DONE)
  - PATCH `v1/changes/close/{id}` — fecha a change (altera status para CLOSED)

- Executions (`ExecutionController` — `v1/executions`)
  - GET `v1/executions` — lista todas as execuções (retorna lista de `ResponseExecution`)
  - POST `v1/executions` — cria uma execução (body: `CreateExecution` com `testCaseId` e `changeId`)
  - PATCH `v1/executions/{id}/success` — marca execução como PASS
    - Observação: o mapeamento contém a rota `sucess` (possível erro de digitação; esperado `success`).
  - PATCH `v1/executions/{id}/fail` — marca execução como FAIL

- Bugs (`BugController` — `v1/bugs`)
  - GET `v1/bugs` — lista todos os bugs (retorna lista `ResponseBugDto`)
  - POST `v1/bugs` — cria um bug (body: `CreateBugDto` com `description` e `changeId`)
  - PATCH `v1/bugs/{id}` — fecha o bug (altera status para CLOSED)

6. Estrutura de pastas (resumo)

Raiz do projeto (caminhos relevantes):

- `src/main/java/br/com/hericlesalves/testmanager/`
  - `controller/` — controllers REST
  - `service/` — regras de negócio
  - `repository/` — interfaces JPA
  - `model/entity/` — entidades JPA
  - `model/enums/` — enums de domínio
  - `dto/` — DTOs (Java Records)
  - `handler/` — tratamento global de exceções
  - `configuration/` — configuração (CORS)

- `src/main/resources/` — recursos e configurações
  - `application.yaml` — configurações de datasource, JPA e Flyway
  - `db/migration/` — pasta de migrations Flyway (contém `V1__create_tables.sql`)

7. Banco de dados e migrations com Flyway

- O `application.yaml` referencia variáveis de ambiente para conexão:
  - `spring.datasource.url: ${DATABASE_URL}`
  - `spring.datasource.username: ${USER}`
  - `spring.datasource.password: ${PASSWORD}`

- Observações importantes:
  - `spring.jpa.hibernate.ddl-auto` está configurado como `validate`. Isso significa que o esquema do banco deve existir e ser compatível com as entidades JPA antes da aplicação iniciar.
  - Flyway está habilitado (`spring.flyway.baseline-on-migrate: true`) e o repositório agora inclui um script de migração inicial `src/main/resources/db/migration/V1__create_tables.sql`.
  - Observação sobre comportamento: se o banco já existir (contendo as tabelas), com `baseline-on-migrate: true` o Flyway fará baseline e NÃO aplicará o `V1` nesse banco existente (evitando recriar objetos). Em um schema vazio o `V1` será aplicado normalmente para criar as tabelas.

Conclusão: antes de executar a aplicação localmente você deve prover o esquema do banco (criar as tabelas necessárias) ou adicionar scripts de migração Flyway. Sem isso, com `ddl-auto=validate`, a aplicação falhará ao validar o esquema.

8. Como executar o projeto localmente

Pré-requisitos:
- Java 17
- Maven (o projeto inclui `mvnw`/`mvnw.cmd` wrappers)
- PostgreSQL com um banco de dados criado para a aplicação