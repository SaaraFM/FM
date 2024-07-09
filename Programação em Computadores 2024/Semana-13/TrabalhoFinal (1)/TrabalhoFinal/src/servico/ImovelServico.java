package servico;

import modelo.Imovel;
import repositorio.ImovelRepositorio;
import excecao.CodigoJaExistenteException;
import excecao.ImovelNaoEncontradoException;

import java.util.List;

public class ImovelServico {
    private ImovelRepositorio repositorio;

    public ImovelServico() {
        this.repositorio = new ImovelRepositorio();
    }

    public void cadastrarImovel(Imovel imovel) {
        if (repositorio.buscarPorCodigo(imovel.getCodigo()) != null) {
            throw new CodigoJaExistenteException("Código do imóvel já existente.");
        }
        repositorio.adicionar(imovel);
    }

    public List<Imovel> listarTodos() {
        return repositorio.listarTodos();
    }

    public void removerImovel(int codigo) {
        Imovel imovel = repositorio.buscarPorCodigo(codigo);
        if (imovel == null) {
            throw new ImovelNaoEncontradoException("Imóvel não encontrado.");
        }
        repositorio.remover(codigo);
    }

    public void alterarImovel(Imovel imovel) {
        if (repositorio.buscarPorCodigo(imovel.getCodigo()) == null) {
            throw new ImovelNaoEncontradoException("Imóvel não encontrado.");
        }
        repositorio.alterar(imovel);
    }

    public Imovel buscarPorCodigo(int codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }

    public List<Imovel> buscarPorTipo(String tipo) {
        return repositorio.buscarPorTipo(tipo);
    }

    public List<Imovel> buscarPorCidade(String cidade) {
        return repositorio.buscarPorCidade(cidade);
    }

    public List<Imovel> buscarPorBairro(String cidade, String bairro) {
        return repositorio.buscarPorBairro(cidade, bairro);
    }

    public List<Imovel> buscarPorFaixaDePreco(float precoMin, float precoMax) {
        return repositorio.buscarPorFaixaDePreco(precoMin, precoMax);
    }

    public List<Imovel> buscarPorNumeroQuartos(int numeroMinQuartos) {
        return repositorio.buscarPorNumeroQuartos(numeroMinQuartos);
    }
}