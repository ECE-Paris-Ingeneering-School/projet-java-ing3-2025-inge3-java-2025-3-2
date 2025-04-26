package Modele;

package modele;

import java.util.ArrayList;
import java.util.List;

public class Agence extends Utilisateur {
    private String secteurActivite;
    private String emailContact;
    private String telephone;
    private String nomEntreprise;
    private List<Emploi> emplois;

    public Agence(int id, String nom, String mail, String motDePasse, String secteurActivite,
                  String emailContact, String telephone, String nomEntreprise) {
        super(id, nom, mail, motDePasse, "Agence");
        this.secteurActivite = secteurActivite;
        this.emailContact = emailContact;
        this.telephone = telephone;
        this.nomEntreprise = nomEntreprise;
        this.emplois = new ArrayList<>();
    }

    @Override
    public void seConnecter() {
        System.out.println("Agence connectée : " + nom);
    }

    public void publierEmploi(Emploi emploi) {
        emplois.add(emploi);
    }

    public void supprimerEmploi(Emploi emploi) {
        emplois.remove(emploi);
    }

    public Statistique analyserStatistiques(Emploi emploi) {
        return emploi.getStatistique();
    }

    // Getters & Setters
    public String getSecteurActivite() { return secteurActivite; }
    public void setSecteurActivite(String secteurActivite) { this.secteurActivite = secteurActivite; }

    public String getEmailContact() { return emailContact; }
    public void setEmailContact(String emailContact) { this.emailContact = emailContact; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getNomEntreprise() { return nomEntreprise; }
    public void setNomEntreprise(String nomEntreprise) { this.nomEntreprise = nomEntreprise; }

    public List<Emploi> getEmplois() { return emplois; }
    public void setEmplois(List<Emploi> emplois) { this.emplois = emplois; }
}
