package modelo;

public class Imovel {
    private int codigo;
    private float areaConstruida;
    private float areaTotal;
    private int numeroQuartos;
    private TipoImovel tipo;
    private float preco;
    private Endereco localizacao;

    public Imovel(int codigo, float areaConstruida, float areaTotal, int numeroQuartos, TipoImovel tipo, float preco,
            Endereco localizacao) {
        this.codigo = codigo;
        this.areaConstruida = areaConstruida;
        this.areaTotal = areaTotal;
        this.numeroQuartos = numeroQuartos;
        this.tipo = tipo;
        this.preco = preco;
        this.localizacao = localizacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public float getAreaConstruida() {
        return areaConstruida;
    }

    public void setAreaConstruida(float areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    public float getAreaTotal() {
        return areaTotal;
    }

    public void setAreaTotal(float areaTotal) {
        this.areaTotal = areaTotal;
    }

    public int getNumeroQuartos() {
        return numeroQuartos;
    }

    public void setNumeroQuartos(int numeroQuartos) {
        this.numeroQuartos = numeroQuartos;
    }

    public TipoImovel getTipo() {
        return tipo;
    }

    public void setTipo(TipoImovel tipo) {
        this.tipo = tipo;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Endereco getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Endereco localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "Imóvel {" +
                "Código=" + codigo +
                ", Área Construida= " + areaConstruida +
                ", Área Total=" + areaTotal +
                ", Número de Quartos=" + numeroQuartos +
                ", Tipo=" + tipo +
                ", Preço=" + preco +
                "," + localizacao +
                "}\n";
    }
}