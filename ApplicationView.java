import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class ApplicationFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton retourBtn;
    private Stack<String> historiqueNavigation = new Stack<>();

    public ApplicationFrame() {
        super("Application de recrutement");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);


        // Ajout des vues...
        cardPanel.add(new ConnexionView(this), "connexion");
        cardPanel.add(new InscriptionView(this), "inscription");
        cardPanel.add(new RechercheEmploiView(this), "rechercheEmploi");
        cardPanel.add(new DemandeurProfilView(this), "profilDemandeur");
        cardPanel.add(new AgenceView(this), "profilAgence");
        cardPanel.add(new MotDePasseOublieView(this), "motDePasseOublie");

        // Bouton Retour
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

        navigateTo("connexion"); // vue par défaut
    }

    public void navigateTo(String viewName) {
        if (!historiqueNavigation.isEmpty() && !historiqueNavigation.peek().equals(viewName)) {
            historiqueNavigation.push(viewName);
        } else if (historiqueNavigation.isEmpty()) {
            historiqueNavigation.push(viewName);
        }
        cardLayout.show(cardPanel, viewName);
    }

  
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ApplicationFrame::new);
    }
}
