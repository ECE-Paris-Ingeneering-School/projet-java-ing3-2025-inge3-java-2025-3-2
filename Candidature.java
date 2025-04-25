package Modele;

package modele;

import java.util.Date;

public class Candidature {
    private int id;
    private Date datePostulation;
    private String statut;
    private String note;
    private Demandeur demandeur;
    private Emploi emploi;

    public Candidature(Demandeur demandeur, Emploi emploi) {
        this.demandeur = demandeur;
        this.emploi = emploi;
        this.datePostulation = new Date();
        this.statut = "En attente";
        this.note = "";
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDatePostulation() { return datePostulation; }
    public void setDatePostulation(Date datePostulation) { this.datePostulation = datePostulation; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public Demandeur getDemandeur() { return demandeur; }
    public void setDemandeur(Demandeur demandeur) { this.demandeur = demandeur; }

    public Emploi getEmploi() { return emploi; }
    public void setEmploi(Emploi emploi) { this.emploi = emploi; }
}
