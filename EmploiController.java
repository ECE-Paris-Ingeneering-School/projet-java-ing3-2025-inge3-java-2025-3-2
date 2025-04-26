package controller;

import dao.EmploiDAO;
import model.Emploi;

import java.util.List;

public class EmploiController {
    private EmploiDAO emploiDAO;

    public EmploiController() {
        this.emploiDAO = new EmploiDAO();
    }

// Le DAO doit fournir : Une méthode : void insert(Emploi emploi), qui insère un emploi dans la table EMPLOI

    public void ajouterEmploi(Emploi emploi) {
        if (emploi == null) {
            throw new IllegalArgumentException("Emploi ne peut pas être nul.");
        }
        emploiDAO.insert(emploi);  //  Méthode à créer dans EmploiDAO
    }

   // Le DAO doit fournir : Une méthode : void delete(int id), qui supprime l'emploi dont l'id correspond.

    public void supprimerEmploi(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invalide pour la suppression d'emploi.");
        }
        emploiDAO.delete(id);  // Méthode à créer dans EmploiDAO
    }

     
    // a coder dans dao, une méthode : List<Emploi> getByCategorieAndLieu(String categorie, String lieu)
    // qui renvoie les emplois correspondant aux critères.

    public List<Emploi> chercherEmploi(String categorie, String lieu) {
        if (categorie == null || lieu == null) {
            throw new IllegalArgumentException("Catégorie et lieu doivent être renseignés.");
        }
        return emploiDAO.getByCategorieAndLieu(categorie, lieu);  // Méthode à créer dans EmploiDAO
    }
}
