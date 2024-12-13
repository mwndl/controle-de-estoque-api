-- V1__create_tables.sql
CREATE TABLE tipos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT,
    data_criacao DATETIME,
    data_atualizacao DATETIME
);

CREATE TABLE subtipos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo BIGINT,
    nome VARCHAR(255),
    descricao TEXT,
    data_criacao DATETIME,
    data_atualizacao DATETIME,
    FOREIGN KEY (id_tipo) REFERENCES tipos(id)
);

CREATE TABLE marcas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255)
);

CREATE TABLE produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_subtipo BIGINT,
    id_marca BIGINT,
    nome VARCHAR(255),
    descricao TEXT,
    peso_volume VARCHAR(255),
    codigo_barras VARCHAR(255),
    data_criacao DATETIME,
    data_atualizacao DATETIME,
    FOREIGN KEY (id_subtipo) REFERENCES subtipos(id),
    FOREIGN KEY (id_marca) REFERENCES marcas(id)
);

CREATE TABLE locais_armazenamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    descricao TEXT
);

CREATE TABLE estoque (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_produto BIGINT,
    quantidade INT,
    data_validade DATE,
    id_local BIGINT,
    data_criacao DATETIME,
    data_atualizacao DATETIME,
    FOREIGN KEY (id_produto) REFERENCES produtos(id),
    FOREIGN KEY (id_local) REFERENCES locais_armazenamento(id)
);

CREATE TABLE movimentacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_produto BIGINT,
    tipo_movimentacao ENUM('entrada', 'saida'),
    quantidade INT,
    data_movimentacao DATETIME,
    observacao TEXT,
    FOREIGN KEY (id_produto) REFERENCES produtos(id)
);

CREATE TABLE estoque_historico (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_estoque BIGINT,
    quantidade_anterior INT,
    quantidade_atual INT,
    data_alteracao DATETIME,
    tipo_movimentacao VARCHAR(255),
    observacao TEXT,
    FOREIGN KEY (id_estoque) REFERENCES estoque(id)
);
