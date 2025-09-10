<div align="center">
  <img src="https://github.com/Codee-Hub/TestYouAI_Web/blob/main/TestTouAI/Logo.png" alt="logo" width="600"/>
</div>
<br/>


Esta API RESTful foi desenvolvida para suportar a aplicação **TestYouAI**, permitindo que o front-end gere, persista e gerencie testes de forma segura e inteligente. A aplicação utiliza **Spring Boot 3.5**, **Java 21**, **Spring AI (OpenAI GPT-4)**, **PostgreSQL/H2**, autenticação com **OAuth2 + JWT**, documentação interativa com **Swagger**, mapeamento DTO-Entity com **MapStruct**, validação de dados, e containerização com **Docker**.

---

## 🔍 Visão Geral

Este projeto é uma **API RESTful poderosa, robusta e extensível**, desenvolvida para gerenciar a geração e armazenamento de testes personalizados com inteligência artificial. Ele permite que usuários não autenticados experimentem testes gerados pela IA, enquanto usuários autenticados podem salvar suas respostas, consultar histórico e interagir de forma segura com o sistema.

Após diversas melhorias e refatorações, a aplicação atingiu um **novo patamar de qualidade e desempenho**, adotando uma arquitetura moderna e altamente escalável. Dentre os principais aprimoramentos, destacam-se:

✅ **Princípios do Clean Code e SOLID** aplicados em todo o projeto.  
🔐 **Segurança reforçada** com Spring Security, autenticação via JWT e suporte completo a OAuth2 (Authorization Code Flow e Client Credentials).  
🛡️ **Controle de acesso** com anotações como `@PreAuthorize` para proteger endpoints sensíveis.  
🔍 **Documentação interativa** com Swagger/OpenAPI, permitindo testar os endpoints diretamente na interface.  
❌ **Tratamento global de exceções** com respostas padronizadas para erros de negócio, validações e falhas inesperadas.  
💬 **Integração com OpenAI (GPT-4) via Spring AI**, permitindo a geração inteligente de testes e manipulação de respostas.  
⚙️ **Validações avançadas** usando Spring Validator e anotações customizadas.  
🔎 **Filtros e conversão de dados** usando MapStruct, DTOs e Jackson para manipular JSON da IA.  
🐳 **Containerização com Docker**, incluindo variáveis de ambiente para fácil configuração e deploy em produção.  
🔁 **Separação em camadas bem definidas**, com DTOs, mapeadores, testes e uso de Spring Data JPA para persistência.  

Esta API está preparada para ser utilizada em **ambientes de produção**, com **alto nível de segurança, manutenibilidade e extensibilidade** para novos recursos.


---

## ⚙️ Funcionalidades

### 🔑 Autenticação e Autorização
*  JWT tokens para usuários autenticados
*  OAuth2 Resource Server para controle de acesso seguro

### 👤 Gestão de Usuários
*  Cadastro de novos usuários
*  Login e autenticação
*  Atualização de dados do usuário
*  Recuperação de informações e redefinição de senha

### 📋 Gestão de Testes
*  Criação de testes via API
*  Listagem e consulta de testes existentes
*  Atualização de testes
*  Correção automática de respostas

### 🤖 Integração com GPT-4
*  Geração inteligente de testes usando **Spring AI Starter OpenAI**
*  Processamento e manipulação de respostas via JSON e Jackson

### 🛡️ Validações Avançadas
*  Spring Validator e Bean Validation para garantir dados consistentes
*  Mensagens customizadas para erros de entrada

### 📚 Documentação Interativa
*  Swagger / Springdoc OpenAPI para testes de endpoints diretamente no navegador

### 💾 Suporte a Bancos de Dados
*  PostgreSQL para ambiente de produção
*  H2 para desenvolvimento e testes locais

### 🔄 Mapeamento de DTOs
*  MapStruct para transformação entre entidades e modelos de API de forma eficiente

### 🔐 Segurança
*  Spring Security com OAuth2 e JWT
*  Controle de acesso com anotações como `@PreAuthorize`

