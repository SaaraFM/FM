package repositorio;

import modelo.Imovel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImovelRepositorio {
    private List<Imovel> listaDeImoveis;

    public ImovelRepositorio() {
        this.listaDeImoveis = new ArrayList<>();
    }

    public void adicionar(Imovel imovel) {
        listaDeImoveis.add(imovel);
    }

    public List<Imovel> listarTodos() {
        return listaDeImoveis;
    }

    public void remover(int codigo) {
        listaDeImoveis.removeIf(imovel -> imovel.getCodigo() == codigo);
    }

    public void alterar(Imovel imovel) {
        remover(imovel.getCodigo());
        adicionar(imovel);
    }

    public Imovel buscarPorCodigo(int codigo) {
        return listaDeImoveis.stream()
                .filter(imovel -> imovel.getCodigo() == codigo)
                .findFirst()
                .orElse(null);
    }

    public List<Imovel> buscarPorTipo(String tipo) {
        return listaDeImoveis.stream()
                .filter(imovel -> imovel.getTipo().toString().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }

    public List<Imovel> buscarPorCidade(String cidade) {
        return listaDeImoveis.stream()
                .filter(imovel -> imovel.getLocalizacao().getCidade().equalsIgnoreCase(cidade))
                .collect(Collectors.toList());
    }

    public List<Imovel> buscarPorBairro(String cidade, String bairro) {
        return listaDeImoveis.stream()
                .filter(imovel -> imovel.getLocalizacao().getCidade().equalsIgnoreCase(cidade)
                        && imovel.getLocalizacao().getBairro().equalsIgnoreCase(bairro))
                .collect(Collectors.toList());
    }

    public List<Imovel> buscarPorFaixaDePreco(float precoMin, float precoMax) {
        return listaDeImoveis.stream()
                .filter(imovel -> imovel.getPreco() >= precoMin && imovel.getPreco() <= precoMax)
                .collect(Collectors.toList());
    }

    public List<Imovel> buscarPorNumeroQuartos(int numeroMinQuartos) {
        return listaDeImoveis.stream()
                .filter(imovel -> imovel.getNumeroQuartos() >= numeroMinQuartos)
                .collect(Collectors.toList());
    }
}