# prova


Segunda Etapa do Processo Seletivo - Implementação em Java
Nesta segunda etapa do processo seletivo, os candidatos serão desafiados a implementar uma aplicação Java com base na situação problema apresentada na etapa anterior (etapa de banco de dados). A aplicação deve permitir o gerenciamento eficiente de disciplinas e professores em um ambiente acadêmico. Em grupo (com no mínimo 3 e no máximo 4 integrantes) serão estabelecidos os seguintes desafios:
Implemente o menu (na classe Main) com as funcionalidades:
Funcionalidade 1: cadastro de disciplinas, incluindo sigla ou código da disciplina, nome da disciplina e ementa.
Funcionalidade 2: cadastro de professores, incluindo código do professor, nome, especialidade, data de admissão e sigla ou código da disciplina na qual ele é responsável.
 Funcionalidade 3: listar todas as disciplinas.
 Funcionalidade 4: listar todos os professores
 Funcionalidade 5: exibir uma disciplina
 Funcionalidade 6: exibir um professor
 Funcionalidade 7: deletar uma disciplina
 Funcionalidade 8: deletar um professor
 Funcionalidade 9: atualizar nome e ementa da disciplina
 Funcionalidade 10: atualizar nome e especialidade do professor
 Funcionalidade 11: exibir todos professores que têm especialidade em "Tecnologia da Informação" (não devem ser exibidos nomes iguais)
 Funcionalidade 12: exibir todos os professores ordenados por data de admissão mais recente. 
O sistema deve ser implementado em JAVA, utilizando padrão de desenvolvimento MVC com camada específica para acesso a dados, ou seja, você deve implementar a camada DAO.
Lembre-se que um professor pode lecionar em mais de uma disciplina.
Lembre-se de adicionar o connector JDBC ao projeto.
Lembre-se de colocar um comentário na classe Main com o nome dos integrantes do grupo.

Prazo: duas horas
Entrega
2MDS - https://classroom.google.com/c/NjE3NDM0Njk4NDkz/a/NTkwMjM0OTU0NjMy/details
2TDS - https://classroom.google.com/c/NTg5MDM5MDg4Mjkw/a/NTkwMjM0NzE4NTUx/details 


"Faça o teu melhor, nas condições que você tem, enquanto não tem condições melhores para fazer melhor ainda."
Mario Sergio Cortella

--------------------------------------------------------------------------------------------------------------
script.sql
-- Cria o banco de dados "prova"
CREATE DATABASE senai_banco_academico;

-- Utiliza o banco de dados "prova"
USE senai_banco_academico;

-- tabela disciplina
CREATE TABLE disciplina (
    sigla VARCHAR(15) PRIMARY KEY,
    nome VARCHAR(40) NOT NULL,
    ementa TEXT
);

-- tabela professor
CREATE TABLE professor (
    codigo VARCHAR(15),
    nome VARCHAR(40) NOT NULL,
    codigo_disciplina VARCHAR(15) NOT NULL,
    especialidade VARCHAR(50) NOT NULL,
    data_admissao DATE NOT NULL,
    PRIMARY KEY(codigo, codigo_disciplina),
    CONSTRAINT fk_professor_disciplina FOREIGN KEY (codigo_disciplina) REFERENCES disciplina(sigla) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Inserção das disciplinas na tabela disciplina
INSERT INTO disciplina (sigla, nome, ementa) VALUES
('PWBE', 'Programação Web para Back-End', 'Tal disciplina aborda conceitos e técnicas para desenvolvimento de aplicativos web no lado do servidor, com foco em back-end.'),
('PWFE', 'Programação Web para Front-End', 'Esta disciplina explora as tecnologias e práticas para desenvolvimento de aplicações web com foco em front-end.'),
('IDM', 'Interface para Dispositivos Móveis', 'A disciplina aborda projetar e desenvolver interfaces de usuário para aplicativos móveis, com foco nos princípios de design e usabilidade.'),
('BD', 'Banco de Dados', 'Esta disciplina contempla conceitos fundamentais de bancos de dados, modelagem de dados e linguagens de consulta SQL.'),
('REDES', 'Redes de Computadores', 'A disciplina aborda conceitos introdutórios sobre redes de computadores, topologias e padrões.'),
('SO', 'Sistemas Operacionais', 'A disciplina contempla conceitos fundamentais sobre sistemas operacionais, apresentando as funcionalidades dos sistemas operacionais baseados nas plataformas Windows e Linux.');






-- Inserção dos professores na tabela professor
INSERT INTO professor (codigo, nome, codigo_disciplina, especialidade, data_admissao) VALUES
(101, 'Eduardo Nascimento', 'PWFE', 'Tecnologia da Informação', '2023-01-15'),
(101, 'Eduardo Nascimento', 'BD', 'Tecnologia da Informação', '2023-01-15'),
(102, 'Matheus Michilino', 'IDM', 'Mecatrônica e Interfaces Robóticas', '2020-01-01'),
(103, 'Rafael Selvagio', 'PWBE', 'Tecnologia da Informação', '2023-06-01'),
(104, 'Rafael Rizzi', 'REDES', 'Eng. Elétrica', '2022-01-01');
