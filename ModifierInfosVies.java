package View;

import javax.swing.*;
import java.awt.*;
import DAO.UtilisateurDAO;
import Modele.Utilisateur;

public class ModifierInfosView extends JPanel {

    private JTextField mailField;
    private JPasswordField passwordField;
    private JButton btnSave;

    public ModifierInfosView(ApplicationFrame parent) {
        setLayout(new BorderLayout(10, 10));

        Utilisateur utilisateur = parent.getUtilisateurConnecte();
        if (utilisateur == null) {
            JOptionPane.showMessageDialog(this, "Aucun utilisateur connecté.");
            return;
        }

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        form.add(new JLabel("Email :"));
        mailField = new JTextField(utilisateur.getMail());
        form.add(mailField);

        form.add(new JLabel("Nouveau mot de passe :"));
        passwordField = new JPasswordField();
        form.add(passwordField);

        btnSave = new JButton("Enregistrer les modifications");

        btnSave.addActionListener(e -> {
            String nouveauMail = mailField.getText();
            String nouveauMdp = new String(passwordField.getPassword());

            utilisateur.setMail(nouveauMail);
            if (!nouveauMdp.isEmpty()) {
                utilisateur.setMotDePasse(nouveauMdp);
            }

            UtilisateurDAO dao = new UtilisateurDAO();
            if (dao.updateUtilisateur(utilisateur)) {
                JOptionPane.showMessageDialog(this, "Modifications enregistrées !");
                parent.setUtilisateurConnecte(utilisateur);
                parent.navigateTo("profilDemandeur");
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour.");
            }
        });

        add(form, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnSave);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
