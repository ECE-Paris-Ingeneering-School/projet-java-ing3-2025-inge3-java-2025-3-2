package Modele;

package modele;

public class Statistique {
    private int id;
    private int nombreVue;
    private int nombrePostulation;
    private Emploi emploi;

    // Constructeur par défaut
    public Statistique() {
    }

    // Constructeur avec emploi associé
    public Statistique(Emploi emploi) {
        this.emploi = emploi;
        this.nombreVue = 0;
        this.nombrePostulation = 0;
    }

    // Constructeur complet
    public Statistique(int id, int nombreVue, int nombrePostulation, Emploi emploi) {
        this.id = id;
        this.nombreVue = nombreVue;
        this.nombrePostulation = nombrePostulation;
        this.emploi = emploi;
    }

    // Méthodes métier pour incrémenter les statistiques
    public void incrementerVue() {
        this.nombreVue++;
    }

    public void incrementerPostulation() {
        this.nombrePostulation++;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNombreVue() {
        return nombreVue;
    }

    public void setNombreVue(int nombreVue) {
        this.nombreVue = nombreVue;
    }

    public int getNombrePostulation() {
        return nombrePostulation;
    }

    public void setNombrePostulation(int nombrePostulation) {
        this.nombrePostulation = nombrePostulation;
    }

    public Emploi getEmploi() {
        return emploi;
    }

    public void setEmploi(Emploi emploi) {
        this.emploi = emploi;
    }
}

