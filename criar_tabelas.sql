-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS sistema_estoque CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sistema_estoque;

-- Tabela Tipos
CREATE TABLE Tipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela Subtipos
CREATE TABLE Subtipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_tipo INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_subtipos_tipos FOREIGN KEY (id_tipo) REFERENCES Tipos(id) ON DELETE CASCADE
);

-- Tabela Marcas
CREATE TABLE Marcas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

-- Tabela Produtos
CREATE TABLE Produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_subtipo INT NOT NULL,
    id_marca INT NOT NULL,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    peso_volume VARCHAR(255),
    codigo_barras VARCHAR(255),
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_produtos_subtipos FOREIGN KEY (id_subtipo) REFERENCES Subtipos(id) ON DELETE CASCADE,
    CONSTRAINT fk_produtos_marcas FOREIGN KEY (id_marca) REFERENCES Marcas(id) ON DELETE CASCADE
);

-- Tabela Locais de Armazenamento
CREATE TABLE LocaisArmazenamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT
);

-- Tabela Estoque
CREATE TABLE Estoque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    quantidade INT NOT NULL,
    data_validade DATE,
    id_local INT NOT NULL,
    data_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_estoque_produtos FOREIGN KEY (id_produto) REFERENCES Produtos(id) ON DELETE CASCADE,
    CONSTRAINT fk_estoque_locais FOREIGN KEY (id_local) REFERENCES LocaisArmazenamento(id) ON DELETE CASCADE
);

-- Tabela Movimentacoes
CREATE TABLE Movimentacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_produto INT NOT NULL,
    tipo_movimentacao ENUM('entrada', 'saida') NOT NULL,
    quantidade INT NOT NULL,
    data_movimentacao DATETIME DEFAULT CURRENT_TIMESTAMP,
    observacao TEXT,
    CONSTRAINT fk_movimentacoes_produtos FOREIGN KEY (id_produto) REFERENCES Produtos(id) ON DELETE CASCADE
);

-- Tabela EstoqueHistorico
CREATE TABLE EstoqueHistorico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_estoque INT NOT NULL,
    quantidade_anterior INT NOT NULL,
    quantidade_atual INT NOT NULL,
    data_alteracao DATETIME DEFAULT CURRENT_TIMESTAMP,
    tipo_movimentacao VARCHAR(255),
    observacao TEXT,
    CONSTRAINT fk_estoquehistorico_estoque FOREIGN KEY (id_estoque) REFERENCES Estoque(id) ON DELETE CASCADE
);
