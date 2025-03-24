import os
import sys
import socket


"""
name: users
kind: variable
type: dictionary
description: Dicionário para guardar apelido, IP e porta de todos os clientes conectados
"""
users = {}

##########################################################################################

"""
name: users
kind: variable
type: integeer
description: Variável que guarda a porta que o servidor abrirá. 
             O número da porta pode ser passado como argumento na execução do servidor.
             Exemplo python server.py 5005
             Se nenhum argumento for informado na execução do servidor, ele abrirá a porta 5000
"""
port = int(sys.argv[1]) if len(sys.argv) == 2 else 5000

##########################################################################################

"""
name: users
kind: variable
type: string
description: Variável que guarda o IP do servidor. Ela é usada tanto para criar o socket quanto para mostrar o IP do servidor no console. 
"""
server_ip_address = socket.gethostbyname(socket.gethostname())
    
##########################################################################################

"""
name: get_users_nickname
kind: function
arguments: 
    - none
return: void
description: Função que percorre a lista de usuário e mostra uma string (separada por ponto-e-virgula) contendo o nome dos usuários.
             Ela é usada pela função que faz o broadcast dos usuários que estão conectados.
"""
def get_users_nickname():
    result = ""
    for user in users:
        result += f"{user};"
    return result[:-1]

##########################################################################################

"""
name: open_server_port
kind: function
arguments: 
    - socket TCP
return: void
description: Funçao que faz cria o socket pra a comunicação com a camada de transporte
"""
def open_server_port():        
    socket_tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)        
    socket_tcp.bind((server_ip_address, port))
    socket_tcp.listen()
    return socket_tcp

##########################################################################################

"""
name: send_command_to_all_connected_users
kind: function
arguments: 
    - command
return: void
description: Função usada para fazer broadcast de um comando qualquer, ou seja, ela recebe um comando e o envia para todos os usuáros conectados.
             Ela é chamada quando um usuário é adicionado ou removido do chat.
"""
def send_command_to_all_connected_users(command):
    for user in users:        
        destination_ip_address = users[user]['client_address'][0]
        destination_port = int(users[user]['client_listening_port'])
        socketTCP = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        socketTCP.connect((destination_ip_address, destination_port))
        socketTCP.sendall(command)
        socketTCP.close()

##########################################################################################

"""
name: handle_register_command
kind: function
arguments: 
    - socket_client
    - client_address
return: void
description: Função que manipula o comando de inclusão de um usuário. 
             A função decompõe o comando, adiciona o usuário no dicionário "users" e notifica todos os usuários sobre a inclusão do usuário.
             Ver formato do comando na "Tabela 1: Adicionar usuário". 
"""
def handle_register_command(socket_client, client_address):
    client_listening_port = int(socket_client.recv(4).decode('utf-8'))
    nickname_size = int(socket_client.recv(2).decode('utf-8'))
    nickname = socket_client.recv(nickname_size).decode('utf-8')
    if (not nickname in users):
        users[nickname] = { 
            'nickname': nickname, 
            'client_address': client_address, 
            'client_listening_port': client_listening_port 
        }
    users_nickname = get_users_nickname()
    command_length = len(users_nickname) + 3 + 1
    response = f"0{command_length:04}{len(users_nickname):03}{users_nickname}".encode('utf-8')
    send_command_to_all_connected_users(response)
    print(f"User {nickname} registered")

##########################################################################################

"""
name: handle_unregister_command
kind: function
arguments: 
    - socket_client
return: void
description: Função que manipula o comando de remoção de um usuário. 
             A função decompõe o comando, remove o usuário no dicionário "users" e notifica todos os usuários sobre a remoção do usuário.
             Ver formato do comando na "Tabela 2: Remover registro usuário".
"""
def handle_unregister_command(socket_client, _):
    nickname_size = int(socket_client.recv(2).decode('utf-8'))
    nickname = socket_client.recv(nickname_size).decode('utf-8')
    if (nickname in users):
      del users[nickname]      
    response = f"1{nickname_size:02}{nickname}".encode('utf-8')
    send_command_to_all_connected_users(response)
    socket_client.close()
    print(f"User {nickname} unregistered")

##########################################################################################

"""
name: handle_send_command
kind: function
arguments: 
    - socket_client
return: void
description: Função que manipula o comando de envio de mensagens para outro usuário
             A função decompõe o comando, e envia a mensagem caso o destinatário esteja registrado no chat
             Ver formato do comando na "Tabela 3: Enviar mensagem".
"""
def handle_send_command(socket_client, _):
    source_size = int(socket_client.recv(2).decode('utf-8'))
    destination_size = int(socket_client.recv(2).decode('utf-8'))
    message_size = int(socket_client.recv(4).decode('utf-8'))    
    source = socket_client.recv(source_size).decode('utf-8')
    destination = socket_client.recv(destination_size).decode('utf-8')
    message = socket_client.recv(message_size).decode('utf-8')
    command_length = 4 + 2 + 4 + len(source) + len(message)
    command = f"2{command_length:04}{len(source):02}{len(message):04}{source}{message}".encode('utf-8')
    if (destination in users):
        destination_ip_address = users[destination]['client_address'][0]
        destination_port = int(users[destination]['client_listening_port'])
        socketTCP = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        socketTCP.connect((destination_ip_address, destination_port))
        socketTCP.sendall(command)
        socketTCP.close()
    socket_client.close()
    print(f"Message sent from {source} to {destination}")

##########################################################################################

"""
name: handle_invalid_command
kind: function
arguments: 
    - socket_client
return: void
description: Função que é chamada quando o servidor recebe um comando não suportado. 
             Ela apenas imprime um texto no console, o que significa que o client não sabe que o comando foi ignorado.
             Versões futuras do protocolo podem envolver uma resposta ao client em cenários como este.
"""
def handle_invalid_command(socket_client):
    print(f"Invalid command received from {socket.gethostname(socket_client)}")
    socket_client.close()
    
##########################################################################################

"""
name: select_command_handler
kind: function
arguments: 
    - command_type
return: function
description: Função que recebe o comando enviado pelo cliente. 
             Com o comando identificado, ela retorna a função adequada para executar o comando.
"""
def select_command_handler(command_type):
    if (command_type == b'0'):
        return handle_register_command
    if (command_type == b'1'):
        return handle_unregister_command
    if (command_type == b'2'):
        return handle_send_command    
    return handle_invalid_command

##########################################################################################

"""
name: listen_for_commands
kind: function
arguments: 
    - none
return: void
description: Função que fica escutando os comandos enviados pelos clientes.
             Ela basicamente chama uma função que retorna a função que processará o comando. 
             Em seguida ela chama tal função e fecha a conexão.
"""
def listen_for_commands():
    server_socket = open_server_port()
    while True:
        try:            
            socket_client, client_address = server_socket.accept()
            command_type = socket_client.recv(1)
            command_handler = select_command_handler(command_type)
            command_handler(socket_client, client_address)
            socket_client.close()
        except Exception as e:
            print(e)

##########################################################################################

"""
name: none
kind: block
description: Ponto de entrada da aplicação. 
"""
if __name__=="__main__":
    os.system('cls' if os.name == 'nt' else 'clear')
    print(f"Server listening on {server_ip_address}:{port}\n")
    listen_for_commands()