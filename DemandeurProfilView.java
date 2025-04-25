import javax.swing.*;
import java.awt.*;

public class DemandeurProfilView extends JPanel {

    JButton modifierInfos, rechercherEmploi;
    JTextArea infosUtilisateur, historiqueCandidatures;

    public DemandeurProfilView(ApplicationFrame parent) {
        setLayout(new BorderLayout(10, 10));

        // Informations personnelles
        JPanel infosPanel = new JPanel(new BorderLayout());
        infosPanel.setBorder(BorderFactory.createTitledBorder("Informations personnelles"));
        infosUtilisateur = new JTextArea("Nom : Doe\nPrénom : John\nMail : john@example.com\nTel : 0123456789");
        infosUtilisateur.setEditable(false);
        modifierInfos = new JButton("Modifier les infos");
        infosPanel.add(new JScrollPane(infosUtilisateur), BorderLayout.CENTER);
        infosPanel.add(modifierInfos, BorderLayout.SOUTH);

      
        // Historique des candidatures
        JPanel historiquePanel = new JPanel(new BorderLayout());
        historiquePanel.setBorder(BorderFactory.createTitledBorder("Historique de candidatures"));
        historiqueCandidatures = new JTextArea("• Développeur Java - Capgemini - Candidature envoyée\n• Stage Marketing - L'Oréal - En attente de réponse");
        historiqueCandidatures.setEditable(false);
        historiquePanel.add(new JScrollPane(historiqueCandidatures), BorderLayout.CENTER);

        // Bouton rechercher emploi
        rechercherEmploi = new JButton("Rechercher emploi");
        rechercherEmploi.addActionListener(e -> parent.navigateTo("rechercheEmploi"));

        JPanel bas = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bas.add(rechercherEmploi);

        add(infosPanel, BorderLayout.WEST);
        add(historiquePanel, BorderLayout.CENTER);
        add(bas, BorderLayout.SOUTH);
    }
}
