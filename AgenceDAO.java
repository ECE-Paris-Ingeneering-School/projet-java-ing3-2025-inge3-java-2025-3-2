package DAO;

import Modele.Agence;
import java.sql.*;

public class AgenceDAO {

    public Agence getById(int id) {
        String query = "SELECT * FROM agence WHERE id = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Agence(
                        rs.getInt("id"),
                        null,
                        rs.getString("mail"),
                        null,
                        rs.getString("secteurActivite"),
                        rs.getString("emailContact"),
                        rs.getString("telephone"),
                        rs.getString("nomEntreprise")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
