package DAO;

import modele.Utilisateur;
import java.sql.*;

public class UtilisateurDAO {
    public Utilisateur getByEmail(String email) {
        String query = "SELECT * FROM Utilisateur WHERE Email = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Utilisateur(rs.getInt("ID_Utilisateur"),
                        rs.getString("Nom"),
                        rs.getString("Email"),
                        rs.getString("Mot_de_Passe"),
                        rs.getString("Type_Utilisateur")) {
                    @Override
                    public void seConnecter() {
                        System.out.println("Utilisateur connecté : " + getNom());
                    }
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
