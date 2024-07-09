package excecao;

public class ImovelNaoEncontradoException extends RuntimeException {
    public ImovelNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
