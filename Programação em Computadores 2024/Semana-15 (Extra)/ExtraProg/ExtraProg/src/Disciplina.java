import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Disciplina {
    private ArrayList<Estudante> turma;

    public Disciplina() {
        turma = new ArrayList<>();
    }

    public ArrayList<Estudante> getTurma() {
        return turma;
    }

    public void listarEstudantes() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado.");
        } else {
            for (Estudante e : turma) {
                System.out.println(e);
            }
        }
    }

    public float mediaTurma() {
        if (turma.isEmpty()) {
            System.out.println("Nenhum estudante cadastrado.");
            return 0;
        }
        float soma = 0;
        for (Estudante e : turma) {
            soma += e.getMedia();
        }
        return soma / turma.size();
    }

    public Estudante getEstudante(String matricula) {
        for (Estudante e : turma) {
            if (e.getMatricula().equals(matricula)) {
                return e;
            }
        }
        return null;
    }

    public boolean insereEstudante(Estudante novo) {
        if (getEstudante(novo.getMatricula()) != null) {
            System.out.println("Cadastro já existe.");
            return false;
        }
        turma.add(novo);
        return true;
    }

    public boolean alteraEstudante(String matricula, String novoNome, String novoCpf, String novaMatricula, float novaNota01, float novaNota02) {
        Estudante e = getEstudante(matricula);
        if (e != null) {
            e.setNome(novoNome);
            e.setCpf(novoCpf);
            e.setMatricula(novaMatricula);
            e.setNota01(novaNota01);
            e.setNota02(novaNota02);
            return true;
        }
        return false;
    }

    public boolean removeEstudante(String matricula) {
        Estudante e = getEstudante(matricula);
        if (e != null) {
            turma.remove(e);
            return true;
        }
        return false;
    }

    public void listaEstudantesAbaixo6() {
        boolean encontrado = false;
        for (Estudante e : turma) {
            if (e.getMedia() < 6.0) {
                System.out.println(e);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum estudante com média abaixo de 6.0.");
        }
    }

    public void listaEstudantesAcima6() {
        boolean encontrado = false;
        for (Estudante e : turma) {
            if (e.getMedia() >= 6.0) {
                System.out.println(e);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("Nenhum estudante com média acima de 6.0.");
        }
    }

    public void gravaArquivo() {
        try (PrintWriter writer = new PrintWriter(new File("estudantes.csv"))) {
            for (Estudante e : turma) {
                writer.println(e.getEstudanteCSV());
            }
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo: " + e.getMessage());
        }
    }

    public void carregaDoArquivo() {
        File arquivo = new File("estudantes.csv");
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    Estudante e = new Estudante(linha, linha, linha, 0, 0);
                    e.setEstudanteCSV(linha);
                    insereEstudante(e);
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo de estudantes não encontrado. Nenhum estudante foi carregado.");
        }
    }

    public void registraEstudanteAutomaticamente(int quantidade) {
        Set<String> matriculasExistentes = new HashSet<>();
        for (Estudante e : turma) {
            matriculasExistentes.add(e.getMatricula());
        }

        int i = 0;
        while (i < quantidade) {
            String nome = "Estudante" + (turma.size() + i + 1);
            String cpf = "CPF" + (turma.size() + i + 1);
            String matricula;
            do {
                matricula = "Matricula" + (int)(Math.random() * 10000);
            } while (matriculasExistentes.contains(matricula));
            float nota01 = (float) (Math.random() * 10);
            float nota02 = (float) (Math.random() * 10);
            Estudante novoEstudante = new Estudante(nome, cpf, matricula, nota01, nota02);
            insereEstudante(novoEstudante);
            matriculasExistentes.add(matricula);
            i++;
        }
        gravaArquivo();
    }
}