class Estudante extends Pessoa {
    private String matricula;
    private float nota01;
    private float nota02;

    public Estudante(String nome, String cpf, String matricula, float nota01, float nota02) {
        super(nome, cpf);
        this.matricula = matricula;
        this.nota01 = nota01;
        this.nota02 = nota02;
    }

    public String getMatricula() {
        return matricula;
    }

    public float getNota01() {
        return nota01;
    }

    public float getNota02() {
        return nota02;
    }

    public float getMedia() {
        return (nota01 + nota02) / 2;
    }

    public String getEstudanteCSV() {
        return getNome() + ";" + getCpf() + ";" + matricula + ";" + nota01 + ";" + nota02;
    }

    public void setEstudanteCSV(String linha) {
        String[] campos = linha.split(";");
        if (campos.length == 5) {
            setNome(campos[0]);
            setCpf(campos[1]);
            this.matricula = campos[2];
            this.nota01 = Float.parseFloat(campos[3]);
            this.nota02 = Float.parseFloat(campos[4]);
        }
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + ", CPF: " + getCpf() + ", Matrícula: " + matricula + ", Nota 1: " + nota01 + ", Nota 2: " + nota02 + ", Média: " + getMedia();
    }

    public void setNome(String nome) {
        super.setNome(nome);
    }

    public void setCpf(String cpf) {
        super.setCpf(cpf);
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNota01(float nota01) {
        this.nota01 = nota01;
    }

    public void setNota02(float nota02) {
        this.nota02 = nota02;
    }
}
