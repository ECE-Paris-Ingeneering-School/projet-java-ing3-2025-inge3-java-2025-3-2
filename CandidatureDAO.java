package DAO;

import Modele.Candidature;
import Modele.Demandeur;
import Modele.Emploi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {

    public void insert(Candidature candidature) {
        String query = "INSERT INTO candidature (id_emploi, id_demandeur, datePostulation, statut, note) VALUES (?, ?, ?, ?, ?)";
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
        String query = "SELECT * FROM candidature WHERE id_demandeur = ?";
        try (Connection conn = ConnexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, demandeur.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Candidature candidature = new Candidature(
                        demandeur,
                        new Emploi(rs.getInt("id_emploi"), null, null, null, 0, null, null, null)
                );
                candidature.setId(rs.getInt("id"));
                candidature.setDatePostulation(rs.getDate("datePostulation"));
                candidature.setStatut(rs.getString("statut"));
                candidature.setNote(rs.getString("note"));
                candidatures.add(candidature);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candidatures;
    }
}
