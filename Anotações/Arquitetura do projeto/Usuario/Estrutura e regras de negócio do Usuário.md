1. **Estilo Arquitetural Principal: Arquitetura Hexagonal (Ports and Adapters)**
    
    - O princípio fundamental foi isolar o "coração" da nossa aplicação (as regras de negócio) de detalhes externos como frameworks, banco de dados e a web. Isso garante que a lógica de negócio seja independente e altamente testável.
2. **Organização do Código: "Package-by-Feature" (Pacote por Funcionalidade)**
    
    - Em vez de agrupar o código por tipo técnico (ex: uma pasta para todos os controllers, outra para todos os services), nós o organizamos por **módulos de negócio**. Atualmente, temos o módulo `usuario` e o esqueleto dos módulos `autenticacao` e `shared`.
    - **Benefício:** Alta coesão (tudo sobre usuários está na pasta `usuario`) e baixo acoplamento (o módulo `usuario` não depende do futuro módulo `plantao`). Isso torna o sistema mais fácil de entender, manter e escalar.
3. **Camadas Dentro de Cada Módulo (Ex: `usuario`)**
    
    - **`domain` (O Coração):** Contém a lógica de negócio mais pura.
        - **Models (`Usuario`, `Medico`, `Hospital`):** Classes Java puras, sem anotações de frameworks, que representam os conceitos do negócio. Usamos herança para modelar os tipos de usuário.
        - **Ports (`UsuarioRepositoryPort`):** Interfaces que definem os **contratos** de que o domínio precisa para funcionar (ex: "preciso de um jeito de salvar um usuário"). O domínio depende dessas abstrações, não de implementações concretas.
    - **`application` (O Maestro):** Orquestra os casos de uso.
        - **Services (`UsuarioService`):** Contém a lógica da aplicação (ex: verificar se um e-mail já existe antes de criar, chamar o port para salvar).
        - **DTOs (Data Transfer Objects):** Objetos que carregam dados entre as camadas (ex: `CriarMedicoDTO`, `UsuarioCadastradoDTO`), garantindo que a API e o domínio não estejam rigidamente acoplados.
    - **`infrastructure` (Os Adaptadores):** Contém os detalhes de tecnologia que "se conectam" às portas do domínio.
        - **Adaptador de Entrada (Web):** O `UsuarioController` recebe requisições HTTP e chama o `UsuarioService`.
        - **Adaptador de Saída (Persistência):** O `UsuarioRepositoryAdapter` implementa a `UsuarioRepositoryPort` usando Spring Data JPA para se comunicar com o banco de dados PostgreSQL.
4. **Princípios e Ferramentas**
    
    - **SOLID:** As decisões de arquitetura foram guiadas pelos princípios SOLID, especialmente o de **Inversão de Dependência (DIP)**, que é a essência da Arquitetura Hexagonal.
    - **Segurança:** A base do Spring Security foi configurada, com uma política `stateless` e proteção CSRF desabilitada, preparando o terreno para autenticação via token.
    - **Ambiente:** Todo o ambiente de desenvolvimento (banco de dados PostgreSQL) está containerizado com **Docker**, garantindo consistência e facilidade de configuração.

---

### **Resumo das Regras de Negócio Implementadas**

Até agora, focamos e completamos a funcionalidade central de gerenciamento de usuários.

1. **Tipos de Usuário:**
    
    - O sistema diferencia claramente dois tipos de atores: **Médicos** e **Hospitais**, cada um com seus próprios campos de dados específicos (CRM/Especialidade para médicos, CNPJ/Razão Social para hospitais).
2. **CRUD Completo de Usuários:**
    
    - **Criação (Registro):** A API expõe endpoints públicos e distintos para registrar novos médicos (`POST /api/usuarios/medicos`) e hospitais (`POST /api/usuarios/hospitais`).
    - **Leitura:** É possível listar todos os usuários da plataforma (`GET /api/usuarios`) e buscar um usuário específico pelo seu ID (`GET /api/usuarios/{id}`).
    - **Atualização:** A API permite a atualização de dados de médicos e hospitais através de endpoints `PUT`.
    - **Remoção Lógica:** A remoção (`DELETE /api/usuarios/{id}`) é feita de forma lógica, alterando o status do usuário para `ativo = false`. Esta é uma regra de negócio importante para não perder o histórico e a integridade dos dados.
3. **Validações e Regras de Segurança:**
    
    - **Unicidade de E-mail:** O sistema não permite o cadastro de dois usuários com o mesmo endereço de e-mail.
    - **Segurança de Senha:** As senhas nunca são armazenadas em texto plano. Elas são sempre criptografadas usando o algoritmo **BCrypt** antes de serem salvas no banco de dados.
    - **Controle de Acesso:** Apenas os endpoints de registro são públicos. Todas as outras operações de gerenciamento de usuários (listar, atualizar, deletar) exigem autenticação, estabelecendo uma base segura para o futuro.