# API de Reserva de Salas (Checkpoint Java Alura)

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-20.10-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Status](https://img.shields.io/badge/Status-Concluído-brightgreen?style=for-the-badge)

Projeto de API REST desenvolvido como checkpoint de Nível 1 da formação Back-End Java da Alura. O objetivo é construir um sistema completo de gerenciamento de reservas de salas, aplicando conceitos de arquitetura em camadas, regras de negócio complexas, testes unitários e containerização.

---

## 🚀 Funcionalidades Principais

* **Gerenciamento de Salas:** CRUD completo para Salas (criar, listar, atualizar, deletar).
* **Gerenciamento de Usuários:** CRUD completo para Usuários.
* **Sistema de Reservas:**
    * Criação de novas reservas.
    * Cancelamento de reservas com registro de motivo.
* **Regra de Negócio (Anti-conflito):** A API impede a criação de reservas para a mesma sala em horários sobrepostos (a query `findConflitos` implementa a lógica `inicio_existente < novo_fim E fim_existente > novo_inicio`).
* **Validações:** O sistema valida regras como datas inválidas, salas inativas e capacidade.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3
* **Persistência:** Spring Data JPA (Hibernate)
* **Banco de Dados:** PostgreSQL
* **Testes:** JUnit 5 e Mockito (para testes unitários da camada de serviço)
* **API:** REST com padrão DTO (Data Transfer Objects)
* **Containerização:** Docker e Docker Compose
* **Versionamento:** Git com fluxo de Pull Request

---

## 🏁 Como Executar o Projeto (Docker)

A forma mais simples e recomendada de rodar este projeto é usando o Docker.

**Pré-requisitos:**
* [Docker](https://www.docker.com/products/docker-desktop/)
* [Docker Compose](https://docs.docker.com/compose/install/)

**Passos:**

1.  Clone este repositório:
    ```bash
    git clone https://github.com/PHVital/checkpoint-reserva-salas
    cd ResevaSalas
    ```

2.  Suba os containers da aplicação e do banco de dados:
    ```bash
    docker-compose up --build
    ```

3.  A API estará disponível em `http://localhost:8080`.

4.  Você pode usar o Postman ou seu navegador para testar os endpoints.

---

## 📋 Endpoints da API

A API está versionada com o prefixo `/api/v1`.

| Verbo HTTP | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/v1/salas` | Cria uma nova sala. |
| `GET` | `/api/v1/salas/disponiveis` | Lista todas as salas ativas. |
| `GET` | `/api/v1/salas/{id}` | Busca uma sala por ID. |
| `PUT` | `/api/v1/salas/{id}` | Atualiza uma sala. |
| `DELETE` | `/api/v1/salas/{id}` | Deleta uma sala. |
| `POST` | `/api/v1/reservas` | Cria uma nova reserva (com checagem de conflito). |
| `PATCH` | `/api/v1/reservas/{id}/cancelar`| Cancela uma reserva existente. |

---

## 🧪 Testes

O projeto inclui testes unitários para a camada de serviço (`ReservaService`), focando na validação das regras de negócio críticas.

* `deveCriarReserva_QuandoHorarioEstaLivre`
* `deveLancarExcecao_QuandoHouverConflito`
* `deveLancarExcecao_QuandoSalaInativa`

Para rodar os testes localmente (sem Docker), use o comando Maven:
```bash
mvn test
```
## Autor

* **Pedro Henrique Vital Guimarães**
* GitHub: [@PHVital](https://github.com/PHVital)
* LinkedIn: [Pedro Henrique Vital Guimarães](https://www.linkedin.com/in/pedro-henrique-vital-guimar%C3%A3es/)