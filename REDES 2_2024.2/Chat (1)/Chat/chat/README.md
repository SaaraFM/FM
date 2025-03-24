Instalação:
Clone o repositório ou copie os arquivos do projeto para o seu ambiente local.

Como executar o projeto:

*Passo 1: Executar o Servidor
Abra um terminal ou prompt de comando.
Navegue até o diretório onde o arquivo server.py está localizado.

Execute o seguinte comando para iniciar o servidor:
Copiar código
python server.py
O servidor escutará conexões na porta 5555 e exibirá no terminal as mensagens dos clientes conectados.
O servidor também enviará notificações para todos os clientes quando um novo cliente entrar ou sair do chat.

*Passo 2: Conectar um Cliente
Em um novo terminal, navegue até o diretório onde o arquivo client.py está localizado.

Execute o seguinte comando para iniciar o cliente:
Copiar código
python client.py
O cliente solicitará o nome de usuário. Digite um nome único para o usuário e pressione Enter.

O nome de usuário será enviado ao servidor, e todos os clientes conectados serão notificados da entrada de um novo usuário.
Após se conectar, o cliente poderá digitar mensagens e enviá-las pressionando Enter. Todas as mensagens serão enviadas ao servidor e retransmitidas a todos os clientes conectados.

Para sair do chat, basta digitar sair e pressionar Enter.

*Passo 3: Conectar Múltiplos Clientes
Para conectar mais clientes, repita o Passo 2 em novos terminais.
Cada novo cliente conectado será notificado com uma mensagem indicando que o usuário entrou no chat.
Mensagens enviadas por qualquer cliente serão recebidas por todos os outros clientes conectados.

Operações Suportadas:
1. LOGIN
O cliente envia seu nome de usuário ao servidor após a conexão.
O servidor notifica os outros usuários da entrada do novo cliente.
2. SEND
O cliente envia uma mensagem de texto para o servidor.
O servidor retransmite a mensagem para todos os outros clientes (broadcast).
3. RECEIVE
O cliente recebe e exibe as mensagens enviadas por outros clientes conectados.
4. LOGOUT
O cliente envia o comando "sair" para encerrar a conexão.
O servidor remove o cliente e notifica os outros usuários da saída.
Observações
O servidor roda indefinidamente até ser encerrado manualmente.
Caso um cliente se desconecte de forma inesperada, o servidor detecta a desconexão e remove o cliente da lista, notificando os demais usuários.
----------------------------------------------------------------------

Descrição do Funcionamento:
server.py
O código do servidor (server.py) é responsável por aceitar conexões de múltiplos clientes e gerenciar a comunicação entre eles. Aqui está uma visão geral detalhada:

Inicialização do Servidor:

O servidor cria um socket TCP usando socket.socket(socket.AF_INET, socket.SOCK_STREAM).
O socket é configurado para reutilizar o endereço local (socket.SO_REUSEADDR).
O servidor se vincula ao IP local ('0.0.0.0') e à porta 5555 usando server_socket.bind(('0.0.0.0', 5555)).
O servidor começa a escutar conexões com server_socket.listen(5), onde o número 5 define a quantidade de conexões pendentes permitidas.
Aceitação de Conexões:

O servidor entra em um loop infinito com while True para aceitar novas conexões.
Quando uma nova conexão é aceita, o servidor cria um novo socket para o cliente e obtém o endereço do cliente com server_socket.accept().
Para cada cliente conectado, o servidor inicia uma nova thread com threading.Thread que chama a função handle_client, passando o socket do cliente como argumento.
Gerenciamento de Clientes:

A função handle_client gerencia a comunicação com um cliente específico.
O cliente envia seu nome de usuário, que é armazenado no dicionário clients com o socket correspondente.
O servidor notifica todos os outros clientes sobre a nova entrada com broadcast(f"{username} entrou no chat!").
Em um loop, o servidor recebe mensagens do cliente, retransmite essas mensagens para todos os outros clientes com a função broadcast, e lida com a desconexão do cliente.
Desconexão e Notificação:

Quando um cliente desconecta, o servidor remove o cliente do dicionário clients, fecha o socket e notifica os outros clientes sobre a saída com broadcast(f"{username} saiu do chat.").
client.py
O código do cliente (client.py) permite a conexão com o servidor, envio e recebimento de mensagens. Aqui está uma visão geral detalhada:

Conexão ao Servidor:

O cliente cria um socket TCP com socket.socket(socket.AF_INET, socket.SOCK_STREAM) e se conecta ao servidor na porta 5555 com client_socket.connect(('localhost', 5555)).
Envio do Nome de Usuário:

Após conectar, o cliente envia o nome de usuário ao servidor usando client_socket.send(username.encode()).
Isso permite que o servidor identifique o cliente e notifique os outros usuários sobre a nova conexão.
Recebimento de Mensagens:

Uma thread separada é criada para receber mensagens do servidor usando a função receive_messages.
Dentro da função receive_messages, o cliente continuamente lê mensagens do socket com client_socket.recv(1024) e exibe essas mensagens na interface do usuário. Se o servidor fechar a conexão, a thread termina.
Envio de Mensagens:

No loop principal, o cliente permite que o usuário digite mensagens.
Se a mensagem digitada for "sair", o cliente fecha a conexão com o servidor e encerra o loop.
Caso contrário, o cliente envia a mensagem para o servidor com client_socket.send(message.encode()), que é então retransmitida para todos os outros clientes.
Desconexão:

Quando o usuário decide sair digitando "sair", o cliente fecha o socket com client_socket.close() e termina o programa.