# Desafio Técnico - API de Tabela Tarifária de Água

API REST desenvolvida em Spring Boot para gerenciar e calcular tarifas de água de forma parametrizável, utilizando faixas de consumo e diferentes categorias de clientes (Comercial, Industrial, Particular e Público).

## Tecnologias Utilizadas

- **Linguagem**: Java 21
- **Framework**: Spring Boot 4.0.6
- **JPA / ORM**: Spring Data JPA
- **Utilitários**: Lombok
- **Banco de Dados**: PostgreSQL 15+
- **Gerenciador de Dependências**: Maven
- **Documentação de API**: Swagger / OpenAPI (Springdoc 3.0.2)

## Regras de Negócio e Funcionalidades

O sistema calcula o consumo de água de forma progressiva por faixas. As tarifas e faixas são totalmente armazenadas no banco de dados, permitindo a mudança de valores sem precisar alterar o código.

- **4 Categorias suportadas**: COMERCIAL, INDUSTRIAL, PARTICULAR e PUBLICO.
- **Validação de Faixas**:
  - **Cobertura Completa**: A primeira faixa obrigatoriamente inicia em `0 m³`.
  - **Ordem Válida**: O valor inicial de uma faixa é sempre menor que o final.
  - **Não Sobreposição**: As faixas não cruzam os seus limites matemáticos.
  - **Cobertura Máxima**: O sistema impede o cálculo e retorna erro se o consumo informado ultrapassar o limite máximo estabelecido pelas faixas.

## Instalação e Execução

### 1. Requisitos
- Java 21 ou superior
- Maven
- PostgreSQL rodando localmente

### 2. Configurando o Banco de Dados

Crie o arquivo `src/main/resources/application-local.properties` com suas credenciais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/agua_tarifa
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### 3. Rodando o Projeto

```bash
mvn clean install
mvn spring-boot:run -Dspring-boot.run.profiles=local
```
A API estará rodando em `http://localhost:8080`.

## Documentação da API (Swagger)
Acesse a interface do Swagger para testar os endpoints:
- `http://localhost:8080/swagger-ui/index.html`

## Endpoints Principais

### 1. Criar Tabela Tarifária
`POST /api/tabelas-tarifarias`
Cria toda a parametrização em lote.

**Payload:**
```json
{
  "nome": "Tabela 2026",
  "dataVigencia": "2026-01-01",
  "categorias": [
    {
      "tipo": "INDUSTRIAL",
      "faixas": [
        { "inicio": 0, "fim": 10, "valorUnitario": 1.00 },
        { "inicio": 11, "fim": 20, "valorUnitario": 2.00 }
      ]
    }
  ]
}
```

### 2. Listar Tabelas
`GET /api/tabelas-tarifarias`
Retorna todas as tabelas cadastradas.

### 3. Deletar Tabela
`DELETE /api/tabelas-tarifarias/{id}`
Remove a tabela correspondente do sistema.

### 4. Calcular Faturamento
`POST /api/calculos`
Realiza o cálculo da fatura do consumidor baseado na tabela tarifária mais recente.

**Payload de Envio:**
```json
{
  "categoria": "INDUSTRIAL",
  "consumo": 18
}
```

**Retorno Esperado:**
```json
{
  "categoria": "INDUSTRIAL",
  "consumoTotal": 18,
  "valorTotal": 26.00,
  "detalhamento": [
    {
      "faixa": { "inicio": 0, "fim": 10 },
      "m3Cobrados": 10,
      "valorUnitario": 1.00,
      "subtotal": 10.00
    },
    {
      "faixa": { "inicio": 11, "fim": 20 },
      "m3Cobrados": 8,
      "valorUnitario": 2.00,
      "subtotal": 16.00
    }
  ]
}
```
