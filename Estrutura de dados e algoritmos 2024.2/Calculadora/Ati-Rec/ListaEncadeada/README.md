## Lista Duplamente Encadeada Simples em Java
Este projeto implementa uma lista duplamente encadeada simplificada, utilizando a classe LinkedList do Java. A lista mantém os elementos ordenados de forma crescente e oferece funcionalidades práticas como inserção de valores, exibição da lista e geração de uma sublista com números pares.

## Funcionalidades
1. Inserção Ordenada
  -Insere um número inteiro na posição correta para manter a ordem crescente.
  -Bloqueia a inserção de números duplicados.
  -Retorna mensagens de feedback apropriadas (ex.: número inserido com sucesso ou já existente na lista).
2. Exibição da Lista
  -Mostra todos os números contidos na lista principal em ordem crescente.
  -Exibe uma mensagem caso a lista esteja vazia.
3. Sublista de Números Pares
  -Gera uma nova lista contendo apenas os números pares da lista principal.
  -Mostra a sublista de números pares ou uma mensagem caso a sublista esteja vazia.

## Estrutura do Projeto
O projeto é composto pelas seguintes classes:

1. ListaDuplamenteEncadeada
  Implementa a lógica da lista utilizando LinkedList<Integer>.
  * Oferece métodos para:
    -Inserção ordenada de números.
    -Retorno de uma sublista com números pares.
    -Exibição dos valores armazenados.
2. TesteLista
  Contém o programa principal com um menu interativo.
  * Permite ao usuário:
    -Inserir números na lista.
    -Exibir a lista completa.
    -Gerar e exibir uma sublista de números pares.