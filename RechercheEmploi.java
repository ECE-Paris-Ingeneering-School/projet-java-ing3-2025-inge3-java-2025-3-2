import javax.swing.*;
import java.awt.*;

public class RechercheEmploiView extends JPanel {

    JTextField titreField, emplacementField, societeField;
    JComboBox<String> typeContratBox, perimetreBox;
    JButton rechercherBtn, effacerBtn;

    JTable resultatTable;

    public RechercheEmploiView(ApplicationFrame parent) {
        setLayout(new BorderLayout(10, 10));

        JPanel recherchePanel = new JPanel(new GridBagLayout());
        recherchePanel.setBorder(BorderFactory.createTitledBorder("Recherche d'emploi"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        titreField = new JTextField(15);
        emplacementField = new JTextField(15);
        societeField = new JTextField(15);
        typeContratBox = new JComboBox<>(new String[]{"Tous", "CDI", "CDD", "Stage"});
        perimetreBox = new JComboBox<>(new String[]{"Tous", "5km", "10km", "50km"});

        // Première ligne
        c.gridx = 0; c.gridy = 0; recherchePanel.add(new JLabel("Titre du poste :"), c);
        c.gridx = 1; recherchePanel.add(titreField, c);
        c.gridx = 2; recherchePanel.add(new JLabel("Emplacement :"), c);
        c.gridx = 3; recherchePanel.add(emplacementField, c);

        // Deuxième ligne
        c.gridx = 0; c.gridy = 1; recherchePanel.add(new JLabel("Société :"), c);
        c.gridx = 1; recherchePanel.add(societeField, c);
        c.gridx = 2; recherchePanel.add(new JLabel("Type de contrat :"), c);
        c.gridx = 3; recherchePanel.add(typeContratBox, c);

        // Troisième ligne
        c.gridx = 0; c.gridy = 2; recherchePanel.add(new JLabel("Périmètre :"), c);
        c.gridx = 1; recherchePanel.add(perimetreBox, c);

        rechercherBtn = new JButton("Rechercher");
        effacerBtn = new JButton("Effacer critères");

        JPanel boutonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        boutonsPanel.add(effacerBtn);
        boutonsPanel.add(rechercherBtn);

        JPanel hautPanel = new JPanel(new BorderLayout());
        hautPanel.add(recherchePanel, BorderLayout.CENTER);
        hautPanel.add(boutonsPanel, BorderLayout.SOUTH);

        // Tableau de résultats
        String[] colonnes = {"Titre", "Société", "Emplacement", "Contrat"};
        Object[][] donnees = {
                {"Développeur Java", "Capgemini", "Paris", "CDI"},
                {"Stage Marketing", "L'Oréal", "Lyon", "Stage"}
        };
        resultatTable = new JTable(donnees, colonnes);
        JScrollPane resultatPane = new JScrollPane(resultatTable);
        resultatPane.setBorder(BorderFactory.createTitledBorder("Résultats"));

        add(hautPanel, BorderLayout.NORTH);
        add(resultatPane, BorderLayout.CENTER);
    }
}
