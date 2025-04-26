package DAO;

import modele.Agence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgenceDAO {

    public Agence getById(int id) {
        String query = "SELECT * FROM Agence WHERE ID_Agence = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Agence(
                        rs.getInt("ID_Agence"),
                        rs.getString("Nom_Entreprise"),
                        rs.getString("Email_Contact"),
                        null, // motDePasse (non récupéré ici)
                        rs.getString("Secteur_Activite"),
                        rs.getString("Email_Contact"),
                        rs.getString("Telephone"),
                        rs.getString("Nom_Entreprise")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Agence> getAll() {
        List<Agence> agences = new ArrayList<>();
        String query = "SELECT * FROM Agence";

        try (Connection conn = ConnexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Agence agence = new Agence(
                        rs.getInt("ID_Agence"),
                        rs.getString("Nom_Entreprise"),
                        rs.getString("Email_Contact"),
                        null,
                        rs.getString("Secteur_Activite"),
                        rs.getString("Email_Contact"),
                        rs.getString("Telephone"),
                        rs.getString("Nom_Entreprise")
                );
                agences.add(agence);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agences;
    }

    public void insert(Agence agence) {
        String query = "INSERT INTO Agence (ID_Utilisateur, Secteur_Activite, Email_Contact, Telephone, Nom_Entreprise) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, agence.getId());
            ps.setString(2, agence.getSecteurActivite());
            ps.setString(3, agence.getEmailContact());
            ps.setString(4, agence.getTelephone());
            ps.setString(5, agence.getNomEntreprise());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM Agence WHERE ID_Agence = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

