package excecao;

public class CodigoJaExistenteException extends RuntimeException {
    public CodigoJaExistenteException(String mensagem) {
        super(mensagem);
    }
}