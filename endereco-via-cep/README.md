# Endereço ViaCEP - Spring Boot + Thymeleaf + Bootstrap

Projeto exemplo que implementa um CRUD de endereços usando **Spring Boot**, **Thymeleaf**, **Bootstrap** e integração com a API pública **ViaCEP** (https://viacep.com.br).

## Funcionalidades
- Listar endereços
- Cadastrar novo endereço (validação do CEP via ViaCEP)
- Editar endereço
- Excluir endereço
- Banco em memória H2 (pode ser alterado para MySQL/Postgres)
- Validações com Jakarta Validation (formulário)
- Preenchimento automático de logradouro, bairro, cidade e UF a partir do ViaCEP

## Como rodar
Requisitos: Java 17+, Maven.

1. Descompacte o arquivo.
2. No diretório do projeto rode:
```bash
mvn spring-boot:run
```
3. Abra `http://localhost:8080` no navegador.
4. Para acessar o console H2, vá em `http://localhost:8080/h2-console` e use a URL padrão `jdbc:h2:mem:endereco-db`.

## Observações
- A aplicação consulta a API pública do ViaCEP. Se estiver sem internet, as consultas de validação e preenchimento não funcionarão.
- Para usar MySQL/Postgres altere `src/main/resources/application.properties` com as configurações do seu banco e dependências no `pom.xml`.

## Estrutura principal
- `Endereco` (entity)
- `EnderecoController` (controller)
- `EnderecoService` (serviço de negócio)
- `ViaCepService` (integração com ViaCEP)
- Templates Thymeleaf em `src/main/resources/templates`

## Prints
Inclua prints das telas depois de rodar a aplicação localmente.

Bom estudo — se quiser, eu adapto para MySQL/Postgres ou adiciono autenticação.
