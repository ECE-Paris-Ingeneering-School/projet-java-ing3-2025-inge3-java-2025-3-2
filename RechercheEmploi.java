package View;

import javax.swing.*;
import java.awt.*;

public class RechercheEmploiView extends JPanel {

    JTextField titreField, emplacementField, societeField;
    JComboBox<String> typeContratBox, perimetreBox;
    JButton rechercherBtn, effacerBtn;

    JTable resultatTable;
    JButton postulerBtn;


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

        rechercherBtn = new JButton("Rechercher");
        effacerBtn = new JButton("Effacer critères");
        postulerBtn = new JButton("Postuler");

        rechercherBtn.addActionListener(e -> {
            String titre = titreField.getText();
            String lieu = emplacementField.getText();
            String societe = societeField.getText();
            String typeContrat = (String) typeContratBox.getSelectedItem();

            DAO.EmploiDAO emploiDAO = new DAO.EmploiDAO();
            java.util.List<Modele.Emploi> emplois = emploiDAO.rechercherEmplois(titre, lieu, typeContrat, societe);


            String[] colonnes = {"Titre", "Société", "Emplacement", "Contrat"};
            Object[][] donnees = new Object[emplois.size()][4];

            for (int i = 0; i < emplois.size(); i++) {
                Modele.Emploi emploi = emplois.get(i);
                donnees[i][0] = emploi.getTitre();
                donnees[i][1] = "Buzzcut Agency"; // Société fictive car tu n'as pas le nom dans Emploi
                donnees[i][2] = emploi.getLieu();
                donnees[i][3] = emploi.getCategorie();
            }

            resultatTable.setModel(new javax.swing.table.DefaultTableModel(donnees, colonnes));
        });
        effacerBtn.addActionListener(e -> {
            titreField.setText("");
            emplacementField.setText("");
            societeField.setText("");
            typeContratBox.setSelectedIndex(0); // "Tous"
        });
        postulerBtn.addActionListener(e -> {
            int selectedRow = resultatTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une offre d'emploi pour postuler.");
                return;
            }

            String titreEmploi = (String) resultatTable.getValueAt(selectedRow, 0);

            DAO.EmploiDAO emploiDAO = new DAO.EmploiDAO();
            Modele.Emploi emploi = emploiDAO.getEmploiByTitre(titreEmploi);

            if (emploi != null) {
                if ("demandeur".equalsIgnoreCase(parent.getUtilisateurConnecte().getType())) {
                    DAO.CandidatureDAO candidatureDAO = new DAO.CandidatureDAO();
                    boolean success = candidatureDAO.postuler(emploi, parent.getUtilisateurConnecte());

                    if (success) {
                        JOptionPane.showMessageDialog(this, "Candidature envoyée !");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erreur lors de la candidature.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Seuls les demandeurs peuvent postuler.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Emploi introuvable.");
            }
        });

        JPanel boutonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        boutonsPanel.add(effacerBtn);
        boutonsPanel.add(rechercherBtn);
        boutonsPanel.add(postulerBtn);

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
        chargerOffres();

        add(hautPanel, BorderLayout.NORTH);
        add(resultatPane, BorderLayout.CENTER);
    }
    private void chargerOffres() {
        DAO.EmploiDAO emploiDAO = new DAO.EmploiDAO();
        java.util.List<Modele.Emploi> emplois = emploiDAO.rechercherEmplois("", "", "Tous", "");

        String[] colonnes = {"Titre", "Société", "Emplacement", "Contrat"};
        Object[][] donnees = new Object[emplois.size()][4];

        for (int i = 0; i < emplois.size(); i++) {
            Modele.Emploi emploi = emplois.get(i);
            donnees[i][0] = emploi.getTitre();
            donnees[i][1] = emploi.getNomEntreprise();
            donnees[i][2] = emploi.getLieu();
            donnees[i][3] = emploi.getCategorie();
        }

        resultatTable.setModel(new javax.swing.table.DefaultTableModel(donnees, colonnes));
    }

}
