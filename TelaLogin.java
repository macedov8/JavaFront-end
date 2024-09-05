import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TelaLogin extends JFrame {

    private final JTextField loginTextField = new JTextField();
    private final JPasswordField senhaTextField = new JPasswordField();
    private final JLabel loginJLabel = new JLabel("Usuário:");
    private final JLabel senhaJLabel = new JLabel("Senha:");
    
    private final JButton entrarJButton = new JButton("Entrar");
    private final JButton adicionarCadastroJButton = new JButton("Novo");
    private final JCheckBox checkBox = new JCheckBox("Aceito os termos");
    private final JLabel notificacoesJLabel = new JLabel("Notificações...");

    public TelaLogin() {
        super("Login");
        setLayout(new GridLayout(10, 1, 5, 5));

        JPanel linha1 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha2 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha3 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha4 = new JPanel(new GridLayout(1, 3, 5, 5));
        JPanel linha5 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha6 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha7 = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel linha8 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha9 = new JPanel(new GridLayout(1, 1, 5, 5));
        JPanel linha10 = new JPanel(new GridLayout(1, 1, 5, 5));

        add(linha1);
        linha2.add(loginJLabel);
        add(linha2);

        linha3.add(loginTextField);
        add(linha3);
        add(linha4);

        linha5.add(senhaJLabel);
        add(linha5);

        linha6.add(senhaTextField);
        add(linha6);

        linha7.add(entrarJButton);
        linha7.add(adicionarCadastroJButton);
        add(linha7);

        add(linha8);

        linha9.add(checkBox);
        add(linha9);

        linha10.add(notificacoesJLabel);
        add(linha10);

        entrarJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = loginTextField.getText();
                String senha = new String(senhaTextField.getPassword());
                boolean aceito = checkBox.isSelected();
                
                if (login.isEmpty() || senha.isEmpty()) {
                    notificacoesJLabel.setText("Por favor, preencha todos os campos.");
                } else if (!aceito) {
                    notificacoesJLabel.setText("Você deve aceitar os termos.");
                } else {
                    verificarLogin(login, senha);
                }
            }
        });

        adicionarCadastroJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new TelaCadastro(); 
            }
        });

        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void verificarLogin(String login, String senha) {
        try (Connection conn = MySQLConnector.conectar()) {
            String query = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, login);
                stmt.setString(2, senha);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        notificacoesJLabel.setText("Login realizado com sucesso.");
                        dispose(); 
                        new EditarCadastro(); 
                    } else {
                        notificacoesJLabel.setText("Usuário ou senha incorretos.");
                    }
                }
            }
        } catch (SQLException ex) {
            notificacoesJLabel.setText("Erro ao conectar ao banco de dados: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}