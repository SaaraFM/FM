import socket
import threading

def receive_messages(client_socket):
    while True:
        try:
            message = client_socket.recv(1024).decode()
            if message:
                print(message)
            else:
                break
        except:
            break

def start_client(username):
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(('localhost', 5555))
    
    client_socket.send(username.encode())  # Envia o nome de usuário ao servidor
    
    # Inicia a thread para receber mensagens
    thread = threading.Thread(target=receive_messages, args=(client_socket,))
    thread.start()

    while True:
        message = input()
        if message.lower() == "sair":
            client_socket.close()
            break
        else:
            client_socket.send(message.encode())

if __name__ == "__main__":
    username = input("Digite seu nome de usuário: ")
    start_client(username)
