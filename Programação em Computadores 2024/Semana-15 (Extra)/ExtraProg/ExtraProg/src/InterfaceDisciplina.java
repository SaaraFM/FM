import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class InterfaceDisciplina extends JFrame {
    private Disciplina disciplina;
    private JTextArea textArea;
    private JTable table;

    public InterfaceDisciplina() {
        disciplina = new Disciplina();
        disciplina.carregaDoArquivo();

        setTitle("Gestão de Disciplina");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 1));

        addButton(buttonPanel, "Cadastrar um novo estudante", e -> cadastrarEstudante());
        addButton(buttonPanel, "Alterar os dados de um estudante", e -> alterarEstudante());
        addButton(buttonPanel, "Remover um estudante", e -> removerEstudante());
        addButton(buttonPanel, "Consultar um estudante", e -> consultarEstudante());
        addButton(buttonPanel, "Listar os estudantes", e -> listarEstudantes());
        addButton(buttonPanel, "Listar os estudantes com média abaixo de 6.0", e -> listarEstudantesAbaixo6());
        addButton(buttonPanel, "Listar os estudantes com média acima de 6.0", e -> listarEstudantesAcima6());
        addButton(buttonPanel, "Mostrar a média da turma", e -> mostrarMediaTurma());
        addButton(buttonPanel, "Registrar estudantes automaticamente", e -> registrarEstudantesAutomaticamente());
        addButton(buttonPanel, "Sair", e -> sair());

        panel.add(buttonPanel, BorderLayout.WEST);
        add(panel);
    }

    private void addButton(JPanel panel, String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        panel.add(button);
    }

    private void cadastrarEstudante() {
        String nome = JOptionPane.showInputDialog(this, "Nome:");
        if (nome == null || nome.trim().isEmpty()) {
            showError("Nome não pode ser vazio.");
            return;
        }

        String cpf = JOptionPane.showInputDialog(this, "CPF:");
        if (cpf == null || cpf.trim().isEmpty()) {
            showError("CPF não pode ser vazio.");
            return;
        }

        String matricula = JOptionPane.showInputDialog(this, "Matrícula:");
        if (matricula == null || matricula.trim().isEmpty()) {
            showError("Matrícula não pode ser vazia.");
            return;
        }

        float nota01 = 0;
        float nota02 = 0;
        try {
            nota01 = Float.parseFloat(JOptionPane.showInputDialog(this, "Nota 1:"));
            nota02 = Float.parseFloat(JOptionPane.showInputDialog(this, "Nota 2:"));
        } catch (NumberFormatException e) {
            showError("Notas devem ser valores numéricos.");
            return;
        }

        Estudante novoEstudante = new Estudante(nome, cpf, matricula, nota01, nota02);
        if (disciplina.insereEstudante(novoEstudante)) {
            showMessage("Estudante cadastrado com sucesso.");
            disciplina.gravaArquivo();
        } else {
            showError("Erro ao cadastrar estudante.");
        }
    }

    private void alterarEstudante() {
        String matricula = JOptionPane.showInputDialog(this, "Matrícula do estudante a alterar:");
        if (matricula == null || matricula.trim().isEmpty()) {
            showError("Matrícula não pode ser vazia.");
            return;
        }

        Estudante estudante = disciplina.getEstudante(matricula);
        if (estudante != null) {
            String novoNome = JOptionPane.showInputDialog(this, "Novo Nome:", estudante.getNome());
            if (novoNome == null || novoNome.trim().isEmpty()) {
                showError("Nome não pode ser vazio.");
                return;
            }

            String novoCpf = JOptionPane.showInputDialog(this, "Novo CPF:", estudante.getCpf());
            if (novoCpf == null || novoCpf.trim().isEmpty()) {
                showError("CPF não pode ser vazio.");
                return;
            }

            String novaMatricula = JOptionPane.showInputDialog(this, "Nova Matrícula:", estudante.getMatricula());
            if (novaMatricula == null || novaMatricula.trim().isEmpty()) {
                showError("Matrícula não pode ser vazia.");
                return;
            }

            float novaNota01 = 0;
            float novaNota02 = 0;
            try {
                novaNota01 = Float.parseFloat(JOptionPane.showInputDialog(this, "Nova Nota 1:", estudante.getNota01()));
                novaNota02 = Float.parseFloat(JOptionPane.showInputDialog(this, "Nova Nota 2:", estudante.getNota02()));
            } catch (NumberFormatException e) {
                showError("Notas devem ser valores numéricos.");
                return;
            }

            if (disciplina.alteraEstudante(matricula, novoNome, novoCpf, novaMatricula, novaNota01, novaNota02)) {
                showMessage("Dados do estudante alterados com sucesso.");
                disciplina.gravaArquivo();
            } else {
                showError("Erro ao alterar os dados do estudante.");
            }
        } else {
            showError("Estudante não encontrado.");
        }
    }

    private void removerEstudante() {
        String matricula = JOptionPane.showInputDialog(this, "Matrícula do estudante a remover:");
        if (matricula == null || matricula.trim().isEmpty()) {
            showError("Matrícula não pode ser vazia.");
            return;
        }

        if (disciplina.removeEstudante(matricula)) {
            showMessage("Estudante removido com sucesso.");
            disciplina.gravaArquivo();
        } else {
            showError("Estudante não encontrado.");
        }
    }

    private void consultarEstudante() {
        String matricula = JOptionPane.showInputDialog(this, "Matrícula do estudante a consultar:");
        if (matricula == null || matricula.trim().isEmpty()) {
            showError("Matrícula não pode ser vazia.");
            return;
        }

        Estudante estudante = disciplina.getEstudante(matricula);
        if (estudante != null) {
            showMessage(estudante.toString());
        } else {
            showError("Estudante não encontrado.");
        }
    }

    private void listarEstudantes() {
        String[] columnNames = {"Nome", "CPF", "Matrícula", "Nota 1", "Nota 2", "Média"};
        ArrayList<Estudante> turma = disciplina.getTurma();
        Object[][] data = new Object[turma.size()][6];

        for (int i = 0; i < turma.size(); i++) {
            Estudante e = turma.get(i);
            data[i][0] = e.getNome();
            data[i][1] = e.getCpf();
            data[i][2] = e.getMatricula();
            data[i][3] = e.getNota01();
            data[i][4] = e.getNota02();
            data[i][5] = e.getMedia();
        }

        table = new JTable(data, columnNames);
        JOptionPane.showMessageDialog(this, new JScrollPane(table), "Lista de Estudantes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarEstudantesAbaixo6() {
        String[] columnNames = {"Nome", "CPF", "Matrícula", "Nota 1", "Nota 2", "Média"};
        ArrayList<Estudante> turma = disciplina.getTurma();
        ArrayList<Estudante> abaixo6 = new ArrayList<>();

        for (Estudante e : turma) {
            if (e.getMedia() < 6.0) {
                abaixo6.add(e);
            }
        }

        if (abaixo6.isEmpty()) {
            showMessage("Nenhum estudante com média abaixo de 6.0.");
            return;
        }

        Object[][] data = new Object[abaixo6.size()][6];

        for (int i = 0; i < abaixo6.size(); i++) {
            Estudante e = abaixo6.get(i);
            data[i][0] = e.getNome();
            data[i][1] = e.getCpf();
            data[i][2] = e.getMatricula();
            data[i][3] = e.getNota01();
            data[i][4] = e.getNota02();
            data[i][5] = e.getMedia();
        }

        table = new JTable(data, columnNames);
        JOptionPane.showMessageDialog(this, new JScrollPane(table), "Estudantes com média abaixo de 6.0", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarEstudantesAcima6() {
        String[] columnNames = {"Nome", "CPF", "Matrícula", "Nota 1", "Nota 2", "Média"};
        ArrayList<Estudante> turma = disciplina.getTurma();
        ArrayList<Estudante> acima6 = new ArrayList<>();

        for (Estudante e : turma) {
            if (e.getMedia() >= 6.0) {
                acima6.add(e);
            }
        }

        if (acima6.isEmpty()) {
            showMessage("Nenhum estudante com média acima de 6.0.");
            return;
        }

        Object[][] data = new Object[acima6.size()][6];

        for (int i = 0; i < acima6.size(); i++) {
            Estudante e = acima6.get(i);
            data[i][0] = e.getNome();
            data[i][1] = e.getCpf();
            data[i][2] = e.getMatricula();
            data[i][3] = e.getNota01();
            data[i][4] = e.getNota02();
            data[i][5] = e.getMedia();
        }

        table = new JTable(data, columnNames);
        JOptionPane.showMessageDialog(this, new JScrollPane(table), "Estudantes com média acima de 6.0", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarMediaTurma() {
        float media = disciplina.mediaTurma();
        if (media != 0) {
            showMessage("Média da turma: " + media);
        }
    }

    private void registrarEstudantesAutomaticamente() {
        int quantidade;
        try {
            quantidade = Integer.parseInt(JOptionPane.showInputDialog(this, "Quantos estudantes deseja registrar automaticamente?"));
        } catch (NumberFormatException e) {
            showError("Quantidade deve ser um valor numérico.");
            return;
        }

        disciplina.registraEstudanteAutomaticamente(quantidade);
        disciplina.gravaArquivo();
        showMessage(quantidade + " estudantes registrados automaticamente.");
    }

    private void sair() {
        disciplina.gravaArquivo();
        System.exit(0);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfaceDisciplina frame = new InterfaceDisciplina();
            frame.setVisible(true);
        });
    }
}