package DAO;

import Modele.Emploi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmploiDAO {

    public void insert(Emploi emploi) {
        String query = "INSERT INTO emploi (id_agence, titre, description, typeContrat, categorie, remuneration, lieu, datePublication) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, emploi.getAgence().getId());
            ps.setString(2, emploi.getTitre());
            ps.setString(3, emploi.getDescription());
            ps.setString(4, emploi.getCategorie());
            ps.setFloat(5, emploi.getRemuneration());
            ps.setString(6, emploi.getLieu());
            ps.setDate(7, new java.sql.Date(emploi.getDatePublication().getTime()));
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emploi> getByCategorieAndLieu(String categorie, String lieu) {
        List<Emploi> emplois = new ArrayList<>();
        String query = "SELECT * FROM emploi WHERE categorie = ? AND lieu = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, categorie);
            ps.setString(2, lieu);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Emploi emploi = new Emploi(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getString("categorie"),
                        rs.getFloat("remuneration"),
                        rs.getString("lieu"),
                        rs.getDate("datePublication"),
                        null
                );
                emplois.add(emploi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emplois;
    }

    public void delete(int id) {
        String query = "DELETE FROM emploi WHERE id = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
