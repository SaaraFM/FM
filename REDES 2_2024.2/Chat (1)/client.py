import os
import sys
import socket
import keyboard
from form import execute_form, draw_form
from menu import execute_menu, draw_menu
from screen import draw_screen, draw_title
from threading import Thread
from time import sleep 

"""
name: users
kind: variable
type: list
description: Guarda o apelido dos demais usuários conectados ao chat
"""
users = []

##########################################################################################

"""
name: my_users
kind: variable
type: string
description: Guarda o apelido do usuário do client que está em execução
"""
my_user = ""

##########################################################################################

"""
name: messages
kind: variável
type: dictionary
description: Guarda o histórico de mensagens entre o client que está em execução e os demais usuários do chat.
             O servidor não guarda tal histórico de modo que apenas as duas pontas envolvidas na conversa possuem tal registro.
             O histórico não é persistido, portanto, quando a aplicação é fechada, o histórico se perde.
"""
messages = {}

##########################################################################################

"""
name: selected_destination_user
kind: variável
type: string
description: Guarda o apelido do usuário com quem o client está conversando no momento atual
"""
selected_destination_user = ""

##########################################################################################

"""
name: client_port
kind: variável
type: integer
description: Inteiro que guarda a porta que client usará para receber mensagens do server.
             A porta pode ser informada como parâmetro na chamada da aplicação.
             Se nenhuma porta for informada, a porta 5001 será assumida
"""
client_port = int(sys.argv[1]) if len(sys.argv) == 2 else 5001

##########################################################################################

"""
name: client_port
kind: variável
type: integer
description: String que guarda o IP do client apenas por conveniência.
"""
client_ip_address = socket.gethostbyname(socket.gethostname())

##########################################################################################

"""
name: client_port
kind: variável
type: integer
description: Variável de controle que permite quebrar o loop principal da aplicação.
"""
running = True

##########################################################################################

"""
name: insert_message_in_history
kind: function
arguments: 
    - user: o usuário remoto que enviou a mensagem ou que receberá a mensagem
    - message: a mensagem que foi recebida ou será enviada
return: void
description: Sempre que uma mensagem é recebida de outro usuário ou enviada para outro usuário, essa função é chamada.
             Ela insere a nova mensagem no topo da lista de mensagens do usuário com que se está conversando.
             A variável global "messages" tem o papel de guardar o histórico de mensagens entre o presente client e os demais usuários.
"""
def insert_message_in_history(user, message):
    if user in messages:
        messages[user].insert(0, message)
    else:
        messages[user] = [message]

##########################################################################################

"""
name: open_server_port
kind: function
arguments: 
    - none
return: socket TCP
description: Função que cria um socket TCP para receber os comandos do servidor
"""
def open_server_port():
    socket_tcp = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    socket_tcp.bind((client_ip_address, client_port))
    socket_tcp.listen()
    return socket_tcp

##########################################################################################

"""
name: handle_register_command
kind: function
arguments: 
    - command: detalhes do comando, tais como destinatario, mensagem e etc
return: void
description: Função que é chamada quando o client é notificado sobre a entrada de um novo usuário no chat. 
             Ela basicamente insere o usuário na lista de usuários conectados e atualiza a tela.
"""
def handle_register_command(command):
    decoded_command = command.decode('utf-8')
    connected_users = decoded_command[3:].split(";")
    users.clear()
    for user in connected_users:
        if (user != my_user):
            users.append(user)
    update_screen()

##########################################################################################

"""
name: handle_invalid_command
kind: function
arguments: 
    - command: detalhes do comando, tais como destinatario, mensagem e etc
return: void
description: Função que é chamada quando o client é notificado sobre a saída de um usuário no chat. 
             Ela basicamente remove o usuário na lista de usuários conectados e atualiza a tela.
"""
def handle_unregister_command(command):
    decoded_command = command.decode('utf-8')
    disconnected_user = decoded_command
    if (disconnected_user == my_user):
        raise Exception(f"{my_user} desconectado")

##########################################################################################

"""
name: handle_send_command
kind: function
arguments: 
    - command: detalhes do comando, tais como destinatario, mensagem e etc
return: void
description: Função que é chamada quando o client é notificado sobre a chegada de uma nova mensagem
             Ela basicamente registra a nova mensagem no histórico atualiza a tela.
             Importante: o client não possui um sistema de notificações para te informar sobre a chegada de novas mensagens.
             Então, se você receber uma mensagem do usuário "A" enquanto conversa com o usuário "B", 
             só saberá sobre da mensagem de "A" quando selecionar "A" na lista de usuários.
"""
def handle_send_command(command):
    decoded_command = command.decode('utf-8')
    source_size = int(decoded_command[:2])
    source = decoded_command[6:6 + source_size]
    message = decoded_command[6 + source_size:]
    insert_message_in_history(source, message)
    update_screen()

