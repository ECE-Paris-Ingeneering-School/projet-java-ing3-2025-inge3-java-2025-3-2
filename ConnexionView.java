package View;

import Controlleur.LoginControlleur;
import DAO.DemandeurDAO;
import DAO.AgenceDAO;
import Modele.Demandeur;
import Modele.Agence;

import javax.swing.*;
import java.awt.*;

public class ConnexionView extends JPanel {

    JTextField idField;
    JPasswordField mdpField;
    JButton btnConnecter, btnInscrire, btnOublie;

    public ConnexionView(ApplicationFrame parent, LoginControlleur loginControlleur) {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel lblId = new JLabel("Adresse-mail :");
        JLabel lblMdp = new JLabel("Mot de passe :");

        idField = new JTextField(15);
        mdpField = new JPasswordField(15);

        btnConnecter = new JButton("Se connecter");
        btnInscrire = new JButton("S'inscrire");
        btnOublie = new JButton("Mot de passe oublié");

        btnInscrire.addActionListener(e -> parent.navigateTo("inscription"));

        btnConnecter.addActionListener(e -> {
            String email = idField.getText();
            String mdp = new String(mdpField.getPassword());
            var utilisateur = loginControlleur.login(email, mdp);

            if (utilisateur != null) {
                if (utilisateur.getType().equalsIgnoreCase("demandeur")) {
                    // Récupérer le vrai Demandeur
                    DemandeurDAO demandeurDAO = new DemandeurDAO();
                    Demandeur demandeurConnecte = demandeurDAO.getByIdUtilisateur(utilisateur.getId());

                    if (demandeurConnecte != null) {
                        parent.setUtilisateurConnecte(demandeurConnecte);
                        parent.addView("profilDemandeur", new DemandeurProfilView(parent, demandeurConnecte));
                        parent.navigateTo("profilDemandeur");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur : Demandeur introuvable en BDD !");
                    }

                } else if (utilisateur.getType().equalsIgnoreCase("agence")) {
                    // Récupérer la vraie Agence
                    AgenceDAO agenceDAO = new AgenceDAO();
                    Agence agenceConnectee = agenceDAO.getAgenceByUtilisateur(utilisateur.getId());

                    if (agenceConnectee != null) {
                        parent.setUtilisateurConnecte(agenceConnectee);
                        parent.addView("profilAgence", new AgenceView(parent));
                        parent.navigateTo("profilAgence");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur : Agence introuvable en BDD !");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Type d'utilisateur inconnu : " + utilisateur.getType());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Identifiants incorrects.");
            }
        });

        btnOublie.addActionListener(e -> parent.navigateTo("motDePasseOublie"));

        c.gridx = 0; c.gridy = 0; c.insets = new Insets(5, 5, 5, 5);
        add(lblId, c);

        c.gridx = 1;
        add(idField, c);

        c.gridx = 0; c.gridy = 1;
        add(lblMdp, c);

        c.gridx = 1;
        add(mdpField, c);

        JPanel buttons = new JPanel();
        buttons.add(btnConnecter);
        buttons.add(btnInscrire);
        buttons.add(btnOublie);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        add(buttons, c);
    }
}
