import javax.swing.*;
import java.awt.*;

public class InscriptionView extends JPanel {

    private JTextField nomField, prenomField, mailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox recruteurBox, demandeurBox;
    private JButton inscrireButton;

    public InscriptionView(ApplicationFrame parent) {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        nomField = new JTextField();
        prenomField = new JTextField();
        mailField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        recruteurBox = new JCheckBox("Recruteur");
        demandeurBox = new JCheckBox("Demandeur d'emploi");

        inscrireButton = new JButton("S'inscrire");

        formPanel.add(new JLabel("Nom :"));
        formPanel.add(nomField);
        formPanel.add(new JLabel("Prénom :"));
        formPanel.add(prenomField);
        formPanel.add(new JLabel("Email :"));
        formPanel.add(mailField);
        formPanel.add(new JLabel("Mot de passe :"));
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Confirmer mot de passe :"));
        formPanel.add(confirmPasswordField);
        formPanel.add(recruteurBox);
        formPanel.add(demandeurBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(inscrireButton);

        inscrireButton.addActionListener(e -> {
            
            JOptionPane.showMessageDialog(this, "Inscription réussie !");
            parent.navigateTo("connexion"); 
        });

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
