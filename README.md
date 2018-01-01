## Projeto Caixa Eletrônico ##

## Contribuidores

Contribuidores do projeto:
- Eduardo A. Di Nizo - eduardodinizo@hotmail.com

## Problema

Desenvolver uma aplicação que simule a entrega de notas quando um cliente efetuar um saque em um caixa eletrônico. Os requisitos básicos são os seguintes:

- Entregar o menor número de notas;

- É possível sacar o valor solicitado com as notas disponíveis;
- Saldo do cliente será cadastro; 
- Quantidade de notas infinito Notas disponíveis de R$ 100,00; R$ 50,00; R$ 20,00 e R$ 10,00.
- O Cliente não poderá entrar no negativo.
- Saque garantir que apenas que no máximo 5 usuários realizem o saque ao mesmo tempo.

Exemplos:

Valor do Saque: R$ 30,00 – Resultado Esperado: Entregar 1 nota de R$20,00 e 1 nota de R$ 10,00.

Valor do Saque: R$ 80,00 – Resultado Esperado: Entregar 1 nota de R$50,00 1 nota de R$ 20,00 e 1 nota de R$ 10,00

Observações:

Cadastro , Edição e Exclusão de Clientes ( saldo de cada cliente será cadastrado junto ) 

## Solução

O projeto foi desenvolvido com a tecnologia Java utilizando os frameworks Spring e a arquitetura REST como base
 para disponibilizar os serviços web necessários para solução do problema. 
 
 Para disponibilizar a aplicação pela <b>URL: localhost:8080 </b> foi utilizado o Spring Boot. 
  
 Para garantir que apenas que no máximo 5 usuários
  realizem o saque ao mesmo tempo, foi utilizado o Spring Websocket para controlar o acesso a tela de saque
  permitindo que apenas 5 usuários acessem esse serviço por vez. 
  
 Para autenticação e segurança da API foi utilizado o Spring Security para controlar as requisições  e a 
 biblioteca JWT para gerar e verificar o token utilizado das requisições HTTP.
 
 Para armazenar no banco de dados Mysql foi utilizado o Spring JPA e o Hibernate
 para mapeamento do modelo relacional e persistencia dos dados.
 
 Foi utilizado a ferramenta Liquibase para criar o banco de dados e persistir o usuario default no banco.
 Usuário: <b>eduardo</b> e senha <b>eduardo</b>.
 
 Para garantir a qualidade e integridade da aplicação foram criados testes de <b>integração</b> e teste de
 <b>unidade</b> dos serviços e endpoints utilizando o Spring Test, JUnit e MockMvc.   
   

##Tecnologias:

### Back-end
- Java 8
- Spring-boot:1.5.9.RELEASE
- Spring-security:1.5.9.RELEASE
- Spring-web:1.5.9.RELEASE
- Spring-test:1.5.9.RELEASE
- Spring-data-jpa:1.5.9.RELEASE
- Spring-websocket:1.5.9.RELEASE
- Jsonwebtoken:jjwt:0.4
- Jackson-databind:2.8.9
- Liquibase-core:3.5.3
- Lombok:1.16.18
- Mysql-connector-java:5.1.6
### Front-end
- angular: 1.6.4,
- angular-animate: 1.6.4,
- jquery: 3.1.0,
- angular-toastr: 2.1.1,
- materialize: 0.98.2,
- components-font-awesome: 4.7.0,
- angular-gridster: 0.13.15,
- angular-ui-router: 1.0.6,
- angular-cookies: 1.6.6,
- angular-environment: 1.0.8,
- sweetalert: 1.1.3,
- ngSweetAlert: angular-sweetalert#1.1.2,
- stomp: stomp-websocket#~2.3.4,
- angular-ui-mask: 1.8.7

### Executando a aplicação
- Para executar o projeto é necessário ter o banco de dados MySql instalado no host. Caso você tenha o docker
instalado em sua maquina, editar o arquivo e executar o seguinte comando:
```shell
#docker-compose mysql
version: '3.2'
services:
  mysql:
    image: "mysql:5.7"
    container_name: mysql
    ports:
      - "3306:3306"
    restart: always
    volumes:
      - mysql_db:/DIRETORIO/PARA/BANCO DE DADOS/mysql/:rw
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: cashmachine
volumes:
  mysql_db:
```
```shell
cd [diretório docker-compose.yaml]
sudo docker-compose up -d
```

- Caso queira alterar o usuário e senha do banco de dados, altere seguintes atributos 
no arquivo application.properties:
```shell
#application.properties
liquibase.user=root
liquibase.password=root
spring.datasource.username=root
spring.datasource.password=root
```