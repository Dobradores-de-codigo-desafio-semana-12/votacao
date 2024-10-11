<img src="compassUolLogoSvg.svg" width="40%"/>

# 💡 Sistema de Propostas

Este sistema permite que funcionários criem propostas e iniciem uma votação. O sistema é composto por quatro APIs: Eureka Server, API Gateway, API Funcionario e API Proposta.

## 👨🏽‍💻 Desenvolvedores

Os desenvolvedores deste projeto são:
+ Vagner Ferreira Lima Junior: [@vagnerflj](https://github.com/vagnerflj)
+ Jeferson Gomes da Silva: [@Jeferson5641](https://github.com/Jeferson5641)
+ Juliana Lourdes Ransani: [@Clexa97](https://github.com/Clexa97)


## 👨‍🏫 Instrutores

- **Rafael Henrique Menegon (P.O):** [@devrafamenegon](https://github.com/devrafamenegon)
- **Edmar Miller (P.O):** [@EdmarMiller](https://github.com/EdmarMiller)
- **Cleiton Fiatkoski Balansin (P.O):** [@CleitonFB](https://github.com/CleitonFB)
- **Mateus de Oliveira e Silveira (P.O):** [@mosilveira](https://github.com/mosilveira)
- **João Nicoli (P.O):** [@Joaonic](https://github.com/Joaonic)
- **Rafael Luz (P.O):** [@rafinhaLQ](https://github.com/rafinhaLQ)
- **Lucas Magno (P.O):** [@lucasmgn](https://github.com/lucasmgn)
- **Manoel Rosa (P.O):** [@guiguel](https://github.com/guiguel)

## 📁 Entrega

+ **Último commit:** até às 18h00 de 12/06/2024 (quarta-feira).
+ Os projetos devem estar disponíveis em um repositório no GitHub, configurado como "privado".
+ O repositório deve ter duas branches fixas: 'main' e 'dev', o restante das branches devem estar padronizadas com o formato: [nome-do-microserviço]/[feature]-[nome_da_funcionalidade], os commits devem ser semânticos.
+ Os instrutores devem ter acesso ao GitHub para revisão.

## 🤝 Prazo

O link do repositório deve ser enviado por e-mail até às 12hrs do dia 13/06/2024 (segunda-feira).

# ⚙️ Requisitos para Iniciar o Sistema

Para iniciar o sistema, você precisa ter o Docker instalado em sua máquina. Depois de instalá-lo, execute os seguintes comandos:

```bash
# Clona este repositório
$ git clone git@github.com:Dobradores-de-codigo-desafio-semana-12/votacao.git

# Acessa a pasta do projeto
$ cd votacao

# Inicia o sistema
$ docker-compose up
```

# 📈 API Eureka Server
A API Eureka Server é responsável por gerenciar as instâncias dos serviços no sistema. Você pode acessá-la em http://localhost:8761.

# 📈 API Gateway
A API Gateway é responsável por rotear as requisições para as APIs Funcionario e Proposta. Você pode acessá-la em http://localhost:8080.

# 📈 API Funcionario
A API Funcionario é responsável por gerenciar os funcionários no sistema. Você pode acessá-la em http://localhost:8081.

# 📈 API Propostas
A API Proposta é responsável por gerenciar as propostas no sistema. Você pode acessá-la em [http://localhost:8082](http://localhost:8082).

# ⚙️ Spring Actuator
O Spring Actuator fornece funcionalidades de monitoramento e gerenciamento para aplicações Spring. Você pode acessá-la em http://localhost:8082/actuator.

# 🚪 Endpoints do Actuator

Você pode acessar os seguintes endpoints do Actuator:

| Método   | Route                     | Descrição                               |
|----------|---------------------------|-----------------------------------------|
| GET      | /actuator                 | Lista todos os endpoints disponíveis    |
| GET      | /actuator/health          | Verifica a saúde da aplicação           |
| GET      | /actuator/metrics         | Obtém métricas da aplicação             |
| GET      | /actuator/info            | Exibe informações sobre a aplicação     |
| GET      | /actuator/env             | Exibe as propriedades de ambiente       |
| GET      | /actuator/loggers         | Gerencia os loggers da aplicação        |
| POST     | /actuator/loggers         | Configura loggers                       |

# 🚪 Endpoints Funcionarios disponíveis

| Método   | Route                     | Descrição                               |
|----------|---------------------------|-----------------------------------------|
| POST     | /funcionarios             | Cria um novo funcionário               |
| GET      | /funcionarios             | Obtém a lista de funcionários          |
| GET      | /funcionarios/{id}        | Obtém um funcionário pelo ID           |
| PUT      | /funcionarios/{id}        | Atualiza um funcionário pelo ID        |
| DELETE   | /funcionarios/{id}        | Exclui um funcionário pelo ID          |


# 🚪 Endpoints Propostas disponíveis

| Route                    | Method | Description                                    |
|--------------------------|--------|------------------------------------------------|
| POST /propostas          | POST   | Cria uma nova proposta                         |
| GET /propostas           | GET    | Obtém a lista de propostas                     |
| GET /propostas/{id}      | GET    | Obtém uma proposta pelo ID                     |
| PUT /propostas/{id}      | PUT    | Atualiza uma proposta pelo ID                  |
| DELETE /propostas/{id}   | DELETE | Exclui uma proposta pelo ID                    |
| POST /propostas/{id}/votar | POST  | Inicia uma votação para uma proposta pelo ID   |
