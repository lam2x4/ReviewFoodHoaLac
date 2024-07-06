/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.ReportType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lam1
 */
public class DAOReportType extends DBConnect {

    public Vector<ReportType> getAll() throws SQLException {
        String sql = "select * from report_type";
        Vector<ReportType> list = new Vector<>();
        try (PreparedStatement pre = conn.prepareStatement(sql)) {
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                ReportType b = new ReportType();

                b.setId(rs.getInt(1));
                b.setName(rs.getString(2));
                b.setDescription(rs.getString(3));

                list.add(b);
            }
            pre.close();
            return list;
        }
    }

    public static void main(String[] args) {
        DAOReportType dao = new DAOReportType();
        try {
            System.out.println(dao.getAll().get(2).getDescription());
        } catch (SQLException ex) {
            Logger.getLogger(DAOReportType.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
