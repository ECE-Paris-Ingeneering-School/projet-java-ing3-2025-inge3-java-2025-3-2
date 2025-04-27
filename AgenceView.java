package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import DAO.EmploiDAO;
import DAO.AgenceDAO;
import DAO.CandidatureDAO;
import Modele.Emploi;
import Modele.Agence;
import java.util.List;

public class AgenceView extends JPanel {

    private ApplicationFrame parent;
    private Agence agenceConnectee; // ✅ Agence connectée
    JButton ajouterEmploi, modifierEmploi, supprimerEmploi;
    JTable emploisTable, candidatsTable;

    public AgenceView(ApplicationFrame parent) {
        this.parent = parent;
        setLayout(new BorderLayout(10, 10));

        // Récupérer l'agence connectée
        AgenceDAO agenceDAO = new AgenceDAO();
        this.agenceConnectee = agenceDAO.getAgenceByUtilisateur(parent.getUtilisateurConnecte().getId());

        // Panel des offres d'emploi
        JPanel emploisPanel = new JPanel(new BorderLayout());
        emploisPanel.setBorder(BorderFactory.createTitledBorder("Vos offres d'emplois"));

        String[] colonnesEmplois = {"Titre", "Société", "Lieu", "Type de contrat"};
        DefaultTableModel emploisModel = new DefaultTableModel(colonnesEmplois, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        emploisTable = new JTable(emploisModel);
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

        String[] colonnesCandidats = {"Nom et Email", "", "", ""};
        DefaultTableModel candidatsModel = new DefaultTableModel(colonnesCandidats, 0);
        candidatsTable = new JTable(candidatsModel);
        JScrollPane candidatsScroll = new JScrollPane(candidatsTable);

        candidatsPanel.add(candidatsScroll, BorderLayout.CENTER);

        // Panel des statistiques
        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistiques"));
        JLabel statsLabel = new JLabel("[Graphique ou résumé des statistiques ici]");
        statsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statsPanel.add(statsLabel, BorderLayout.CENTER);

        // Split haut entre offres et candidats
        JSplitPane splitPaneHaut = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, emploisPanel, candidatsPanel);
        splitPaneHaut.setResizeWeight(0.5);

        add(splitPaneHaut, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);

        // Charger les offres de l'agence
        chargerOffresDepuisBDD();

        // Actions
        ajouterEmploi.addActionListener(e -> ouvrirPopupAjouter());
        modifierEmploi.addActionListener(e -> ouvrirPopupModifier());
        supprimerEmploi.addActionListener(e -> supprimerEmploi());

        // Clic sur une offre pour voir les candidats
        emploisTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int selectedRow = emploisTable.getSelectedRow();
                if (selectedRow != -1) {
                    String titreOffre = (String) emploisTable.getValueAt(selectedRow, 0);
                    chargerCandidatsPourEmploi(titreOffre);
                }
            }
        });
    }

    private void chargerOffresDepuisBDD() {
        EmploiDAO emploiDAO = new EmploiDAO();
        int idAgence = agenceConnectee.getIdAgence(); // ✅ utiliser idAgence
        List<Emploi> emplois = emploiDAO.getEmploisParAgence(idAgence);

        DefaultTableModel model = (DefaultTableModel) emploisTable.getModel();
        model.setRowCount(0);

        for (Emploi emploi : emplois) {
            model.addRow(new Object[]{
                    emploi.getTitre(),
                    emploi.getNomEntreprise() != null ? emploi.getNomEntreprise() : "Votre Société",
                    emploi.getLieu(),
                    emploi.getCategorie()
            });
        }
    }

    private void ouvrirPopupAjouter() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Ajouter une offre", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        JTextField titreField = new JTextField(15);
        JTextField lieuField = new JTextField(15);
        JComboBox<String> typeContratBox = new JComboBox<>(new String[]{"CDI", "CDD", "Stage"});
        JTextField categorieField = new JTextField(15);
        JTextArea descriptionArea = new JTextArea(5, 15);
        JTextField remunerationField = new JTextField(15);

        int y = 0;
        c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Titre :"), c);
        c.gridx = 1; dialog.add(titreField, c); y++;
        c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Lieu :"), c);
        c.gridx = 1; dialog.add(lieuField, c); y++;
        c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Type de contrat :"), c);
        c.gridx = 1; dialog.add(typeContratBox, c); y++;
        c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Catégorie :"), c);
        c.gridx = 1; dialog.add(categorieField, c); y++;
        c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Description :"), c);
        c.gridx = 1; dialog.add(new JScrollPane(descriptionArea), c); y++;
        c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Rémunération :"), c);
        c.gridx = 1; dialog.add(remunerationField, c); y++;

        JPanel panelBtn = new JPanel();
        JButton validerBtn = new JButton("Valider");
        JButton annulerBtn = new JButton("Annuler");
        panelBtn.add(validerBtn);
        panelBtn.add(annulerBtn);

        c.gridx = 0; c.gridy = y; c.gridwidth = 2;
        dialog.add(panelBtn, c);

        validerBtn.addActionListener(ev -> {
            String titre = titreField.getText();
            String lieu = lieuField.getText();
            String typeContrat = (String) typeContratBox.getSelectedItem();
            String categorie = categorieField.getText();
            String description = descriptionArea.getText();
            String remunerationStr = remunerationField.getText();

            if (titre.isEmpty() || lieu.isEmpty() || categorie.isEmpty() || description.isEmpty() || remunerationStr.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Tous les champs doivent être remplis !");
                return;
            }

            float remuneration;
            try {
                remuneration = Float.parseFloat(remunerationStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Erreur : La rémunération doit être un nombre.");
                return;
            }

            Emploi nouvelEmploi = new Emploi(
                    0, titre, description, categorie, remuneration, lieu, new java.util.Date(), agenceConnectee
            );
            nouvelEmploi.setTypeContrat(typeContrat);

            EmploiDAO emploiDAO = new EmploiDAO();
            boolean success = emploiDAO.insert(nouvelEmploi);

            if (success) {
                chargerOffresDepuisBDD();
                JOptionPane.showMessageDialog(dialog, "Offre ajoutée !");
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Erreur lors de l'ajout de l'offre !");
            }
        });

        annulerBtn.addActionListener(ev -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void ouvrirPopupModifier() {
        int selectedRow = emploisTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une offre à modifier.");
            return;
        }

        String titre = (String) emploisTable.getValueAt(selectedRow, 0);
        EmploiDAO emploiDAO = new EmploiDAO();
        Emploi emploi = emploiDAO.getEmploiByTitre(titre);

        if (emploi != null) {
            JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Modifier une offre", true);
            dialog.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(5, 5, 5, 5);
            c.fill = GridBagConstraints.HORIZONTAL;

            JTextField titreField = new JTextField(emploi.getTitre(), 15);
            JTextField lieuField = new JTextField(emploi.getLieu(), 15);
            JTextField categorieField = new JTextField(emploi.getCategorie(), 15);
            JComboBox<String> typeContratBox = new JComboBox<>(new String[]{"CDI", "CDD", "Stage"});
            JTextArea descriptionArea = new JTextArea(emploi.getDescription(), 5, 15);
            JTextField remunerationField = new JTextField(String.valueOf(emploi.getRemuneration()), 15);

            int y = 0;
            c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Titre :"), c);
            c.gridx = 1; dialog.add(titreField, c); y++;
            c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Lieu :"), c);
            c.gridx = 1; dialog.add(lieuField, c); y++;
            c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Catégorie :"), c);
            c.gridx = 1; dialog.add(categorieField, c); y++;
            c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Type de contrat :"), c);
            c.gridx = 1; dialog.add(typeContratBox, c); y++;
            c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Description :"), c);
            c.gridx = 1; dialog.add(new JScrollPane(descriptionArea), c); y++;
            c.gridx = 0; c.gridy = y; dialog.add(new JLabel("Rémunération :"), c);
            c.gridx = 1; dialog.add(remunerationField, c); y++;

            JButton validerBtn = new JButton("Valider");
            JButton annulerBtn = new JButton("Annuler");
            JPanel panelBtn = new JPanel();
            panelBtn.add(validerBtn);
            panelBtn.add(annulerBtn);
            c.gridx = 0; c.gridy = y; c.gridwidth = 2;
            dialog.add(panelBtn, c);

            validerBtn.addActionListener(ev -> {
                emploi.setTitre(titreField.getText());
                emploi.setLieu(lieuField.getText());
                emploi.setCategorie(categorieField.getText());
                emploi.setTypeContrat((String) typeContratBox.getSelectedItem());
                emploi.setDescription(descriptionArea.getText());
                emploi.setRemuneration(Float.parseFloat(remunerationField.getText()));

                if (emploiDAO.update(emploi)) {
                    chargerOffresDepuisBDD();
                    JOptionPane.showMessageDialog(dialog, "Offre modifiée !");
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Erreur lors de la modification.");
                }
            });

            annulerBtn.addActionListener(ev -> dialog.dispose());

            dialog.pack();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Erreur : Offre introuvable.");
        }
    }

    private void supprimerEmploi() {
        int selectedRow = emploisTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une offre à supprimer.");
            return;
        }

        String titre = (String) emploisTable.getValueAt(selectedRow, 0);
        EmploiDAO emploiDAO = new EmploiDAO();
        Emploi emploi = emploiDAO.getEmploiByTitre(titre);

        if (emploi != null) {
            emploiDAO.delete(emploi.getId());
            chargerOffresDepuisBDD();
            JOptionPane.showMessageDialog(this, "Offre supprimée !");
        } else {
            JOptionPane.showMessageDialog(this, "Erreur : Offre introuvable.");
        }
    }

    private void chargerCandidatsPourEmploi(String titreOffre) {
        EmploiDAO emploiDAO = new EmploiDAO();
        CandidatureDAO candidatureDAO = new CandidatureDAO();

        Emploi emploi = emploiDAO.getEmploiByTitre(titreOffre);

        if (emploi == null) {
            JOptionPane.showMessageDialog(this, "Erreur : Offre non trouvée.");
            return;
        }

        List<String> candidatures = candidatureDAO.getCandidatures(emploi.getId());

        DefaultTableModel model = (DefaultTableModel) candidatsTable.getModel();
        model.setRowCount(0);

        for (String candidature : candidatures) {
            model.addRow(new Object[]{candidature, "", "", ""});
        }
    }
}
