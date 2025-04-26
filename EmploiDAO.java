package DAO;

import modele.Emploi;
import modele.Agence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmploiDAO {

    public void insert(Emploi emploi) {
        String query = "INSERT INTO Emploi (ID_Agence, Titre, Description, Categorie, Remuneration, Lieu, Date_Publication) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

    public void delete(int id) {
        String query = "DELETE FROM Emploi WHERE ID_Emploi = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emploi> getByCategorieAndLieu(String categorie, String lieu) {
        List<Emploi> emplois = new ArrayList<>();
        String query = "SELECT * FROM Emploi WHERE Categorie = ? AND Lieu = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, categorie);
            ps.setString(2, lieu);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Emploi emploi = new Emploi(
                        rs.getInt("ID_Emploi"),
                        rs.getString("Titre"),
                        rs.getString("Description"),
                        rs.getString("Categorie"),
                        rs.getFloat("Remuneration"),
                        rs.getString("Lieu"),
                        rs.getDate("Date_Publication"),
                        null
                );
                emplois.add(emploi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emplois;
    }
}
