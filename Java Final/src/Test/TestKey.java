/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Tech.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mechlaoui
 */
public class TestKey {
    public static void main(String[] args) throws SQLException {
        Connection connection = DataSource.GetInstance().GetConnection();
        String req = "SELECT * FROM commentaire";
        PreparedStatement pre = connection.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = pre.getGeneratedKeys();
        rs.next();
        System.out.println(rs.getInt(1));
    }
    
}
