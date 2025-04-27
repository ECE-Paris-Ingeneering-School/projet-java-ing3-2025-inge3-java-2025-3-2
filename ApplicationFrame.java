package View;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;
import Modele.Utilisateur;

public class ApplicationFrame extends JFrame {

    private Utilisateur utilisateurConnecte;

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private Stack<String> historiqueNavigation = new Stack<>();
    private JButton retourBtn;

    public ApplicationFrame() {
        super("Application de recrutement");

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("Impossible de charger le Look and Feel Nimbus");
        }

        retourBtn = new JButton("Retour");
        retourBtn.addActionListener(e -> retour());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(retourBtn);

        add(topPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addView(String name, JPanel view) {
        cardPanel.add(view, name);
    }

    public void showView(String name) {
        if (!historiqueNavigation.isEmpty() && !historiqueNavigation.peek().equals(name)) {
            historiqueNavigation.push(name);
        } else if (historiqueNavigation.isEmpty()) {
            historiqueNavigation.push(name);
        }
        cardLayout.show(cardPanel, name);
    }

    private void retour() {
        if (historiqueNavigation.size() > 1) {
            historiqueNavigation.pop();
            cardLayout.show(cardPanel, historiqueNavigation.peek());
        } else {
            JOptionPane.showMessageDialog(this, "Vous êtes déjà sur la page initiale.");
        }
    }
    public void navigateTo(String viewName) {
        if (!historiqueNavigation.isEmpty() && !historiqueNavigation.peek().equals(viewName)) {
            historiqueNavigation.push(viewName);
        } else if (historiqueNavigation.isEmpty()) {
            historiqueNavigation.push(viewName);
        }
        cardLayout.show(cardPanel, viewName);
    }
    public void setUtilisateurConnecte(Utilisateur utilisateur) {
        this.utilisateurConnecte = utilisateur;
    }

    public Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }
    public int getIdAgenceConnectee() {
        if (utilisateurConnecte != null && utilisateurConnecte instanceof Modele.Agence) {
            return utilisateurConnecte.getId(); // c'est l'id de l'agence !
        }
        return -1; // aucun connecté ou mauvais type
    }


}
