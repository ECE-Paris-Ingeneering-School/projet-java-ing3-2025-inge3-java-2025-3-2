package Modele;

package modele;

import java.util.ArrayList;
import java.util.List;

public class Demandeur extends Utilisateur {
    private String cv;
    private String profil;
    private List<Candidature> candidatures;

    public Demandeur(int id, String nom, String mail, String motDePasse, String cv, String profil) {
        super(id, nom, mail, motDePasse, "Demandeur");
        this.cv = cv;
        this.profil = profil;
        this.candidatures = new ArrayList<>();
    }

    @Override
    public void seConnecter() {
        System.out.println("Demandeur connecté : " + nom);
    }

    public void postuler(Emploi emploi) {
        Candidature candidature = new Candidature(this, emploi);
        candidatures.add(candidature);
        emploi.getStatistique().incrementerPostulation();
    }

    public List<Candidature> voirHistorique() {
        return candidatures;
    }

    // Getters & Setters
    public String getCv() { return cv; }
    public void setCv(String cv) { this.cv = cv; }

    public String getProfil() { return profil; }
    public void setProfil(String profil) { this.profil = profil; }

    public List<Candidature> getCandidatures() { return candidatures; }
    public void setCandidatures(List<Candidature> candidatures) { this.candidatures = candidatures; }
}
