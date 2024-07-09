package controle;

import modelo.Imovel;
import modelo.Endereco;
import servico.ImovelServico;
import modelo.TipoImovel;
import excecao.CodigoJaExistenteException;
import excecao.ImovelNaoEncontradoException;
import excecao.EntradaInvalidaException;

import java.util.List;
import java.util.Scanner;

public class ImovelControle {
    private ImovelServico servico;
    private Scanner scanner;

    public ImovelControle() {
        this.servico = new ImovelServico();
        this.scanner = new Scanner(System.in);
    }

    public void iniciarSistema() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            try {
                switch (opcao) {
                    case 1:
                        cadastrarImovel();
                        break;
                    case 2:
                        listarTodosImoveis();
                        break;
                    case 3:
                        listarImoveisCondicionais();
                        break;
                    case 4:
                        excluirImovel();
                        break;
                    case 5:
                        alterarImovel();
                        break;
                    case 6:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (CodigoJaExistenteException | ImovelNaoEncontradoException | EntradaInvalidaException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 6);
    }

    private void exibirMenu() {
        System.out.println("Menu:");
        System.out.println("1. Cadastrar Imóvel");
        System.out.println("2. Listar Todos os Imóveis");
        System.out.println("3. Listar Imóveis por Condição");
        System.out.println("4. Excluir Imóvel");
        System.out.println("5. Alterar Imóvel");
        System.out.println("6. Sair\n");
        System.out.print("Escolha uma opção: \n");
    }

    private int lerOpcao() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
            }
        }
    }

    private int lerCodigo() {
        while (true) {
            try {
                System.out.print("Código: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
            }
        }
    }

    private float lerFloat(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um valor numérico.");
            }
        }
    }

    private int lerInt(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
            }
        }
    }

    private String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private TipoImovel lerTipoImovel() {
        while (true) {
            try {
                System.out.print("Tipo (0 - Casa, 1 - Apartamento): ");
                int tipoInt = Integer.parseInt(scanner.nextLine());
                if (tipoInt == 0) {
                    return TipoImovel.CASA;
                } else if (tipoInt == 1) {
                    return TipoImovel.APARTAMENTO;
                } else {
                    System.out.println("Opção inválida. Por favor, escolha 0 para Casa ou 1 para Apartamento.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira 0 ou 1.");
            }
        }
    }

    private void cadastrarImovel() {
        int codigo = lerCodigo();
        float areaConstruida = lerFloat("Área Construída: ");
        float areaTotal = lerFloat("Área Total: ");
        int numeroQuartos = lerInt("Número de Quartos: ");
        TipoImovel tipo = lerTipoImovel();
        float preco = lerFloat("Preço: ");
        String cidade = lerString("Cidade: ");
        String bairro = lerString("Bairro: ");
        
        Endereco endereco = new Endereco(cidade, bairro);
        Imovel imovel = new Imovel(codigo, areaConstruida, areaTotal, numeroQuartos, tipo, preco, endereco);

        try {
            servico.cadastrarImovel(imovel);
            System.out.println("Imóvel cadastrado com sucesso!");
        } catch (CodigoJaExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarTodosImoveis() {
        List<Imovel> imoveis = servico.listarTodos();
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel encontrado.");
        } else {
            imoveis.forEach(imovel -> System.out.println(imovel.toString()));
        }
    }

    private void listarImoveisCondicionais() {
        System.out.println("1. Listar por Tipo");
        System.out.println("2. Listar por Cidade");
        System.out.println("3. Listar por Bairro e Cidade");
        System.out.println("4. Listar por Faixa de Preço");
        System.out.println("5. Listar por Número de Quartos");
        System.out.print("Escolha uma opção: ");
        int opcao = lerOpcao();
        switch (opcao) {
            case 1:
                listarPorTipo();
                break;
            case 2:
                listarPorCidade();
                break;
            case 3:
                listarPorBairroECidade();
                break;
            case 4:
                listarPorFaixaDePreco();
                break;
            case 5:
                listarPorNumeroDeQuartos();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void listarPorTipo() {
        String tipo = lerString("Tipo (Casa ou Apartamento): ");
        List<Imovel> imoveis = servico.buscarPorTipo(tipo);
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel encontrado para o tipo especificado.");
        } else {
            imoveis.forEach(imovel -> System.out.println(imovel.toString()));
        }
    }

    private void listarPorCidade() {
        String cidade = lerString("Cidade: ");
        List<Imovel> imoveis = servico.buscarPorCidade(cidade);
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel encontrado para a cidade especificada.");
        } else {
            imoveis.forEach(imovel -> System.out.println(imovel.toString()));
        }
    }

    private void listarPorBairroECidade() {
        String cidade = lerString("Cidade: ");
        String bairro = lerString("Bairro: ");
        List<Imovel> imoveis = servico.buscarPorBairro(cidade, bairro);
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel encontrado para o bairro e cidade especificados.");
        } else {
            imoveis.forEach(imovel -> System.out.println(imovel.toString()));
        }
    }

    private void listarPorFaixaDePreco() {
        float precoMin = lerFloat("Preço Mínimo: ");
        float precoMax = lerFloat("Preço Máximo: ");
        List<Imovel> imoveis = servico.buscarPorFaixaDePreco(precoMin, precoMax);
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel encontrado para a faixa de preço especificada.");
        } else {
            imoveis.forEach(imovel -> System.out.println(imovel.toString()));
        }
    }

    private void listarPorNumeroDeQuartos() {
        int numeroMinQuartos = lerInt("Número Mínimo de Quartos: ");
        List<Imovel> imoveis = servico.buscarPorNumeroQuartos(numeroMinQuartos);
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel encontrado com o número mínimo de quartos especificado.");
        } else {
            imoveis.forEach(imovel -> System.out.println(imovel.toString()));
        }
    }

    private void excluirImovel() {
        int codigo = lerCodigo();
        try {
            servico.removerImovel(codigo);
            System.out.println("Imóvel excluído com sucesso!");
        } catch (ImovelNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void alterarImovel() {
        int codigo = lerCodigo();
        float novaAreaConstruida = lerFloat("Nova Área Construída: ");
        float novaAreaTotal = lerFloat("Nova Área Total: ");
        int novoNumeroQuartos = lerInt("Novo Número de Quartos: ");
        TipoImovel novoTipo = lerTipoImovel();
        float novoPreco = lerFloat("Novo Preço: ");
        String novaCidade = lerString("Nova Cidade: ");
        String novoBairro = lerString("Novo Bairro: ");
        
        Endereco novoEndereco = new Endereco(novaCidade, novoBairro);
        Imovel novoImovel = new Imovel(codigo, novaAreaConstruida, novaAreaTotal, novoNumeroQuartos, novoTipo, novoPreco, novoEndereco);

        try {
            servico.alterarImovel(novoImovel);
            System.out.println("Imóvel alterado com sucesso!");
        } catch (ImovelNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}
