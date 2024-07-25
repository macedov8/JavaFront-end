import java.awt.*;
import javax.swing.*;

public class EditarCadastroNavegador extends JFrame {

    private final JTextField idTextField = new JTextField();
    private final JTextField nomeTextField = new JTextField();
    private final JTextField emailTextField = new JTextField();
    private final JTextField senhaTextField = new JTextField();
    private final JLabel idJLabel = new JLabel("ID:");
    private final JLabel nomeJLabel = new JLabel("Digite um nome:");
    private final JLabel emailJLabel = new JLabel("Digite um email:");
    private final JLabel senhaJLabel = new JLabel("Digite uma senha:");
    private final JButton atualizarJButton = new JButton("✓");
    private final JButton primeiroRegistroJButton = new JButton("<<");
    private final JButton registroAnteriorJButton = new JButton("<");
    private final JButton proximoRegistroJButton = new JButton(">");
    private final JButton ultimoRegistroJButton = new JButton(">>");
    private final JLabel notificacaoJLabel = new JLabel("Notificações...");

    public EditarCadastroNavegador() {

        super("Editar Cadastro");
        setLayout(new GridLayout(6, 4, 5, 5));

        add(idJLabel);
        add(idTextField);
        add(atualizarJButton);
        add(new JLabel());
        atualizarJButton.setToolTipText("Atualizar cadastro");
        
        add(nomeJLabel);
        add(nomeTextField);
        add(new JLabel());
        add(new JLabel());

        add(emailJLabel);
        add(emailTextField);
        add(new JLabel());
        add(new JLabel());

        add(senhaJLabel);
        add(senhaTextField);
        add(new JLabel());
        add(new JLabel());

        idTextField.setEnabled(false);
        atualizarJButton.setEnabled(false);

        add(primeiroRegistroJButton);
        add(registroAnteriorJButton);
        add(proximoRegistroJButton);
        add(ultimoRegistroJButton);
        add(notificacaoJLabel);
        setSize(500, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        EditarCadastroNavegador application = new EditarCadastroNavegador();
        application.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}