# API BoaventuraMarketplace

**Esse projeto foi criado com o intuito de colocar todos meus conhecimentos em Java/SpringBoot na prática.**

---

## Funcionalidades

- **Cadastro de usuários**
- **Login de usuarios com token JWT**
- **Cadastro de produtos**
- **Compra de produtos**
- **Log das transações**
---

## Tecnologias Usadas

- **Backend**: Java, Spring Boot, SpringSecurity
- **Banco de Dados**: MySQL
- **Containerização**: Docker e Kubernetes
- **Autenticação**: JWT
- **Testes**: JUnit
- **CI/CD**: GitHub Actions para automação de build, testes e deploy
- **Outros**: Swagger
---

## Como Rodar Localmente

### 1. **Pré-requisitos**

Antes de rodar o projeto, verifique se você tem as seguintes ferramentas instaladas:

- **Docker**: [Instalar Docker](https://www.docker.com/get-started)
- **Docker Compose**: [Instalar Docker Compose](https://docs.docker.com/compose/install/)
- **Java 17**: [Instalar OpenJDK 17](https://adoptopenjdk.net/)

### 2. **Clone o repositório**

```bash
git clone https://github.com/guilhermeboaventurarodrigues/api-BoaventuraMarketplace-java.git
cd api-BoaventuraMarketplace-java
```

### 3. **Configurar variáveis de ambiente**

Crie um arquivo `.env` na raiz do projeto e configure as seguintes variáveis de ambiente:

```bash
# Configurações do banco de dados
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/seu-banco
SPRING_DATASOURCE_USERNAME=usuario
SPRING_DATASOURCE_PASSWORD=senha

# Configurações de JWT
jwt.secret=seu-segredo
jwt.expiration=3600000
```
### 4. **Rodar com o Docker Compose**

```bash
docker-compose up --build
```

### 5. **Acesse o Swagger para interagir**

```bash
http://localhost:8080/swagger-ui.html
```