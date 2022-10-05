Executar o RabbitMq via container:

docker run -d  --name rabbit13 -p 8080:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management

O RabbiMQ deve ter uma exchange do tipo fanout chamada "marcelo", e mais duas filas denominadas (produto, email).

Deve ser criado as seguintes routing-key

EXCHANGE->FILA   = ROUTING-KEY

marcelo->produto = marcelo-key-produto

marcelo->email   = marcelo-key-email


EndPoint para enviar mensagens para a exchange e filas especificas

ENVIA PARA TODAS AS FILAS (EMAIL, PRODUTO)

http://localhost:8081/produtor/exchange/{MENSAGEM}

ENVIA PARA A FILA EMAIL
http://localhost:8081/produtor/exchange/email/{MENSAGEM}

ENVIA PARA A FILA PRODUTO
http://localhost:8081/produtor/exchange/produto/{MENSAGEM}








