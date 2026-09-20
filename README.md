# Desafio Gerenciamento Contas Bancarias

## Descrição

Web API feita em Java 8 com o Spring Framework com foco na criação de relação entre entidade, sendo estas 
"Correntista", "Conta" e "Transacao", integrado com o banco de dados MySQL, Swagger OpenAPI para documentação e Git/Github para versionamento de códgio.

## Instruções de execução

1. Cópiar o schema.sql da pasta src/main/resources/db para dentro do Mysql;
2. Entrar no aplicativo de teste de API (Insomnia, Postman, etc...) ou acessar o endpoint do swagger disponibilizado na sessão de 'Endpoints'.
3. Inseririr os dados disponibilizados  na sessão de 'Dados JSON' dentro do campo data
4. Fazer a requisição HTTP seguindo a orientação na sessão de 'Endpoints'

## Endpoints

### Swagger
<hr>

#### http://localhost:8080/swagger-ui/index.html

<hr>

### Correntista
<hr>

#### Requisição Post

#### http://localhost:8080/correntista/add

#### Requisição Get

#### http://localhost:8080/correntista/{id}

#### http://localhost:8080/correntista/{nome}

<hr>

### Conta
<hr>

#### Requisição Post

#### http://localhost:8080/conta/add

#### Requisição Get

#### http://localhost:8080/conta/{id}

#### http://localhost:8080/conta/correntista/{id}
<hr>

### Transação
<hr>

#### Requisição Post

#### http://localhost:8080/transacao/add/solo

#### http://localhost:8080/transacao/add/duo

#### http://localhost:8080/transacao/add/rendimento/{taxa}

#### Requisição Get

#### http://localhost:8080/transacao/{id}

#### http://localhost:8080/transacao/destinatario/{id}

#### http://localhost:8080/transacao/remetente/{id}
<hr>

## Dados JSON

### Correntista/add
    {
	"nome" : "Nome Sobrenome",
	"documento" : "01234567890",
	"contato" : "01234567890"
    }
### conta/add
    {
	"numero" : "123",
	"saldo" : "100.50",
	"tipo" : "CONTA_CORRENTE",
	"correntista" : 1
    }  
****
    {
    "numero" : "123",
	"saldo" : "",
	"tipo" : "CONTA_POUPANCA",
	"correntista" : 2
    }
### transacao/add/solo
    {
	"tipo" : "DEPOSITO",
	"valor" : 100.00,
	"destinatario" : 1
    }
### transacao/add/duo
    {
	"valor" : 100,
	"remetente" : 1,
	"destinatario" : 2
    }
### transacao/add/rendimento/{taxa}
	{
	"tipo" : "RENDIMENTO",
	"destinatario" : 2
    }

