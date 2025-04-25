package Modele;

package modele;

public abstract class Utilisateur {
    protected int id;
    protected String nom;
    protected String mail;
    protected String motDePasse;
    protected String type;

    public Utilisateur(int id, String nom, String mail, String motDePasse, String type) {
        this.id = id;
        this.nom = nom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.type = type;
    }

    public abstract void seConnecter();

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
