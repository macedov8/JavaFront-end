import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelaCadastro extends JFrame {

    private final JTextField nomeTextField = new JTextField();
    private final JTextField emailTextField = new JTextField();
    private final JPasswordField senhaTextField = new JPasswordField();
    private final JLabel nomeJLabel = new JLabel("Nome:");
    private final JLabel emailJLabel = new JLabel("E-mail:");
    private final JLabel senhaJLabel = new JLabel("Senha:");
    
    private final JButton cadastrarJButton = new JButton("Cadastrar");
    private final JButton voltarJButton = new JButton("Voltar");
    private final JLabel notificacoesJLabel = new JLabel("Notificações...");

    public TelaCadastro() {
        super("Cadastro");
        setLayout(new GridLayout(10, 1, 5, 5));

        JPanel linha1 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha2 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha3 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha4 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha5 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha6 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha7 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha8 = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel linha9 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha10 = new JPanel(new GridLayout(1, 1, 5, 5));

        add(linha1);
        linha2.add(nomeJLabel);
        add(linha2);

        linha3.add(nomeTextField);
        add(linha3);

        linha4.add(emailJLabel);
        add(linha4);

        linha5.add(emailTextField);
        add(linha5);

        linha6.add(senhaJLabel);
        add(linha6);

        linha7.add(senhaTextField);
        add(linha7);

        linha8.add(cadastrarJButton);
        linha8.add(voltarJButton);
        add(linha8);

        add(linha9);

        linha10.add(notificacoesJLabel);
        add(linha10);

        cadastrarJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTextField.getText();
                String email = emailTextField.getText();
                String senha = new String(senhaTextField.getPassword());
                
                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    notificacoesJLabel.setText("Por favor, preencha todos os campos.");
                } else {
                    cadastrarUsuario(nome, email, senha);
                }
            }
        });

        voltarJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new TelaLogin(); 
            }
        });

        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void cadastrarUsuario(String nome, String email, String senha) {
        try (Connection conn = MySQLConnector.conectar()) {
            String query = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.setString(3, senha);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    notificacoesJLabel.setText("Usuário cadastrado com sucesso.");
                } else {
                    notificacoesJLabel.setText("Erro ao cadastrar usuário.");
                }
            }
        } catch (SQLException ex) {
            notificacoesJLabel.setText("Erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new TelaCadastro();
    }
}
