package Application;

import db.DB;

import java.sql.*;
import java.text.SimpleDateFormat;

public class Program3 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();
            /*
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    +   "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    +   "VALUES "
                    +   "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);                                  //? significa placeholder

            st.setString(1, "irlan suzano");
            st.setString(2, "irlansuzano@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("20/05/1997").getTime()));           //toda vez que for passar data em banco eh o java sql date
            st.setDouble(4, 3000.0);
            st.setInt(5, 4);
             */
              st = conn.prepareStatement(
                      "insert into department (name) values ('D1'), ('D2')", Statement.RETURN_GENERATED_KEYS);
            int rowsAffected = st.executeUpdate();                                 //somente quando for alterar os dados

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id =" + id);
                }
            }else{
                System.out.println("no row affected");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.closeStatement(st);
            DB.closeConnection();


        }

    }
}
