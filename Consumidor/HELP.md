Executar o RabbitMq via container:

docker run -d  --name rabbit13 -p 8080:15672 -p 5672:5672 -p 25676:25676 rabbitmq:3-management

O RabbiMQ deve ter uma exchange do tipo direct chamada "marcelo", e mais duas filas denominadas (produto, email).

Deve ser criado as seguintes routing-key

EXCHANGE->FILA   = ROUTING-KEY

marcelo->produto = marcelo-key-produto

marcelo->email   = marcelo-key-email

marcelo->produto = marcelo-key

marcelo->email   = marcelo-key

Para executar a aplicação, deve ser passado os parametros (vm parametros) que informam a porta e fila a ser escutada:

-Dserver.port=8084 -Dqueue=email

-Dserver.port=8085 -Dqueue=produto


