Sistema de Calculadora
Este projeto é um sistema de calculadora simples que utiliza duas pilhas: uma para armazenar valores reais e outra para operadores. O sistema permite empilhar números, empilhar operadores, realizar cálculos entre os números empilhados e imprimir o estado atual das pilhas.

Estrutura do Projeto: 
O projeto está estruturado da seguinte forma: Src
principal
Java
calculadora
Calculadora.java
ListaEncadeadaGenerica.java
PilhaGenerica.java
TestaCalculadora.java

Descrição das Classes e Métodos:

Calculadora.java:
Descrição: Classe principal que gerencia a lógica da calculadora.
Atributos:
PilhaGenerica pilhaDeReais: Pilha que armazena os valores numéricos.
PilhaGenerica pilhaDeOperadores: Pilha que armazena os operadores matemáticos.
Métodos:
empilharValorReal(double valor): Empilha um valor real na pilha de valores.
empilharOperador(char operador): Empilha um operador (+, -, *, /) na pilha de operadores.
realizarCalculo(): Desempilha dois valores e um operador, realiza o cálculo e empilha o resultado.
imprimirPilhas(): Imprime o conteúdo das pilhas de valores e operadores.

ListaEncadeadaGenerica.java:
Descrição: Implementação de uma lista encadeada genérica.
Atributos:
Nodo topo: Nó que representa o topo da lista.
Métodos:
inserirNoTopo(Item dado): Insere um item no topo da lista.
removerDoTopo(): Remove e retorna o item do topo da lista.
espiar(): Retorna o item no topo sem removê-lo.
estaVazia(): Verifica se a lista está vazia.

PilhaGenerica.java:
Descrição: Implementação de uma pilha genérica que utiliza a ListaEncadeadaGenerica para armazenar os dados.
Atributos:
ListaEncadeadaGenerica lista: Lista encadeada que armazena os elementos da pilha.
Métodos:
empilhar(Item dado): Empilha um item no topo.
desempilhar(): Desempilha e retorna o item no topo.
espiar(): Retorna o item no topo sem removê-lo.
estaVazia(): Verifica se a pilha está vazia.
toString(): Retorna uma representação textual da pilha.

TestaCalculadora.java:
Descrição: Classe que simula a interação do usuário com a calculadora através de um menu.
Métodos:
main(String[] args): Método principal que cria uma instância da Calculadora e exibe o menu para empilhar valores, operadores, realizar cálculos e imprimir as pilhas.

Funcionalidades da Calculadora:
Empilhar Valores: Permite ao usuário inserir valores reais na pilha.
Empilhar Operadores: O usuário pode inserir operadores matemáticos (+, -, *, /) na pilha de operadores.
Realizar Cálculo: A calculadora desempilha dois valores e um operador, realiza a operação correspondente e empilha o resultado.
E-mail Pilhas: Exibe o estado atual das pilhas de valores e operadores.
Sair: para sair do sistema de forma rápida.
Conclusão Este sistema de calculadora utiliza a estrutura de pilhas para realizar operações matemáticas básicas de forma simples e eficiente. A implementação modular facilita a manutenção e a extensão de novas funcionalidades.
