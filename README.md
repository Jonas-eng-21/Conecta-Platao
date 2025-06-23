# ConectaPlantão - API & Plataforma de Gestão de Plantões Médicos

## 📄 Breve Descrição
O ConectaPlantão é uma plataforma digital inovadora, desenvolvida para modernizar e otimizar a gestão de plantões médicos. O projeto visa substituir a desorganização de grupos de WhatsApp por um sistema centralizado, eficiente e seguro, conectando hospitais e médicos plantonistas de forma transparente.

## ✨ Funcionalidades Chave
- 👨‍⚕️ Gestão de Plantões Otimizada: Filtros avançados por especialidade, hospital e data, alertas personalizados e um sistema de agendamento intuitivo.
- 💬 Comunicação Centralizada: Chat interno para comunicação direta entre médicos e hospitais, eliminando a necessidade de apps externos.
- ⭐️ Sistema de Avaliação Mútuo: Construção de reputação e confiança através de feedback entre as partes.
- 📂 Gerenciamento de Documentos: Upload e validação de documentos essenciais (CRM, certificados) de forma segura.
- 📈 Histórico Completo: Acesso a um dashboard detalhado com todos os plantões realizados e transações financeiras.

## 🛠️ Tecnologias Utilizadas

A plataforma é construída sobre uma stack moderna e escalável, escolhida para garantir performance e manutenibilidade.

### Back-end
- Java 17: Versão LTS do Java, garantindo estabilidade e acesso a recursos modernos da linguagem.
- Spring Boot 3: Framework robusto para a criação rápida de APIs RESTful seguras e eficientes.
- Spring Security: Implementação de autenticação e autorização baseada em JWT (JSON Web Tokens).
- Spring Data JPA: Camada de persistência de dados com o banco de dados relacional.
- RabbitMQ: Message broker utilizado para a comunicação assíncrona, essencial para o sistema de notificações e chat em tempo real.
  
### Front-end
- Next.js: Framework React para a construção de interfaces de usuário reativas, performáticas e com renderização no lado do servidor (SSR).
- Tailwind CSS: Framework de estilização CSS "utility-first" para a criação de designs modernos e responsivos de forma ágil.  

### Banco de Dados

- PostgreSQL: Banco de dados relacional de código aberto, conhecido por sua robustez, confiabilidade e performance.
  
### DevOps & Infraestrutura
- Docker & Docker Compose: Containerização da aplicação e de seus serviços (banco de dados, message broker) para garantir um ambiente de desenvolvimento e produção consistente.
- CI/CD com GitHub Actions: Automação de testes, build e análise estática de código a cada novo commit.
- Vercel: Plataforma de deploy para o front-end, com integração contínua e otimizações automáticas para projetos Next.js.
  
## 🏛️ Arquitetura e Boas Práticas
*A qualidade do código e a solidez da arquitetura são pilares deste projeto.*

- Domain-Driven Design (DDD): O back-end é modelado em torno do domínio de negócio, com a lógica central protegida de detalhes de infraestrutura.
- Arquitetura Hexagonal (Ports and Adapters): A estrutura do back-end isola o "core" da aplicação, permitindo que tecnologias como banco de dados e frameworks web sejam "adaptadores" facilmente substituíveis.
- Princípios SOLID: O código é escrito seguindo os cinco princípios SOLID para criar um software mais compreensível, flexível e de fácil manutenção.
- Testes Automatizados: Cobertura de testes unitários e de integração utilizando JUnit, Mockito e RestAssured para garantir a confiabilidade das regras de negócio e dos endpoints da API.
- Monorepo: O projeto está estruturado em um monorepo, facilitando a gestão integrada do código do back-end e front-end em um único lugar.

## 🚀 Como Executar o Projeto
*Para executar a aplicação localmente, siga os passos abaixo.*

**Pré-requisitos**  
Git | Java 17+ | Maven 3.8+ | Node.js 18+ | Docker e Docker Compose  

### Passos  
1. Clone o repositório:
```
git clone https://github.com/Jonas-eng-21/Conecta-Platao.git
```
2. Inicie os serviços de infraestrutura (Banco de Dados e RabbitMQ):
```
docker-compose up -d
```
3. Execute o Back-end
```
cd backend
./mvnw spring-boot:run
```
4. Execute o Front-end
```
cd frontend
npm install
npm run dev
```
