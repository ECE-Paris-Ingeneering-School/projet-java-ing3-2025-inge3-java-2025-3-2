package DAO;

import Modele.Utilisateur;
import java.sql.*;

public class UtilisateurDAO {

    public Utilisateur getByMail(String mail) {
        String query = "SELECT * FROM utilisateur WHERE mail = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Utilisateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("mail"),
                        rs.getString("motDePasse"),
                        rs.getString("type")
                ) {
                    @Override
                    public void seConnecter() {
                        System.out.println("Connexion utilisateur : " + getNom());
                    }
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
