import socket
import threading

# Dicionário para armazenar os clientes conectados (nome de usuário -> socket)
clients = {}

# Função para transmitir mensagens para todos os clientes
def broadcast(message, sender_socket=None):
    for client_socket in clients.values():
        if client_socket != sender_socket:
            try:
                client_socket.send(message.encode())
            except Exception as e:
                print(f"Erro ao enviar mensagem: {e}")
                client_socket.close()

# Função que lida com cada cliente individualmente
def handle_client(client_socket):
    try:
        # Recebe o nome de usuário do cliente
        username = client_socket.recv(1024).decode()
        clients[username] = client_socket
        print(f"{username} conectou.")
        
        broadcast(f"{username} entrou no chat!")
        
        while True:
            # Recebe as mensagens do cliente
            message = client_socket.recv(1024).decode()
            if message:
                print(f"{username}: {message}")
                broadcast(f"{username}: {message}", sender_socket=client_socket)
            else:
                break
    
    except Exception as e:
        print(f"Erro: {e}")
    
    finally:
        # Remove o cliente quando ele sair
        client_socket.close()
        del clients[username]
        broadcast(f"{username} saiu do chat.")
        print(f"{username} desconectado.")

# Função principal para iniciar o servidor
def start_server():
    # Cria o socket do servidor (IPv4, TCP)
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    
    # Reutiliza o endereço em caso de reinicialização rápida
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    
    # Associa o socket ao IP local e porta 5555
    server_socket.bind(('0.0.0.0', 5555))
    
    # Habilita o servidor para escutar até 5 conexões pendentes
    server_socket.listen(5)
    print("Servidor iniciado. Aguardando conexões...")
    
    while True:
        try:
            # Aceita novas conexões de clientes
            client_socket, client_address = server_socket.accept()
            print(f"Nova conexão de {client_address}")
            
            # Cria uma thread para gerenciar o cliente
            thread = threading.Thread(target=handle_client, args=(client_socket,))
            thread.start()
        
        except Exception as e:
            print(f"Erro ao aceitar conexão: {e}")

if __name__ == "__main__":
    start_server()
