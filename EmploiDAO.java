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

