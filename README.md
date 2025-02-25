# 🐾 HostPet

Bem-vindo ao **HostPet**! Este é um sistema de gerenciamento para hotéis pet, permitindo o cadastro de clientes e seus pets, agendamentos de hospedagem, controle de disponibilidade das baias e gestão financeira. O projeto foi desenvolvido com **Spring Boot** e **SQLite**.

## 🚀 Tecnologias Utilizadas
- **Java 17** + **Spring Boot**
- **Spring Security** (Autenticação com JWT)
- **SQLite** (Banco de dados)
- **JPA/Hibernate** (ORM)
- **Maven** (Gerenciador de dependências)

---

## 📌 Como Rodar o Projeto

### 1⃣ Pré-requisitos
Antes de começar, certifique-se de ter:
- **Java 17** ou superior instalado
- **Maven** configurado
- **IntelliJ IDEA** (ou outra IDE de sua escolha)

### 2⃣ Clonar o repositório
```sh
git clone https://github.com/seu-usuario/HostPet.git
cd HostPet
```

### 3⃣ Configurar o ambiente
O banco de dados utilizado é o **SQLite**, então ele já está embutido no projeto e não requer instalação separada.

Caso precise, verifique a configuração do banco no arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:sqlite:hostpet.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update
```

### 4⃣ Executar o projeto
No IntelliJ IDEA, basta rodar a classe principal:
```sh
mvn spring-boot:run
```
Ou, se preferir, executar diretamente:
```sh
java -jar target/hostpet.jar
```

A API estará disponível em **http://localhost:8080** 🚀

---

## 🔑 Autenticação e Segurança
O sistema usa **JWT** para autenticação. Para acessar as rotas protegidas, primeiro você precisa registrar um usuário e fazer login para obter um token.

Para acessar endpoints protegidos, envie o token JWT no **header** da requisição:
```
Authorization: Bearer SEU_TOKEN_AQUI
```

---

## 🛠 Comandos úteis
🔹 Para compilar o projeto:
```sh
mvn clean package
```
🔹 Para rodar os testes:
```sh
mvn test
```
🔹 Para limpar e reconstruir:
```sh
mvn clean install
```

---

## 📌 Observações
- O banco de dados **SQLite** gera um arquivo `hostpet.db` na raiz do projeto.
- Para visualizar o banco de dados, use ferramentas como **DBeaver**.
- Caso precise resetar os dados, basta excluir `hostpet.db` e reiniciar a aplicação.

---

## 🎯 Sobre o Projeto
Este projeto foi desenvolvido com foco na administração de hotéis para pets, garantindo um controle eficiente de clientes, pets e hospedagens.

Se tiver dúvidas ou sugestões, entre em contato! 🚀🐶🐱

