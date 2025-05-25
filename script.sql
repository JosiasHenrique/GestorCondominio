USE condominio_db;

CREATE TABLE Pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    idade INT,
    rg VARCHAR(30),
    cpf VARCHAR(30),
    tipo ENUM('Proprietario', 'Morador') NOT NULL
);

CREATE TABLE Residencia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(200),
    numero INT,
    cep VARCHAR(200),
    proprietario_id INT NOT NULL,
    CONSTRAINT FK_Residencia_Proprietario FOREIGN KEY (proprietario_id) REFERENCES Pessoa(id)
);

ALTER TABLE Pessoa ADD COLUMN residencia_id INT NULL,
ADD CONSTRAINT FK_Pessoa_Residencia FOREIGN KEY (residencia_id) REFERENCES Residencia(id);

CREATE TABLE mes_devido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    residencia_id INT NOT NULL,
    mes TINYINT NOT NULL CHECK (mes BETWEEN 1 AND 12),
    ano INT NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    CONSTRAINT FK_MesDevido_Residencia FOREIGN KEY (residencia_id) REFERENCES Residencia(id)
);

-- ===== Inserções =====

INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) VALUES
('João Silva', 45, 'MG123456', '123.456.789-00', 'Proprietario', NULL),
('Maria Oliveira', 38, 'SP987654', '987.654.321-00', 'Proprietario', NULL),
('Carlos Santos', 50, 'RJ112233', '111.222.333-44', 'Proprietario', NULL);

INSERT INTO Residencia (rua, numero, cep, proprietario_id) VALUES
('Rua das Flores', 123, '30123-456', 1),
('Avenida Brasil', 456, '40234-789', 2);

UPDATE Pessoa SET residencia_id = 1 WHERE id = 1;
UPDATE Pessoa SET residencia_id = 2 WHERE id = 2;

INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) VALUES
('Ana Silva', 20, 'MG654321', '321.654.987-00', 'Morador', 1),
('Pedro Silva', 18, 'MG111222', '111.222.333-55', 'Morador', 1),
('Lucas Silva', 25, 'MG333444', '333.444.555-66', 'Morador', 1),
('Fernanda Oliveira', 22, 'SP555666', '555.666.777-88', 'Morador', 2),
('Paula Oliveira', 19, 'SP777888', '777.888.999-00', 'Morador', 2),
('Bruno Oliveira', 27, 'SP999000', '999.000.111-22', 'Morador', 2),
('Marina Santos', 30, 'RJ123789', '123.789.456-00', 'Morador', 2),
('Diego Santos', 21, 'RJ456123', '456.123.789-11', 'Morador', 1),
('Carla Santos', 24, 'RJ789456', '789.456.123-22', 'Morador', 1),
('Rafael Santos', 29, 'RJ159357', '159.357.258-33', 'Morador', 2);

INSERT INTO mes_devido (residencia_id, mes, ano, valor) VALUES
(1, 1, 2025, 500.00),
(1, 2, 2025, 500.00),
(1, 3, 2025, 500.00),
(2, 1, 2025, 700.00),
(2, 2, 2025, 700.00);

-- ===== Verificações =====

 SELECT * FROM Pessoa;
 SELECT * FROM Residencia;
 SELECT * FROM mes_devido;
 
 -- Comando para buscar todos os moradores de uma residência específica
SELECT id, nome, idade, rg, cpf 
FROM Pessoa 
WHERE residencia_id = 1 AND tipo = 'Morador';

-- Comando para buscar o proprietário de uma residência específica
SELECT p.id, p.idade, p.nome, p.rg, p.cpf 
FROM Pessoa p 
JOIN Residencia r ON p.id = r.proprietario_id 
WHERE r.id = 1;

-- Comando para listar todas as residências com seus dados e o id do proprietário
SELECT id, rua, numero, cep, proprietario_id FROM Residencia;

-- Comando para listar todos os meses devidos associados a uma residência específica, filtrando pelo residencia_id
SELECT id, residencia_id, mes, ano, valor FROM mes_devido WHERE residencia_id = 1;
