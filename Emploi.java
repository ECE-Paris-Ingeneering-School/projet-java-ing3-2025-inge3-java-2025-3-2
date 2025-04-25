package Modele;

package modele;

import java.util.Date;

public class Emploi {
    private int id;
    private String titre;
    private String description;
    private String categorie;
    private float remuneration;
    private String lieu;
    private Date datePublication;
    private Agence agence;
    private Statistique statistique;

    public Emploi(int id, String titre, String description, String categorie, float remuneration,
                  String lieu, Date datePublication, Agence agence) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.remuneration = remuneration;
        this.lieu = lieu;
        this.datePublication = datePublication;
        this.agence = agence;
        this.statistique = new Statistique(this);
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public float getRemuneration() { return remuneration; }
    public void setRemuneration(float remuneration) { this.remuneration = remuneration; }

    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }

    public Date getDatePublication() { return datePublication; }
    public void setDatePublication(Date datePublication) { this.datePublication = datePublication; }

    public Agence getAgence() { return agence; }
    public void setAgence(Agence agence) { this.agence = agence; }

    public Statistique getStatistique() { return statistique; }
    public void setStatistique(Statistique statistique) { this.statistique = statistique; }
}
