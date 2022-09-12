package Application;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Program6 {

    public static void main(String[] args) {

        Connection conn = null;
        Statement st = null;

        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);                                                                          //só permite que a transação seja feita quando o dev autorizar com conn.commit();
            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

//            int x = 1;
//            if (x < 2) {
//                throw new SQLException("fake error");
//            }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();
            System.out.println("rows  1:"+ rows1);
            System.out.println("rows  2:"+ rows2);
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            }catch (SQLException e1){
                throw new DbException("Error trying to roll back! Caused by: " + e1.getMessage());
            }
            } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
