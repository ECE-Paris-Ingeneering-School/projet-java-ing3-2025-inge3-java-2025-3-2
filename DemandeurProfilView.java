package View;

import javax.swing.*;
import java.awt.*;
import Modele.Utilisateur;
import Modele.Candidature;
import DAO.CandidatureDAO;
import Modele.Demandeur;

public class DemandeurProfilView extends JPanel {

    JButton modifierInfos, rechercherEmploi;
    JTextArea infosUtilisateur, historiqueCandidatures;

    private ApplicationFrame parent;
    private Demandeur demandeur;

    public DemandeurProfilView(ApplicationFrame parent, Demandeur demandeur) {
        this.parent = parent;
        this.demandeur = demandeur;

        setLayout(new BorderLayout(10, 10));

        // Panel infos personnelles
        JPanel infosPanel = new JPanel(new BorderLayout());
        infosPanel.setBorder(BorderFactory.createTitledBorder("Informations personnelles"));

        infosUtilisateur = new JTextArea();
        infosUtilisateur.setEditable(false);

        if (demandeur != null) {
            infosUtilisateur.setText(
                            "Nom : " + demandeur.getNom() + "\n" +
                            "Email : " + demandeur.getMail() + "\n" +
                            "Type : " + demandeur.getType() + "\n" +
                            "CV : " + demandeur.getCv() + "\n" +
                            "Profil : " + demandeur.getProfil() + "\n"
            );
        } else {
            infosUtilisateur.setText("Utilisateur non connecté");
        }

        modifierInfos = new JButton("Modifier les infos");
        modifierInfos.addActionListener(e -> {
            parent.addView("modifierInfos", new ModifierInfosView(parent));
            parent.navigateTo("modifierInfos");
        });

        infosPanel.add(new JScrollPane(infosUtilisateur), BorderLayout.CENTER);
        infosPanel.add(modifierInfos, BorderLayout.SOUTH);

        // Panel historique candidatures
        JPanel historiquePanel = new JPanel(new BorderLayout());
        historiquePanel.setBorder(BorderFactory.createTitledBorder("Historique de candidatures"));

        historiqueCandidatures = new JTextArea();
        historiqueCandidatures.setEditable(false);

        if (this.demandeur != null) {
            CandidatureDAO candidatureDAO = new CandidatureDAO();

            java.util.List<Candidature> candidatures = candidatureDAO.getByDemandeur(this.demandeur);

            StringBuilder historique = new StringBuilder();
            boolean candidatureTrouvee = false;

            if (candidatures != null) {
                for (Candidature candidature : candidatures) {
                    if (candidature.getDemandeur() != null && candidature.getDemandeur().getIdf() == this.demandeur.getIdf()) {
                        candidatureTrouvee = true;
                        historique.append("- ")
                                .append(candidature.getEmploi() != null ? candidature.getEmploi().getTitre() : "Offre inconnue")
                                .append(" | Statut: ")
                                .append(candidature.getStatut() != null ? candidature.getStatut() : "N/A")
                                .append("\n");
                    }
                }
            }

            if (candidatureTrouvee) {
                historiqueCandidatures.setText(historique.toString());
            } else {
                historiqueCandidatures.setText("Aucune candidature trouvée pour ce demandeur.");
            }
        } else {
            historiqueCandidatures.setText("Non connecté comme demandeur.");
        }

        historiquePanel.add(new JScrollPane(historiqueCandidatures), BorderLayout.CENTER);

        // Bouton rechercher emploi
        rechercherEmploi = new JButton("Rechercher emploi");
        rechercherEmploi.addActionListener(e -> parent.navigateTo("rechercheEmploi"));

        JPanel bas = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bas.add(rechercherEmploi);

        // Ajout des panels à l'affichage global
        add(infosPanel, BorderLayout.WEST);
        add(historiquePanel, BorderLayout.CENTER);
        add(bas, BorderLayout.SOUTH);
    }
}
