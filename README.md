# Email handler

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

## 💻 Sobre
Este é um projeto que foi desenvolvido envolvendo a criação de um "microsserviço" do tipo translate service para o envio de e-mails utilizando 
conceitos da arquitetura limpa, este projeto é apenas experimental, serve somente para estudos.

## 🤖 Tecnologias
* [Spring Boot (Web, Mail, Data JPA, Validation, Devtools, AMQP)](https://docs.spring.io/spring-framework/reference/index.html)
* [Docker](https://docs.docker.com)
* [Maven](https://maven.apache.org)
* [Hibernate](https://hibernate.org)
* [PostgreSQL](https://www.postgresql.org)
* [Lombok](https://projectlombok.org)

## 🚀 Setup
Primeiramente, caso queira configurar com credenciais fora do valor padrão definido, você deverá modificar algumas variáveis de ambiente no arquivo
`src/main/resources/application.properties`, como essas:
```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST:localhost}:${DB_PORT:5432}/${DB_NAME:email_handler}?createDatabaseIfNotExist=true
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSW:admin}

spring.mail.username=${GMAIL_SENDER_USER}
spring.mail.password=${GMAIL_SENDER_PASSW}

spring.rabbitmq.host=${RABBITMQ_HOST:localhost}
spring.rabbitmq.username=${RABBITMQ_USER:guest}
spring.rabbitmq.password=${RABBITMQ_PASSWORD:guest}
```
- *DB_USERNAME*: Esta tem o valor de "postgres" que é o username padrão do PostgreSQL, serve para se conectar ao banco, este valor é passado caso não 
tenha configurado nenhuma variável de ambiente.
- *DB_PASSW*: Serve para se conectar ao banco junto com o username e tem seu valor padrão definido como admin caso não 
seja passado nenhuma variável de ambiente.
- *DB_NAME*: Este define o nome do banco de dados que a aplicação tentará se conectar ao iniciar.
- *GMAIL_SENDER_USER*: Aqui será informado o e-mail rementente para o envio do que deseja.
- *GMAIL_SENDER_PASSW*: Neste campo, será passado a senha para apps gerada de 16 digitos e não a senha do seu e-mail,
para mais informações de como cria-lá, acesse este [link](https://support.google.com/accounts/answer/185833).

Este projeto já possui uma imagem no [dockerhub](https://hub.docker.com/r/crystianlefundes/email-handler/tags), caso seja do seu interesse utilizar, basta que utilize a 
imagem passando as variáveis de ambiente acima como parâmetro, assim que seu container estiver de pé, basta que 
envie suas mensagens para uma fila chamada de email. 

## 🤔 Como funciona
Rodando a aplicação, seu banco de dados e o rabbitmq, você irá enviar uma requisição do tipo POST para sua API com o seguinte
formato de requisição JSON:
```json
{
  "ownerRef": "Crystian",
  "emailTo": "******@gmail.com",
  "subject": "Título do e-mail",
  "body": "Corpo do e-mail"
}
```
- ownerRef: Este campo é referente por enquanto ao nome de quem está enviando o e-mail, somente para controle.
- emailTo: Aqui será colocado para quem você deseja enviar este e-mail, seu destinatário.
- subject: Como já implica no exemplo, é o título do e-mail, o assunto.
- body: Assim como no exemplo já diz, este é o campo do corpo do e-mail que será enviado.

Com o envio deste requisição, primeiro o e-mail será montado e enviado através do SMTP(Simple Mail Transfer Protocol) 
do Gmail, por fim, será salvo na tabela emails, com sucesso ou não.

### 🏛 Arquitetura
A arquitetura usada no projeto é baseada em Clean Architecture e ela tem este formato:
- Core: Pasta onde é encontrado a entidade, neste caso, o e-mail, também tem seus casos de uso, ou regras de negócio e 
DTO (data transfer object).
- Application: Neste diretório é onde é montado os serviços com base nos casos de uso da entidade, neste caso, lá
poderá ser encontrado o `EmailService` que implementa os métodos assinados na interface de regra de negócio `EmailUseCase`.
- Adapter: Diretório que fornece interfaces para que tudo que chegar de infra seja adaptado para funcionar na camada da
aplicação.
- Repository: Parte onde fornecerá uma interface que conectará ao banco de dados e entregar os métodos para a camada de
aplicação.
- Infra: Por último temos a camada de infraestrutura, nela teremos todo código externo a aplicação somente tendo que se conectar
através da camada de Adapter, como exemplo, temos o nosso serviço de envio de e-mail externo, sendo assim, se eu quiser
modificar para Amazon SES, basta que eu crie a classe e implemente a interface e seus métodos necessários para a conexão
com a aplicação.

## 📝 Referencias

Links de refência para a criação do projeto:

[Desafio UBER](https://github.com/uber-archive/coding-challenge-tools/blob/master/coding_challenge.md),
[Fernanda Kipper](https://github.com/Fernanda-Kipper/desafio-backend-uber/tree/main),
[Michelli Brito](https://github.com/MichelliBrito/microservices-na-pratica/tree/main).
