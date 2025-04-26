package DAO;

import Modele.Demandeur;
import java.sql.*;

public class DemandeurDAO {

    public Demandeur getByIdf(int idf) {
        String query = "SELECT * FROM demandeur WHERE idf = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, idf);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Demandeur(
                        rs.getInt("idf"),
                        null,
                        null,
                        null,
                        rs.getString("cv"),
                        rs.getString("profil")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
