package controller;
import dao.UtilisateurDAO;
import model.Utilisateur;

public class LoginController {
    private UtilisateurDAO utilisateurDAO;

    public LoginController() {
        this.utilisateurDAO = new UtilisateurDAO();
    }

    public Utilisateur login(String email, String mdp) { //Méthode publique appelée par l’interface graphique quand l’utilisateur clique sur "Se connecter".
        if (email == null || mdp == null) {
            throw new IllegalArgumentException("Email ou mot de passe ne peut pas être nul.");
        }

        Utilisateur utilisateur = utilisateurDAO.getByEmail(email); // a coder dans la partie dao, qui renvoie l'objet Utilisateur correspondant à l'email, ou null si non trouvé.
        if (utilisateur != null && utilisateur.getMdp().equals(mdp)) { // si utilisateur trouver et mdp valide 
            return utilisateur; 
        }
        return null; // Mauvais identifiants
    }
}