##########################################################################################

"""
name: handle_invalid_command
kind: function
arguments: 
    - command: detalhes do comando, tais como destinatario, mensagem e etc
return: void
description: Função simples que apenas lida eventuais comandos inválidos enviados pelo servidor.
             Ela simplesmente mostra o comando inválid no console, mas em tese ela nunca deve ser chamada.
"""
def handle_invalid_command(command):
    print("\033[0;40H", f"Comando inválido {command}")
    
##########################################################################################

"""
name: select_command_handler
kind: function
arguments: 
    - command_type: "0-inclusão de usuário", "1-remoção de usuário" ou "2-envio de mensagens"
return: function
description: Função que recebe o comando enviado pelo servidor. 
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
name: get_server_connection_data
kind: function
arguments: 
    - none
return: dictionary
description: Executa um formulário que solicita o IP do servidor, a porta do servidor e o apelido do usuário que está executando o client. 
             Com o formulário preenchido, a função retorna um dicionário contendo o valor dos três campos.
"""
def get_server_connection_data():
    return execute_form("Conecte-se a um servidor de chat", ["IP do servidor", "Porta do servidor", "Seu apelido"], 80, 1, 1)

##########################################################################################

"""
name: send_command_to_server
kind: function
arguments: 
    - server_connection_data: dicionário que possui o IP e a porta do servidor
    - command: string que possui os comandos que serão enviados pro server
return: void
description: Conecta-se no servidor e envia o comando recebido por parâmetro
"""
def send_command_to_server(server_connection_data, command):
    server_ip_address = server_connection_data["IP do servidor"]
    server_port = int(server_connection_data["Porta do servidor"])
    socketTCP = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    socketTCP.connect((server_ip_address, server_port))
    socketTCP.sendall(command.encode('utf-8'))    
    socketTCP.close()

##########################################################################################

"""
name: connect_to_server
kind: function
arguments: 
    - server_connection_data: dicionário que possui o IP e a porta do servidor
return: o apelido que foi escolhido pelo usuário do client que está em execução.
description: Monta o comando para registro de usuários (ver "Tabela 1: Adicionar usuário").
             Envia o comando ao servidor e retorna o apelido do usuário que foi registrado.
"""
def connect_to_server(server_connection_data):    
    nickname = server_connection_data["Seu apelido"]
    command = f"0{client_port:04}{len(nickname):02}{nickname}"
    send_command_to_server(server_connection_data, command)
    return nickname

##########################################################################################

"""
name: send_message_to_another_user
kind: function
arguments: 
    - server_connection_data: dicionário que possui o IP e a porta do servidor 
    - form_data: que possui a mensagem que será enviada para o outro usuário
return: void
description: Monta o comando para o envio de mensagem a outro usuário (ver "Tabela 3: Enviar mensagem").
             Envia o comando ao servidor, insere a mensagem no histórico e atualiza a tela para que ela renderize a nova mensagem na tela.
"""
def send_message_to_another_user(server_connection_data, form_data):
    message = form_data["Mensagem"]
    command = f"2{len(my_user):02}{len(selected_destination_user):02}{len(message):04}{my_user}{selected_destination_user}{message}"
    send_command_to_server(server_connection_data, command)
    insert_message_in_history(selected_destination_user, message)
    update_screen()

##########################################################################################

"""
name: update_screen
kind: function
arguments: 
    - none
return: void
description: Função que limpa a tela e chama funções para desenhá-la novamente. 
             Ela poderia ser otimizada para redesenhar apenas as partes modificadas, 
             mas seria muito preciosismo para um trabalho cujo foco não é a programação.
             Observação: Ela só desenha a tela se existir ao menos 1 outro usuário no chat.
"""
def update_screen():
    os.system('cls' if os.name == 'nt' else 'clear')
    if (len(users) <= 0):
        print("Nenhum usuário conectado.\n")
        print("Aguardando a conexão de outros usuários...")
    else:                
        if selected_destination_user in messages:
            content = messages[selected_destination_user]  
        else: 
            content = ""        
        draw_screen(f"Histório de conversa com {selected_destination_user}", "", content, 20, 80, 1, 7)
        draw_title("Pressione ESC para sair", 110, 1, 31)
        draw_menu("Usuários (F12)", users, selected_destination_user, 20, 27, 84, 7)
        draw_form(f"[{my_user}] -> Enviar mensagem", ["Mensagem"], 110, 1, 2)        

