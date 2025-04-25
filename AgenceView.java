import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AgenceView extends JPanel {

    JButton ajouterEmploi, modifierEmploi, supprimerEmploi;
    JTable emploisTable, candidatsTable;

    public AgenceView(ApplicationFrame parent) {
        setLayout(new BorderLayout(10, 10));

        // Panel des offres d'emploi
        JPanel emploisPanel = new JPanel(new BorderLayout());
        emploisPanel.setBorder(BorderFactory.createTitledBorder("Vos offres d'emplois"));

        String[] colonnesEmplois = {"Titre", "Société", "Lieu", "Type de contrat"};
        Object[][] donneesEmplois = {
                {"Développeur Web", "Google", "Paris", "CDI"},
                {"Chef de projet", "Accenture", "Lille", "CDD"}
        };

        emploisTable = new JTable(new DefaultTableModel(donneesEmplois, colonnesEmplois));
        emploisTable.setEnabled(false);
        JScrollPane emploisScroll = new JScrollPane(emploisTable);

        JPanel boutonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ajouterEmploi = new JButton("Ajouter");
        modifierEmploi = new JButton("Modifier");
        supprimerEmploi = new JButton("Supprimer");

        boutonsPanel.add(ajouterEmploi);
        boutonsPanel.add(modifierEmploi);
        boutonsPanel.add(supprimerEmploi);

        emploisPanel.add(emploisScroll, BorderLayout.CENTER);
        emploisPanel.add(boutonsPanel, BorderLayout.SOUTH);

        // Panel des candidats
        JPanel candidatsPanel = new JPanel(new BorderLayout());
        candidatsPanel.setBorder(BorderFactory.createTitledBorder("Vos candidats"));

        String[] colonnesCandidats = {"Nom", "Prénom", "Poste visé", "Statut"};
        Object[][] donneesCandidats = {
                {"Doe", "John", "Développeur Web", "Entretien programmé"},
                {"Smith", "Anna", "Chef de projet", "Candidature envoyée"}
        };

        candidatsTable = new JTable(new DefaultTableModel(donneesCandidats, colonnesCandidats));
        candidatsTable.setEnabled(false);
        JScrollPane candidatsScroll = new JScrollPane(candidatsTable);

        candidatsPanel.add(candidatsScroll, BorderLayout.CENTER);

        // Panel des statistiques
        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistiques"));
        JLabel statsLabel = new JLabel("[Graphique ou résumé des statistiques ici]");
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statsPanel.add(statsLabel, BorderLayout.CENTER);

        // Ajout des panneaux dans la vue principale
        JSplitPane splitPaneHaut = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, emploisPanel, candidatsPanel);
        splitPaneHaut.setResizeWeight(0.5);

        add(splitPaneHaut, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);
    }
}
