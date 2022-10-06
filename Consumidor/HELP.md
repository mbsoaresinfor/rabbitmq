Executar o RabbitMq via container:

docker run -d  --name rabbit13 -p 8080:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management

O RabbiMQ deve ter as seguintes exchanges:
"exchange.compra.fanout" do tipo fanout
"exchange.compra.direct" do tipo direct

O RabbiMQ deve ter as seguintes filas:
Duas filas denominadas (produto, email)

Apos criado as exchanges e filas, deve-se criar as routing-key para a exchange "exchange.compra.direct".
A "exchange.compra.fanout não necessita de routing-key.
EXCHANGE->FILA   = ROUTING-KEY
exchange.compra.direct -> produto = key.compra.produto
exchange.email.direct -> email = key.compra.email


Para executar a aplicação, deve ser passado os parametros (vm parametros) que informam a porta e fila a ser escutada:

-Dserver.port=8084 -Dqueue=email

-Dserver.port=8085 -Dqueue=produto