### 🐳 Docker
*  Containerização completa
*  Configuração de variáveis de ambiente para deploy rápido e seguro

---

## 🌐 Estrutura de Pacotes

```
src/main/java/github/devhub/testyouai/
├── config/          # Configurações de segurança, OAuth2, CORS, etc.
├── controller/      # Endpoints REST da API
├── dto/             # Objetos de transferência de dados
├── entity/          # Entidades JPA mapeadas para o banco
├── exception/       # Tratamento de exceções personalizadas
├── mapper/          # MapStruct mapeadores DTO <-> Entity
├── repository/      # Interfaces JPA Repositories
├── service/         # Regras de negócio
├── utils/           # Funções auxiliares e helpers
└── validator/       # Validações customizadas
```

---

## 🐳 Docker

### Dockerfile

```dockerfile
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

EXPOSE 8080

ENV DATASOURCE_URL=""
ENV DATASOURCE_USERNAME=""
ENV DATASOURCE_PASSWORD=""
ENV OPENAI_API_KEY=""
ENV SPRING_PROFILES_ACTIVE="production"
ENV TZ="America/Sao_Paulo"

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## 📄 Swagger

Após rodar a aplicação, acesse a documentação interativa:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔐 Segurança com OAuth2 e JWT

* **OAuth2 + JWT**
* Tokens assinados e verificados
* Controle de acesso via roles
* Filtros personalizados de autenticação

---

## ⚙️ Tecnologias Utilizadas

| Tecnologia                      | Função                                   |
| ------------------------------- | ---------------------------------------- |
| **Java 21**                     | Linguagem base                           |
| **Spring Boot 3.5**             | Core do framework                        |
| **Spring AI (GPT-4)**           | Geração e interpretação de testes com IA |
| **Spring Security + OAuth2**    | Autenticação e autorização               |
| **JWT**                         | Tokens de acesso seguro                  |
| **Spring Data JPA / Hibernate** | ORM para persistência                    |
| **PostgreSQL / H2**             | Banco de dados                           |
| **MapStruct**                   | Conversão entre DTOs e entidades         |
| **Lombok**                      | Redução de boilerplate                   |
| **Spring Validation**           | Validação de dados                       |
| **Swagger / Springdoc OpenAPI** | Documentação interativa da API           |
| **Docker**                      | Containerização e deploy                 |
| **Jackson Databind**            | Desserialização JSON produzido pela IA   |

---

## 📄 Documentação Interativa com Swagger

Após rodar a aplicação, acesse:

```
http://localhost:8081/swagger-ui/index.html
```

![Swagger](https://github.com/Codee-Hub/TestYouAI_APIRestful/blob/main/TestTouAI/swagger.png)
![Swagger](https://github.com/Codee-Hub/TestYouAI_APIRestful/blob/main/TestTouAI/token.png)

---

## 🛠️ Como Rodar Localmente

```bash
# Construa a imagem Docker
docker build -t testyouai-api .

# Execute o container
docker run -d -p 8080:8080 \
  -e DATASOURCE_URL="jdbc:postgresql://host.docker.internal:5432/testyouai" \
  -e DATASOURCE_USERNAME="postgres" \
  -e DATASOURCE_PASSWORD="senha" \
  -e OPENAI_API_KEY="sk-xxxxxxxxxxxxxxxx" \
  --name testyouai-api \
  testyouai-api
```

---

## 🧠 Contribuições e Boas Práticas Adotadas

-  **Princípios SOLID**
-  **Camadas bem definidas**
-  **Boas práticas de DTOs, validações, tratamento de erros e organização de pacotes**
-  **Autenticação e segurança alinhada com padrões modernos**

---

## 📄 Licença

MIT License © 2025 - [Cauã Farias](https://github.com/CauZy-Goes)

---

## 👨‍💻 Autor

Desenvolvido por [Cauã Farias (CauZy-Goes)](https://github.com/CauZy-Goes)
