package controller;

import dao.CandidatureDAO;
import dao.DemandeurDAO;
import model.Candidature;
import model.Demandeur;

import java.util.List;

public class CandidatureController {
    private CandidatureDAO candidatureDAO;
    private DemandeurDAO demandeurDAO;

    public CandidatureController() {
        this.candidatureDAO = new CandidatureDAO();
        this.demandeurDAO = new DemandeurDAO();
    }

 //Le DAO doit fournir : Une méthode void insert(Candidature candidature), qui insère une ligne dans la table CANDIDATU

    public void creerCandidature(Candidature candidature) {
        if (candidature == null) {
            throw new IllegalArgumentException("Candidature ne peut pas être nulle.");
        }
        candidatureDAO.insert(candidature);  // Méthode à créer dans CandidatureDAO
    }

  //Le DAO doit fournir :  Une méthode List<Candidature> getByDemandeur(Demandeur demandeur, qui récupère toutes les candidatures associées à ce demandeur.
    public List<Candidature> voirCandidature(Demandeur demandeur) {
        if (demandeur == null) {
            throw new IllegalArgumentException("Demandeur ne peut pas être nul.");
        }
        return candidatureDAO.getByDemandeur(demandeur);  // Méthode à créer dans CandidatureDAO
    }
}
