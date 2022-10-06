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


EndPoint para enviar mensagens para a exchange e filas especificas

ENVIA PARA TODAS AS FILAS (EMAIL, PRODUTO)

http://localhost:8081/produtor/exchange/{MENSAGEM}

ENVIA PARA A FILA EMAIL
http://localhost:8081/produtor/exchange/email/{MENSAGEM}

ENVIA PARA A FILA PRODUTO
http://localhost:8081/produtor/exchange/produto/{MENSAGEM}








