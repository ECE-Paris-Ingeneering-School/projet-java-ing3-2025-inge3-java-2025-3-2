package View;

import Controlleur.InscriptionControlleur;
import javax.swing.*;
import java.awt.*;

public class InscriptionView extends JPanel {

    private JTextField nomField, mailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JCheckBox recruteurBox, demandeurBox;
    private JButton inscrireButton;

    public InscriptionView(ApplicationFrame parent, InscriptionControlleur inscriptionControlleur) {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        nomField = new JTextField();
        mailField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();
        recruteurBox = new JCheckBox("Recruteur");
        demandeurBox = new JCheckBox("Demandeur d'emploi");

        inscrireButton = new JButton("S'inscrire");

        formPanel.add(new JLabel("Nom :"));
        formPanel.add(nomField);
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
            String nom = nomField.getText();
            String mail = mailField.getText();
            String mdp = new String(passwordField.getPassword());
            String confirmMdp = new String(confirmPasswordField.getPassword());
            String type = demandeurBox.isSelected() ? "demandeur" : recruteurBox.isSelected() ? "recruteur" : "";

            if (!mdp.equals(confirmMdp)) {
                JOptionPane.showMessageDialog(this, "Les mots de passe ne correspondent pas.");
                return;
            }

            if (inscriptionControlleur.inscrire(nom, mail, mdp, type)) {
                JOptionPane.showMessageDialog(this, "Inscription réussie !");
                parent.navigateTo("connexion");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'inscription.");
            }
        });

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
