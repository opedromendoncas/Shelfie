# 🎲 Shelfie

Sistema para gerenciamento de coleções de jogos de tabuleiro desenvolvido em Java utilizando Swing e os princípios da Programação Orientada a Objetos.

---

## 📖 Sobre o Projeto

O Shelfie é uma aplicação desktop criada para auxiliar usuários no gerenciamento de suas coleções de jogos de tabuleiro.

O sistema permite cadastrar jogos, gerenciar amigos, controlar empréstimos e realizar consultas rápidas, proporcionando uma organização simples e eficiente da coleção.

Este projeto foi desenvolvido como parte das atividades da disciplina de Programação Orientada a Objetos, ministrada no 5º semestre do curso de Engenharia de Software da Universidade Santo Amaro (UNISA).

---

## ✨ Funcionalidades

### 🎮 Gerenciamento de Jogos

- Cadastro de jogos
- Edição de informações
- Remoção de registros
- Visualização da coleção

### 👥 Gerenciamento de Amigos

- Cadastro de amigos
- Atualização de informações
- Exclusão de registros

### 🤝 Controle de Empréstimos

- Registro de empréstimos
- Associação de jogos a amigos
- Acompanhamento dos empréstimos realizados

### 🔍 Consulta de Jogos

- Busca rápida por jogos cadastrados
- Visualização facilitada das informações

### ℹ️ Tela Sobre

- Informações do projeto
- Equipe de desenvolvimento
- Dados acadêmicos

---

## 🖥️ Interface

O sistema possui interface gráfica desenvolvida com Java Swing, seguindo uma identidade visual própria e organizada.

### Principais telas

- Home
- Meu Acervo
- Amigos
- Empréstimos
- Consulta
- Sobre

---

## 🛠️ Tecnologias Utilizadas

- Java
- Java Swing
- Eclipse IDE
- Programação Orientada a Objetos (POO)

---

## 🏗️ Arquitetura

O projeto foi organizado seguindo uma separação em camadas:

- **Model:** representa as entidades e dados do sistema.
- **View:** responsável pela interface gráfica e interação com o usuário.
- **Controller:** responsável pelo processamento das ações e regras de negócio.

Essa organização segue os princípios da Programação Orientada a Objetos, promovendo maior manutenção e reutilização do código.

---

## 📂 Estrutura do Projeto

```text
Shelfie/
│
├── src/
│   ├── controller/
│   │   └── Classes responsáveis pela lógica de negócio
│   │
│   ├── model/
│   │   └── Entidades e modelos do sistema
│   │
│   ├── view/
│   │   └── Interfaces gráficas desenvolvidas em Swing
│   │
│   └── module-info.java
│
├── assets/
│   ├── deal_icon.png
│   ├── filter_icon.png
│   ├── friends_icon1.png
│   ├── game_icon.png
│   ├── games_icon.png
│   ├── info.png
│   ├── logoblue.png
│   ├── logowhite.png
│   ├── search_icon.png
│   └── trash_icon.png
│
├── README.md
└── .gitignore
```

## 🚀 Como Executar

### Pré-requisitos

- Java JDK 8 ou superior
- Eclipse IDE (ou outra IDE compatível)

### Passos

1. Clone o repositório:

```bash
git clone [https://github.com/opedromendoncas/Shelfie]
```

2. Abra o projeto no Eclipse.

3. Certifique-se de que a pasta `assets` esteja na raiz do projeto.

4. Execute a classe:

```text
view.TelaPrincipal
```

---

## 👨‍💻 Equipe de Desenvolvimento

- Bárbara Lima
- Julia Tavares
- Letícia Miranda
- Lucas Viana
- Mileny Nazário
- Pedro Mendonça
- Ullisses Morais

---

## 🎓 Informações Acadêmicas

**Disciplina:** Programação Orientada a Objetos

**Projeto:** Shelfie – Sistema de Gerenciamento de Jogos de Tabuleiro

**Ano:** 2026

---

## 📸 Capturas de Tela

### Tela Inicial

![Tela Inicial](docs/home.png)

### Meu Acervo

![Meu Acervo](docs/acervo.png)

### Amigos

![Amigos](docs/amigos.png)

### Empréstimos

![Empréstimos](docs/emprestimos.png)

### Consulta

![Consulta](docs/consultas.png)

### Sobre

![Sobre](docs/sobre.png)

---

## 📄 Licença

Este projeto foi desenvolvido exclusivamente para fins acadêmicos.

© Shelfie 2026
