-- Atenção: se o banco já existir e o Flyway estiver configurado com
-- baseline-on-migrate=true, este script NÃO será aplicado automaticamente
-- nesse banco (Flyway fará baseline). Em um schema vazio, este script
-- criará as tabelas necessárias.

CREATE TABLE IF NOT EXISTS tb_change (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    client VARCHAR(250) NOT NULL,
    description VARCHAR(1000),
    priority VARCHAR(20),
    status VARCHAR(20),
    deleted_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_test (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(300) NOT NULL UNIQUE,
    steps VARCHAR(1000) NOT NULL,
    expected_result VARCHAR(500) NOT NULL,
    deleted_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tb_bug (
    id BIGSERIAL PRIMARY KEY,
    description VARCHAR(500) NOT NULL,
    status VARCHAR(20),
    change_id BIGINT NOT NULL,
    CONSTRAINT fk_bug_change FOREIGN KEY (change_id) REFERENCES tb_change(id)
);

CREATE TABLE IF NOT EXISTS tb_execution (
    id BIGSERIAL PRIMARY KEY,
    test_case_id BIGINT NOT NULL,
    change_id BIGINT NOT NULL,
    status VARCHAR(20),
    executed_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_execution_test FOREIGN KEY (test_case_id) REFERENCES tb_test(id),
    CONSTRAINT fk_execution_change FOREIGN KEY (change_id) REFERENCES tb_change(id)
);

