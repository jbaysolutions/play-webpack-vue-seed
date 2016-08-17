# App Play Base JBay 

Aplicação Play base com autenticação, usando [deadbolt](http://deadbolt.ws/) e [play-authenticate](http://joscha.github.io/play-authenticate/)

## Requisitos

* JDK >= 1.8
* [Play! Framework 2.5.1](http://www.playframework.com/)
* [MySQL 5.6](http://www.mysql.com/)
* [PhantomJS (apenas para testes)](docs/howto_install_phantomjs.md)


## Para começar

Copiar directório conf/TEMPLATE para conf/LOCAL e modificar os vários ficheiros com as credenciais necessárias para envio de mails

Copiar conf/application-TEMPLATE.conf para conf/application.conf e modificar este ultimo com credenciais da DB (mudar <schema> para nome desejado do schema da DB) e links para os outros ficheiros de configuração

	db.default.driver=com.mysql.jdbc.Driver
	db.default.url="jdbc:mysql://localhost:3306/<schema>"
	db.default.user=<user>
	db.default.pass="<pass>"

    # Deadbolt
    include "LOCAL/deadbolt.conf"
    
    # SMTP
    include "LOCAL/smtp.conf"
    
    # And play authenticate
    include "LOCAL/mine.conf"


Criar schema <schema> no servidor de base de dados a usar:

	create schema <schema>


Abrir linha de comandos no directório do projecto e arrancar consola play:

	activator
	run

Abrir browser e aceder a http://localhost:9000

Fazer login com conta google. Se o email usado estiver na tabela user_invites o utilizador é criado automaticamente no primeiro login. 
Para editar a lista de invites alterar ficheiro *conf/initial-data.yml*