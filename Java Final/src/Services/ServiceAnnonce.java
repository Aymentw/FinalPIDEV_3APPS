/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Annonce;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Tech.DataSource;

/**
 *
 * @author Aymen
 */
public class ServiceAnnonce {

    public Connection connection = DataSource.GetInstance().GetConnection();
    public Statement ste;

    public ServiceAnnonce() {

        try {
            ste = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAnnonce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AjouterAnnonce(Annonce a) throws SQLException {
        String req = "INSERT INTO Annonce(libelle,description,path) VALUES(?,?,?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, a.getLibelle());
        pre.setString(2, a.getDescription());
        pre.setString(3, a.getPath());
        pre.executeUpdate();
        System.out.println("Annonce Ajoutée");
    }

    public void updateAnnonce(Annonce a) throws SQLException {
        String req = "UPDATE Annonce SET libelle=?,description=? WHERE id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, a.getLibelle());
        pre.setString(2, a.getDescription());
        pre.setInt(3, a.getId());
        pre.executeUpdate();
    }

    public void deleteAnnonce(int id) throws SQLException {
        String req = "DELETE FROM Annonce WHERE id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, id);
        pre.execute();
    }

    public void afficherAnnonces() throws SQLException {
        ResultSet rs = ste.executeQuery("SELECT * FROM Annonce");

        List<Annonce> annonces = new ArrayList<>();
        while (rs.next()) {
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));

        }

    }

    public ObservableList<Annonce> displayAnnonce() throws SQLException {

        ObservableList<Annonce> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM Annonce";
        PreparedStatement ste = connection.prepareStatement(req);
        ResultSet result = ste.executeQuery();

        while (result.next()) {
            Annonce a = new Annonce(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));

            list.add(a);
        }

        return list;
    }

    public void pdfs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Annonce> display1Annonce(int id) throws SQLException {

        ObservableList<Annonce> list = FXCollections.observableArrayList();
        String req = "SELECT * FROM Annonce where id='" + id + "'";
        PreparedStatement ste = connection.prepareStatement(req);
        ResultSet result = ste.executeQuery();

        while (result.next()) {
            Annonce a = new Annonce(result.getInt(1), result.getString(2), result.getString(3));

            list.add(a);
        }

        return list;
    }

}
