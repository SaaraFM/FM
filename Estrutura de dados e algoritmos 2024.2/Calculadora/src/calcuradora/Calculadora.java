package calcuradora;
public class Calculadora {
    private PilhaGenerica<Double> pilhaDeReais;
    public Calculadora(PilhaGenerica<Double> pilhaDeReais, PilhaGenerica<Character> pilhaDeOperadores) {
        this.pilhaDeReais = pilhaDeReais;
        this.pilhaDeOperadores = pilhaDeOperadores;
    }

    public PilhaGenerica<Double> getPilhaDeReais() {
        return pilhaDeReais;
    }

    public void setPilhaDeReais(PilhaGenerica<Double> pilhaDeReais) {
        this.pilhaDeReais = pilhaDeReais;
    }

    private PilhaGenerica<Character> pilhaDeOperadores;

    public Calculadora() {
        pilhaDeReais = new PilhaGenerica<>();
        pilhaDeOperadores = new PilhaGenerica<>();
    }

    public void empilharValorReal(double valor) {
        pilhaDeReais.empilhar(valor);
        System.out.println("Valor " + valor + " empilhado.");
    }

    public void empilharOperador(char operador) {
        if (operador == '+' || operador == '-' || operador == '*' || operador == '/') {
            pilhaDeOperadores.empilhar(operador);
            System.out.println("Operador " + operador + " empilhado.");
        } else {
            System.out.println("Operador inválido. Use apenas: +, -, *, /.");
        }
    }

    public void realizarCalculo() {
        if (pilhaDeReais.estaVazia() || pilhaDeReais.toString().split(" ").length < 2) {
            System.out.println("Não há valores suficientes na pilha de reais. São necessários no minímo dois valores.");
            return;
        }
        if (pilhaDeOperadores.estaVazia()) {
            System.out.println("Não há operadores suficientes na pilha de operadores.");
            return;
        }

        double valor1 = pilhaDeReais.desempilhar();
        double valor2 = pilhaDeReais.desempilhar();
        char operador = pilhaDeOperadores.desempilhar();

        double resultado = 0;
        switch (operador) {
            case '+':
                resultado = valor2 + valor1;
                break;
            case '-':
                resultado = valor2 - valor1;
                break;
            case '*':
                resultado = valor2 * valor1;
                break;
            case '/':
                if (valor1 != 0) {
                    resultado = valor2 / valor1;
                } else {
                    System.out.println("Erro: divisão por zero.");
                    pilhaDeReais.empilhar(valor2); 
                    pilhaDeReais.empilhar(valor1);
                    pilhaDeOperadores.empilhar(operador);
                    return;
                }
                break;
        }

        pilhaDeReais.empilhar(resultado);
        System.out.println("Resultado da operação: " + valor2 + " " + operador + " " + valor1 + " = " + resultado);
    }

    public void imprimirPilhas() {
        System.out.println("\n------------");
        System.out.println("Pilha de valores reais:");
        imprimirEmpilhamento(pilhaDeReais);
        System.out.println("\n------------");
        System.out.println("Pilha de operadores:");
        imprimirEmpilhamento(pilhaDeOperadores);
    }

    private <Item> void imprimirEmpilhamento(PilhaGenerica<Item> pilha) {
        PilhaGenerica<Item> auxiliar = new PilhaGenerica<>();
        StringBuilder sb = new StringBuilder();

        while (!pilha.estaVazia()) {
            Item elemento = pilha.desempilhar();
            auxiliar.empilhar(elemento);
            sb.append("(\"" + elemento + "\");\n");
        }

        while (!auxiliar.estaVazia()) {
            pilha.empilhar(auxiliar.desempilhar());
        }

        System.out.print(sb.toString());
    }
}