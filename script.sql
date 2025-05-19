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

INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) VALUES
('João Silva', 45, '123456789', '111.222.333-44', 'Proprietario', NULL);

INSERT INTO Residencia (rua, numero, cep, proprietario_id) VALUES
('Rua das Flores',   100, '12345-678', 1),
('Avenida Central',  200, '23456-789', 1);

INSERT INTO Pessoa (nome, idade, rg, cpf, tipo, residencia_id) VALUES
('Maria Souza',    34, '987654321', '222.333.444-55', 'Morador', 1),
('Carlos Pereira', 30, '567890123', '333.444.555-66', 'Morador', 2);

INSERT INTO mes_devido (residencia_id, mes, ano, valor) VALUES
(1, 4, 2025, 500.00),  -- Casa 1 em débito Abril/2025
(1, 5, 2025, 520.00),  -- Casa 1 em débito Maio/2025
(2, 3, 2025, 480.00);  -- Casa 2 em débito Março/2025

-- ===== Verificações =====
 SELECT * FROM Pessoa;
 SELECT * FROM Residencia;
 SELECT * FROM mes_devido;