##########################################################################################

"""
name: listen_input_from_user
kind: function
arguments: 
    - server_connection_data: dicionário que possui o IP e a porta do servidor
return: void
description: Escuta a porta aberta para receber os comandos do servidor. 
             Quando um comando é recebido, seleciona-se a função que irá processá-lo e chama-se tal.
             Essa função possui um loop infinito e roda dentro de uma thread.
             A chamada da função sleep serve para que a presente função não sequestre todo o tempo de processamento para si própria.
"""
def listen_for_messages_from_server():    
    server_socket = open_server_port()
    while running:
        try:            
            socket_client, _ = server_socket.accept()
            command_type = socket_client.recv(1)
            command_length = int(socket_client.recv(4))
            command = socket_client.recv(command_length)
            command_handler = select_command_handler(command_type)
            command_handler(command)
            socket_client.close()
            sleep(0.1)
        except Exception as e:
            draw_title(e, 110, 1, 1)
            os.system('cls' if os.name == 'nt' else 'clear')
            sleep(1)

##########################################################################################

"""
name: listen_input_from_user
kind: function
arguments: 
    - server_connection_data: dicionário que possui o IP e a porta do servidor
return: void
description: Executa um formulário que solicita ao usuário a mensagem que deve ser enviada. 
             Com a mensagem em mãos, a função chama uma rotina que a envia para o destinatário previamente escolhido.
             Essa função possui um loop infinito e roda dentro de uma thread.
             A chamada da função sleep serve para que a presente função não sequestre todo o tempo de processamento para si própria.
"""
def listen_input_from_user(server_connection_data):
    while running:
        try:
            form_data = execute_form(f"[{my_user}] -> Enviar mensagem ", ["Mensagem"], 110, 1, 2)
            send_message_to_another_user(server_connection_data, form_data)
            sleep(0.1)
        except Exception as e:
            draw_title(e, 110, 1, 1)
            os.system('cls' if os.name == 'nt' else 'clear')
            sleep(1)

##########################################################################################

"""
name: none
kind: block
description: Ponto de entrada da aplicação. Explicações sobre o funcionamento deste bloco podem ser encontrados nos comentários inline abaixo
"""
if __name__=="__main__":
    #limpa a tela
    os.system('cls' if os.name == 'nt' else 'clear')
    
    #chama o formulário que solicita os dados para conexão com o servidor
    server_connection_data = get_server_connection_data()
    
    #chama a função que cria o socket TCP que será usado na comunição com o servidor
    my_user = connect_to_server(server_connection_data)    
    
    #inicia a thread que ficará escutando comandos do servidor.
    msg_from_server_thread = Thread(target = listen_for_messages_from_server)
    msg_from_server_thread.start()

    #inicia a thread que ficará escutando o teclado do usuário, permitindo que este digite uma mensagem ao mesmo tempo que a aplicação escuta comando do servidor
    input_from_user_thread = Thread(target = listen_input_from_user, args=(server_connection_data, ))
    input_from_user_thread.start()    

    #atualiza a tela, renderizando o formulário de digitação de mensagens, a lista de usuários e o histórico de mensagens.
    update_screen()
    
    #loop que mantém a aplicação rodando
    while running:
        try:
            #quando o primeiro usuário entra no chat, o cliente automaticamente o seleciona como destinatário atual
            if len(users) == 1 and selected_destination_user != users[0]:
                selected_destination_user = users[0]
                update_screen()
            
            #se o usuário pressionar F12, um menu será exuctado onde o usuário poderá selecionar outro destinario para suas mensagens
            if keyboard.is_pressed("F12"):
                selected_destination_user = execute_menu("Usuários (F12)", users, selected_destination_user, 20, 27, 84, 7)
                update_screen()
            
            #se o usuário pressionar ESC, uma mensagem de desconexão é enviada ao servidor e variável que mantém o loop infinito é atualizada.
            if keyboard.is_pressed("ESC"):                
                command = f"1{len(my_user):02}{my_user}"
                send_command_to_server(server_connection_data, command)
                running = False
            
            #a chamada da função sleep serve apenas para que esse loop não sequestre todo o processamento para si.
            sleep(0.1)
        except Exception as e:
            draw_title(e, 110, 1, 1)
            os.system('cls' if os.name == 'nt' else 'clear')
            sleep(1)