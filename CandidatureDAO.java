package DAO;

import modele.Candidature;
import modele.Demandeur;
import modele.Emploi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {

    public void insert(Candidature candidature) {
        String query = "INSERT INTO Candidature (ID_Emploi, ID_Demandeur, Date_Postulation, Statut, Note) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, candidature.getEmploi().getId());
            ps.setInt(2, candidature.getDemandeur().getId());
            ps.setDate(3, new java.sql.Date(candidature.getDatePostulation().getTime()));
            ps.setString(4, candidature.getStatut());
            ps.setString(5, candidature.getNote());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Candidature> getByDemandeur(Demandeur demandeur) {
        List<Candidature> candidatures = new ArrayList<>();
        String query = "SELECT * FROM Candidature WHERE ID_Demandeur = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, demandeur.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Candidature candidature = new Candidature(
                        demandeur,
                        new Emploi(rs.getInt("ID_Emploi"), null, null, null, 0, null, null, null)
                );
                candidature.setId(rs.getInt("ID_Candidature"));
                candidature.setDatePostulation(rs.getDate("Date_Postulation"));
                candidature.setStatut(rs.getString("Statut"));
                candidature.setNote(rs.getString("Note"));
                candidatures.add(candidature);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidatures;
    }
}
