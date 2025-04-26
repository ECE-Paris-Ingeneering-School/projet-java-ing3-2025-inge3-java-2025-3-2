package DAO;

import modele.Demandeur;
import java.sql.*;

public class DemandeurDAO {

    public Demandeur getById(int id) {
        String query = "SELECT * FROM Demandeur WHERE ID_Demandeur = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Demandeur(
                        rs.getInt("ID_Demandeur"),
                        null, null, null,
                        rs.getString("CV"),
                        rs.getString("Profil")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
