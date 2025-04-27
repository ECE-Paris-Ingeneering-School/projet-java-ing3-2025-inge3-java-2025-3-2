package View;

import javax.swing.*;
import java.awt.*;

public class MotDePasseOublieView extends JPanel {

    JTextField mailField;
    JButton envoyerLien;

    public MotDePasseOublieView(ApplicationFrame parent) {
        setLayout(new BorderLayout(10, 10));

        JLabel instruction = new JLabel("Entrez votre adresse mail pour recevoir le lien de récupération :");
        mailField = new JTextField(20);
        envoyerLien = new JButton("Envoyer lien");

        envoyerLien.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Lien envoyé à " + mailField.getText());
            parent.navigateTo("connexion"); // Optionnel : retour après envoi
        });

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(mailField);
        panel.add(envoyerLien);

        add(instruction, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }
}
