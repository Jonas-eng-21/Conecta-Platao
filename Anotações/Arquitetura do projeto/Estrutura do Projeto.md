Vamos organizar o código em torno dos **módulos de negócio** do seu sistema. Os principais módulos que já identificamos são `usuarios`, `autenticacao`, `plantoes`, etc. Dentro de cada um desses módulos, aplicaremos os princípios de uma arquitetura limpa (como a Hexagonal).

A ideia central é ter um **domínio** (o coração do negócio) puro e isolado, e a **infraestrutura** (tecnologias como Spring Web, JPA) como detalhes externos.

Aqui está a estrutura de pastas que eu recomendo para começarmos com os módulos de **`usuario`** e **`autenticacao`**:

```
/src/main/java/br/com/conectaplantao/api
|
├── ConectaPlantaoApiApplication.java
|
├── **usuario/** (MÓDULO DE USUÁRIOS)
│   ├── **domain/** (O Coração do Negócio - Sem Spring, Sem JPA)
│   │   ├── model/
│   │   │   ├── Usuario.java       (Classe base abstrata com campos comuns)
│   │   │   ├── Medico.java        (Herda de Usuario)
│   │   │   └── Hospital.java      (Herda de Usuario)
│   │   ├── enums/
│   │   │   └── TipoUsuario.java
│   │   └── ports/
│   │       └── UsuarioRepositoryPort.java (A INTERFACE/Contrato do Repositório)
│   │
│   ├── **application/** (Orquestração e Casos de Uso)
│   │   ├── dto/
│   │   │   ├── CriarMedicoDTO.java
│   │   │   ├── CriarHospitalDTO.java
│   │   │   └── UsuarioDetalhesDTO.java
│   │   └── service/
│   │       └── UsuarioService.java (Orquestra a criação de usuários)
│   │
│   └── **infrastructure/** (Detalhes de Tecnologia - Spring, JPA, Web)
│       ├── web/
│       │   └── controller/
│       │       └── UsuarioController.java (Endpoint para registro)
│       └── persistence/
│           ├── entity/
│           │   └── UsuarioPersistenceEntity.java (A entidade anotada com @Entity)
│           ├── repository/
│           │   └── SpringUsuarioRepository.java (Interface que estende JpaRepository)
│           └── UsuarioRepositoryAdapter.java (A IMPLEMENTAÇÃO da Porta)
│
├── **autenticacao/** (MÓDULO DE AUTENTICAÇÃO)
│   ├── application/
│   │   ├── dto/
│   │   │   ├── LoginRequestDTO.java
│   │   │   └── TokenResponseDTO.java
│   │   └── service/
│   │       └── AutenticacaoService.java
│   └── infrastructure/
│       └── web/
│           └── controller/
│               └── AutenticacaoController.java
│
└── **shared/** (ou common)
    └── infrastructure/
        └── security/
            ├── SecurityConfigurations.java
            ├── TokenService.java (Serviço de geração/validação de JWT)
            └── ...
```

---

### Por que esta Estrutura é Superior?

1. **SOLID na Prática:**
    
    - **SRP (Single Responsibility Principle):** Cada classe e módulo tem uma responsabilidade clara. O módulo `usuario` cuida de usuários. O `AuthController` cuida de autenticação.
    - **DIP (Dependency Inversion Principle):** O `domain` define as regras e as interfaces (`UsuarioRepositoryPort`). A `infrastructure` depende dessa abstração, e não o contrário. O negócio não sabe qual banco de dados você usa.
2. **Alta Coesão e Baixo Acoplamento:** Tudo relacionado a "Usuários" está dentro do pacote `usuario`. Se você precisar alterar algo sobre usuários, você sabe exatamente onde ir. O módulo de usuário não conhece o módulo de plantão.
    
3. **Testabilidade Extrema:** Você pode testar toda a sua lógica de negócio no `domain` sem precisar subir o contexto do Spring, pois são classes Java puras. Isso torna seus testes unitários extremamente rápidos e confiáveis.
    
4. **Pronto para Microserviços:** Se amanhã você decidir que o gerenciamento de usuários deve ser um serviço separado, você pode pegar a pasta inteira `/usuario`, movê-la para um novo projeto Spring Boot, e ela funcionará com pouquíssimos ajustes.
    
5. **Demonstra Senioridade:** Esta arquitetura (Package-by-Feature + Hexagonal) é um padrão moderno e altamente respeitado no mercado. Adotá-la no seu portfólio demonstra que você entende de design de software em um nível além do básico.